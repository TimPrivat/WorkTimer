package com.home.tim.worktimer.views.helloworld;

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

import java.util.Collection;

@PageTitle("Hello World")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends FormLayout {

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


        updatTimeWorked();

    }

    private ProgressBar createProgressBar() {

        ProgressBar progressBar = new ProgressBar();
        progressBar.setValue(0.3);
        return progressBar;


    }

    private void updatTimeWorked() {

        UI ui =UI.getCurrent();
        new Thread(new Runnable() {
            int counter = 0;

            @Override
            public void run() {
                //UI ui = par_time_worked.getUI().get();
                while(true){

                    ui.access(() -> par_time_worked.setText("Gearbeitet: 01:12:00 " +counter++));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                }

        }).start();

    }

}
