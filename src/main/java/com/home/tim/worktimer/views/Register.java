package com.home.tim.worktimer.views;

import com.home.tim.worktimer.control.UserControl;
import com.home.tim.worktimer.dtos.UserDTO;
import com.home.tim.worktimer.dtos.impl.UserDTOImpl;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Register")
@Route(value = "register")
public class Register extends VerticalLayout {


    @Autowired
    UserControl userControl;
    private FormLayout formLayout;
    private TextField userName;
    private TextField eMail;
    private PasswordField passwordField1;
    private PasswordField passwordField2;
    private Button register;


    private String userNameValue;
    private String emailValue;
    private String password1Value;
    private String password2Value;

    public Register() {
        formLayout = new FormLayout();

        userName = new TextField("Username:");
        eMail = new TextField("E-Mail:");
        passwordField1 = new PasswordField("Enter your Password please:");
        passwordField2 = new PasswordField("Please confirm your Password:");

        register = new Button("Register");
        formLayout.setColspan(register, 2);
        register.addClassName("register-button");


        register.addClickListener(click -> createUser());
        formLayout.add(userName, eMail, passwordField1, passwordField2);
        formLayout.addClassName("register-Form");
        add(formLayout, register);

        setAlignItems(Alignment.CENTER);

    }

    private void createUser() {
        readFields();

        if (!isVerifiedInput()) {
            System.err.println("User could not be created!");
            return;

        }

        UserDTO user = new UserDTOImpl();
        user.setUserName(userNameValue);
        user.setPassword(password1Value);
        user.setEmail(emailValue);
        user.setRole("User");

        userControl.saveUser(user);

        Notification.show("User created!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        UI.getCurrent().navigate("login");
    }

    private void readFields() {
        userNameValue = userName.getValue();
        emailValue = eMail.getValue();
        password1Value = passwordField1.getValue();
        password2Value = passwordField2.getValue();

    }

    private boolean isVerifiedInput() {
        boolean nothingNull = !userNameValue.isEmpty() && !emailValue.isEmpty() && !password1Value.isEmpty() && !password2Value.isEmpty();


        if (!nothingNull) {
            Notification.show("Inputs must not be empty!").addThemeVariants(NotificationVariant.LUMO_ERROR);
            return false;
        }
        boolean passwordsMatch = password1Value.equals(password2Value);
        if (!passwordsMatch) {
            Notification.show("Your Passwords must Match!").addThemeVariants(NotificationVariant.LUMO_ERROR);
            return false;
        }
        return true;
    }


}
