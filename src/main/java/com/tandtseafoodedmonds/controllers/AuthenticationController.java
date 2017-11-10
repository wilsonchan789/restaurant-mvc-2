package com.tandtseafoodedmonds.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.tandtseafoodedmonds.models.User;
import com.tandtseafoodedmonds.models.data.UserDao;
import com.tandtseafoodedmonds.models.forms.LoginForm;
import com.tandtseafoodedmonds.models.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by LaunchCode
 */
@Controller
public class AuthenticationController extends AbstractController {

    @Autowired
    protected UserDao userDao;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String displayRegister(HttpServletRequest request, Model model) {
        model.addAttribute("sessionOn", isSessionActive(request.getSession()));
        model.addAttribute("title", "Register");
        model.addAttribute("registerForm", new RegistrationForm());
        return "user/register";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLogin(Model model, HttpServletRequest request) {
        model.addAttribute("sessionOn", isSessionActive(request.getSession()));
        model.addAttribute("title", "Login");
        model.addAttribute("loginForm", new LoginForm());
        return "user/login";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute @Valid RegistrationForm registrationForm, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            model.addAttribute("sessionOn", isSessionActive(request.getSession()));
            return "user/register";
        }

        User existUser = userDao.findByUsername(registrationForm.getUsername());

        if (existUser != null) {
            model.addAttribute("existingUsername", "Username already exists");
            model.addAttribute("title", "Register");
            model.addAttribute("sessionOn", isSessionActive(request.getSession()));
            return "user/register";
        }
        String verifyError = "";
        if (registrationForm.getVerifyPassword().equals(registrationForm.getPassword())) {
            User newUser = new User(registrationForm.getUsername(), registrationForm.getPassword());
            userDao.save(newUser);
            setUserInSession(request.getSession(), newUser);
            model.addAttribute("title", "Welcome " + newUser.getUsername());
            return "redirect:/user/index2";
        } else {
            verifyError = "Please enter a matching Password";
            model.addAttribute("title", "Register");
            model.addAttribute("verifyError", verifyError);
            model.addAttribute("sessionOn", isSessionActive(request.getSession()));
            return "user/register";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(Model model, @ModelAttribute @Valid LoginForm loginForm, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Login");
            model.addAttribute("sessionOn", isSessionActive(request.getSession()));
            return "user/login";
        }

        User user = userDao.findByUsername(loginForm.getUsername());
        String password = loginForm.getPassword();

        if (user == null) {
            model.addAttribute("usernameError", "Invalid username! Please try again!");
            model.addAttribute("sessionOn", isSessionActive(request.getSession()));
            model.addAttribute("title", "Login");
            return "user/login";
        }

        if (!user.isMatchingPassword(password)) {
            model.addAttribute("passwordError", "Wrong password! Please try again!");
            model.addAttribute("sessionOn", isSessionActive(request.getSession()));
            model.addAttribute("title", "Login");
            return "user/login";
        }

        model.addAttribute("title", "Welcome " + user.getUsername());
        setUserInSession(request.getSession(), user);
        return "redirect:/user/index2";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }
}