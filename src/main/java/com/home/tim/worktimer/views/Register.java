package com.home.tim.worktimer.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Register")
@Route(value = "register")
public class Register extends VerticalLayout {


    FormLayout formLayout;

    private TextField userName;
    private TextField eMail;
    private PasswordField passwordField1;
    private PasswordField passwordField2;
    private Button register;
    public Register(){

        formLayout = new FormLayout();

        userName = new TextField("Username:");
        eMail = new TextField("E-Mail:");
        passwordField1 = new PasswordField("Enter your Password please:");
        passwordField2 = new PasswordField("Please confirm your Password:");

        register = new Button("Register");
        formLayout.setColspan(register,2);
        register.addClickListener(click -> UI.getCurrent().navigate("Hello-World"));
        register.addClassName("register-button");


        formLayout.add(userName,eMail,passwordField1,passwordField2);
        formLayout.addClassName("register-Form");
        add(formLayout,register);
        setAlignItems(Alignment.CENTER);





    }
}
