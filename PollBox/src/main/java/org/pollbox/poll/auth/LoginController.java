package org.pollbox.poll.auth;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.pollbox.conf.locale.LocaleUtil;
import org.pollbox.poll.managers.ServiceManager;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@Controller
//@RequestMapping("/login")
//TODO: implement custome login controller
public class LoginController {
    private final ServiceManager serviceManager;
    private final Jaxb2Marshaller marshaller;

    @Inject
    public LoginController(ServiceManager serviceManager, Jaxb2Marshaller marshaller) {
        this.serviceManager = serviceManager;
        this.marshaller = marshaller;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getLoginForm(Model model, HttpServletRequest request) {
        model.addAllAttributes(LocaleUtil.getLanguagesAttributes(null, request));
        
        return "forward:/j_spring_security_check";
    }
}
