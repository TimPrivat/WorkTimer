package com.home.tim.worktimer.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;

@PageTitle("Login")
@Route(value = "")
@RouteAlias(value = "login")
public class Login extends VerticalLayout {


    private LoginForm loginForm;
    public Login(){
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        add(loginForm);
        add(getRegisterButton());




    }

    private Div getRegisterButton(){

        Div register = new Div();
        RouterLink routerLink = new RouterLink("Create new Account", Register.class);

        register.add(routerLink);

        return register;


    }

    private LoginForm configureLoginForm(){
        loginForm = new LoginForm();
        loginForm.addLoginListener(loginEvent -> {



        });
        return loginForm;

    }



}
