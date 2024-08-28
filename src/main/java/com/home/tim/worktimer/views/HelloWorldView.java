package com.home.tim.worktimer.views;

import com.home.tim.worktimer.control.UserControl;
import com.home.tim.worktimer.dtos.UserDTO;
import com.home.tim.worktimer.entities.User;

import com.home.tim.worktimer.repositories.UserRepository;
import com.home.tim.worktimer.views.MainLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

@PageTitle("Hello World")
@Route(value = "Hello-World", layout = MainLayout.class)
public class HelloWorldView extends FormLayout {

    @Autowired
    UserRepository userRepository;
    private Button startWork;
    private Paragraph par_startWork;
    private Paragraph par_time_worked;
    private Button endWork;
    private Button sayHello;

    public HelloWorldView() {

        startWork = new Button("Start Work");
        endWork = new Button("Stop Work");
        par_startWork = new Paragraph("Startzeit: 12:30 Uhr");
        par_time_worked = new Paragraph("Gearbeitet: 01:12:00");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            // Notification.show("Hello " + startWork.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        // setMargin(true);
        // setVerticalComponentAlignment(Alignment.END, name, sayHello);
        add(par_startWork, par_time_worked, startWork, endWork, sayHello);
        TextField t = new TextField("Test");
        add(t);
        add(createProgressBar());


        startWork.addClickListener(event -> {
            LocalDateTime now = LocalDateTime.now();

            UI.getCurrent().access(() -> par_startWork.setText("Startzeit: " + now));

            User Tim = new User();
            Tim.setEmail("email@123");
            Tim.setPassword("PassWord");
            Tim.setUsername("TimsUsername");
            Tim.setRole("User");

            userRepository.save(Tim);
        });

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UserDTO currentUser = (UserDTO) UI.getCurrent().getSession().getAttribute("CurrentUser");

        if (currentUser == null) {
            attachEvent.getUI().getCurrent().navigate("login");

        } else {
            System.out.println("Authenticated!");

        }

    }

    private ProgressBar createProgressBar() {

        ProgressBar progressBar = new ProgressBar();
        progressBar.setValue(0.3);
        return progressBar;


    }




}


