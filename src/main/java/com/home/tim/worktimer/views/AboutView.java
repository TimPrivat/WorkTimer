package com.home.tim.worktimer.views;

import com.home.tim.worktimer.control.LoginControl;
import com.home.tim.worktimer.dtos.UserDTO;
import com.home.tim.worktimer.entities.User;
import com.home.tim.worktimer.util.Routes;
import com.home.tim.worktimer.views.MainLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("About")
@Route(value = Routes.ABOUT, layout = MainLayout.class)
public class AboutView extends VerticalLayout {
    @Autowired
    LoginControl loginControl;
    public AboutView() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("To be updated");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("This simple page was developed as a side Project."));
        add(new Paragraph("If you encounter bugs or have ideas for improvement please contact: timleon2701@gmail.com"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {

        UserDTO userDTO = loginControl.validateUser(attachEvent);


    }

}
