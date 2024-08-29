package com.home.tim.worktimer.views;

import com.home.tim.worktimer.control.LoginControl;
import com.home.tim.worktimer.control.TimestampControl;
import com.home.tim.worktimer.dtos.TimestampDTO;
import com.home.tim.worktimer.dtos.UserDTO;
import com.home.tim.worktimer.dtos.impl.TimestampDTOImpl;
import com.home.tim.worktimer.entities.Timestamp;
import com.home.tim.worktimer.entities.Type;
import com.home.tim.worktimer.entities.User;

import com.home.tim.worktimer.repositories.TimeStampRepository;
import com.home.tim.worktimer.repositories.UserRepository;
import com.home.tim.worktimer.util.Routes;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@PageTitle("Overview")
@Route(value = Routes.OVERVIEW, layout = MainLayout.class)
public class Overview extends VerticalLayout {

    @Autowired
    LoginControl loginControl;


    private H2 heading;
    private FormLayout formLayout;
    private Button startWork;
    private Button endWork;
    private Paragraph par_startWork;
    private Paragraph par_time_worked;
    private ProgressBar progressBar;
    private Button testButton;

    private UserDTO currentUser;

    public Overview() {

        createForm();
        createHeading();
        createProgressBar();
        createTestButton();

        add(heading, formLayout, progressBar, testButton);

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {

        currentUser = loginControl.validateUser(attachEvent);
        heading.setText("Welcome " + currentUser.getUserName());
        configureButtons();

    }

    private void createForm() {

        formLayout = new FormLayout();

        startWork = new Button("Start Work");
        endWork = new Button("Stop Work");
        par_startWork = new Paragraph("Startzeit: 12:30 Uhr");
        par_time_worked = new Paragraph("Gearbeitet: 01:12:00");

        formLayout.add(par_startWork, par_time_worked, startWork, endWork);


    }

    private void createProgressBar() {

        progressBar = new ProgressBar();
        progressBar.setValue(0.3);


    }

    private void createHeading() {

        heading = new H2();
        heading.setText("Welcome ");


    }

    @Autowired
    TimestampControl timestampControl;

    private void createTestButton() {

        testButton = new Button("Test");

        testButton.addClickListener(buttonClickEvent -> {

            timestampControl.saveCurrentTimeStamp(currentUser);
            timestampControl.getLatestTimeOfUser(currentUser);


        });
    }

    private void configureButtons() {

        if (currentUser == null)
            return;

        updateButtonStates();

        startWork.addClickListener(event -> {

            TimestampDTO timestampDTO = new TimestampDTOImpl();

            timestampDTO.setType(Type.START);
            timestampDTO.setTime(LocalDateTime.now());
            timestampDTO.setUserID(currentUser.getUserID());

            timestampControl.saveTimeStamp(timestampDTO);

            UI.getCurrent().access(() -> par_startWork.setText("Startzeit: " + timestampDTO+"Uhr"));
            updateButtonStates();
            System.err.println("Start");

        });

        endWork.addClickListener(buttonClickEvent -> {

            UI.getCurrent().access(() -> par_time_worked.setText("Endzeit: " + LocalDateTime.now()));
            updateButtonStates();
            System.err.println("END");
        });

    }
    private void updateButtonStates(){
        TimestampDTO timestampDTO = timestampControl.getLatestTimeOfUser(currentUser);

        boolean isStart = timestampDTO == null || timestampDTO.getType() == Type.END;
        endWork.setEnabled(!isStart);
        startWork.setEnabled(isStart);


    }


}


