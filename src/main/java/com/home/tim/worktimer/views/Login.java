package com.home.tim.worktimer.views;

import com.home.tim.worktimer.control.LoginControl;
import com.home.tim.worktimer.control.UserControl;
import com.home.tim.worktimer.dtos.UserDTO;
import com.vaadin.flow.component.UI;
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
import org.springframework.beans.factory.annotation.Autowired;

import static com.vaadin.flow.component.UI.getCurrent;

@PageTitle("Login")
@Route(value = "")
@RouteAlias(value = "login")
public class Login extends VerticalLayout {

    @Autowired
    LoginControl loginControl;
    @Autowired
    UserControl userControl;
    private LoginForm loginForm;

    public Login() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        loginForm = configureLoginForm();
        add(loginForm);
        add(getRegisterButton());


    }

    private Div getRegisterButton() {

        Div register = new Div();
        RouterLink routerLink = new RouterLink("Create new Account", Register.class);

        register.add(routerLink);

        return register;


    }

    private LoginForm configureLoginForm() {
        loginForm = new LoginForm();
        loginForm.addLoginListener(loginEvent -> {
            String userName = loginEvent.getUsername();
            String password = loginEvent.getPassword();

            if (loginControl.isRegisteredUser(userName, password)) {
                UserDTO currentUser = userControl.getUserByUsernameAndPassword(userName, password);
                UI.getCurrent().getSession().setAttribute("CurrentUser", currentUser);
                UI.getCurrent().navigate("Hello-World");
            } else {
                loginForm.showErrorMessage("Wrong LoginData", "The User could not be found");

            }

        });
        return loginForm;

    }


}
