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
import com.vaadin.flow.component.AttachNotifier;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.communication.PushMode;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;


@PageTitle("Overview")
@Route(value = Routes.OVERVIEW, layout = MainLayout.class)
public class Overview extends VerticalLayout {

    @Autowired
    protected TimestampControl timestampControl;
    @Autowired
    LoginControl loginControl;
    private H2 heading;
    private FormLayout formLayout;
    private Button startWork;
    private Button endWork;
    private Paragraph par_startWork;
    private Paragraph par_time_worked;
    private ProgressBar progressBar;
    private double progressBarProgress;

    private UserDTO currentUser;
    private UI ui;

    public Overview() {

        createForm();
        createHeading();
        createProgressBar();

        add(heading, formLayout, progressBar);


    }
// TODO:
// - Input Field for Custom Work TIme


    @Override
    protected void onAttach(AttachEvent attachEvent) {

        currentUser = loginControl.validateUser(attachEvent);
        heading.setText("Welcome " + currentUser.getUserName());
        configureButtons();
        configureButtonLabels();
        timeWorkedUpdates(UI.getCurrent());


    }

    private void createForm() {

        formLayout = new FormLayout();

        startWork = new Button("Start Work");
        endWork = new Button("Stop Work");
        par_startWork = new Paragraph("Startzeit:  Uhr");
        par_time_worked = new Paragraph("Gearbeitet: ");

        formLayout.add(par_startWork, par_time_worked, startWork, endWork);


    }

    private void createProgressBar() {

        progressBar = new ProgressBar();

    }

    private Double getProgressBarValue() {
        //Value for 9,5 hours;
        //int secondsToWork = 34000;

        int secondsToWork = 20;
        TimestampDTO timestampDTO = timestampControl.getLatestTimeOfUser(currentUser);

        long secondsSinceStart = 1;
        if (timestampDTO.getType() == Type.START) {

            Duration difference = Duration.between(timestampDTO.getTime(), LocalDateTime.now());
            secondsSinceStart = difference.getSeconds();
        } else {
            TimestampDTO starTimestampDTO = timestampControl.getLatestTypeTimeOfUser(currentUser, Type.START);
            Duration difference = Duration.between(starTimestampDTO.getTime(), timestampDTO.getTime());
            secondsSinceStart = difference.getSeconds();

        }


        Double progress = (double) secondsSinceStart / secondsToWork;
        progressBarProgress = (progress >= 1) ? 1 : progress;

        return progressBarProgress;
    }

    private void createHeading() {

        heading = new H2();
        heading.setText("Welcome ");


    }


    private void configureButtons() {

        if (currentUser == null) return;

        updateButtonStates();

        startWork.addClickListener(event -> {

            TimestampDTO timestampDTO = new TimestampDTOImpl();

            timestampDTO.setType(Type.START);
            timestampDTO.setTime(LocalDateTime.now());
            timestampDTO.setUserID(currentUser.getUserID());

            timestampControl.saveTimeStamp(timestampDTO);

            UI.getCurrent().access(() -> par_startWork.setText("Startzeit: " + timestampDTO + "Uhr"));
            timeWorkedUpdates(UI.getCurrent());
            updateButtonStates();
            configureButtonLabels();

        });

        endWork.addClickListener(buttonClickEvent -> {

            TimestampDTO timestampDTO = new TimestampDTOImpl();

            timestampDTO.setType(Type.END);
            timestampDTO.setTime(LocalDateTime.now());
            timestampDTO.setUserID(currentUser.getUserID());

            timestampControl.saveTimeStamp(timestampDTO);

            UI.getCurrent().access(() -> {

                par_time_worked.setText("Endzeit: " + timestampDTO);

                progressBar.setValue(getProgressBarValue());
            });
            updateButtonStates();
            configureButtonLabels();


        });


    }

    private void updateButtonStates() {
        TimestampDTO timestampDTO = timestampControl.getLatestTimeOfUser(currentUser);

        boolean isStart = timestampDTO == null || timestampDTO.getType() == Type.END;
        endWork.setEnabled(!isStart);
        startWork.setEnabled(isStart);


    }

    private void configureButtonLabels() {

        TimestampDTO startTime = timestampControl.getLatestTimeOfUser(currentUser);

        if (startTime == null) return;

        String updatedStart = "Startzeit: " + startTime + " Uhr";


        String updatedEnd;
        if (timestampControl.getLatestTimeOfUser(currentUser).getType() == Type.START) {

            String endTime = startTime.timeDifferenceSinceStart();
            updatedEnd = "Gearbeitet: " + endTime;
        } else {
            startTime = timestampControl.getLatestTypeTimeOfUser(currentUser, Type.START);
            TimestampDTO endTimeDTO = timestampControl.getLatestTypeTimeOfUser(currentUser, Type.END);

            String endTime = startTime.timeDifferenceBetween(endTimeDTO.getTime());
            updatedEnd = "Gearbeitet: " + endTime;

        }


        UI.getCurrent().access(() -> {
            par_startWork.setText(updatedStart);
            par_time_worked.setText(updatedEnd);
        });


    }

    public void timeWorkedUpdates(UI ui) {

        currentUser = (UserDTO) ui.getSession().getAttribute("CurrentUser");
        if (currentUser == null || ui == null) return;


        new Thread(() -> updateTimeWorkedLambda(ui)).start();

    }

    private void updateTimeWorkedLambda(UI ui) {


        while (timestampControl.getLatestTimeOfUser(currentUser).getType() == Type.START) {
            TimestampDTO startTime = timestampControl.getLatestTimeOfUser(currentUser);
            try {
                Thread.sleep(1000);
                if (!ui.isAttached()) return;
                ui.access(() -> {
                    par_time_worked.setText("Gearbeitet: " + startTime.timeDifferenceSinceStart());
                    progressBar.setValue(getProgressBarValue());
                });

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        if (timestampControl.getLatestTimeOfUser(currentUser).getType() == Type.END) {
            if (!ui.isAttached()) return;
            ui.access(() -> {
                progressBar.setValue(getProgressBarValue());
            });

        }
    }

}


