package org.pollbox.poll.home;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.pollbox.conf.locale.LocaleUtil;
import org.pollbox.poll.accounts.Account;
import org.pollbox.poll.owners.Owner;
import org.pollbox.poll.auth.SecurityUtil;
import org.pollbox.poll.auth.User;
import org.pollbox.poll.managers.ServiceManager;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class HomeController {
    private final ServiceManager serviceManager;
    private final Jaxb2Marshaller marshaller;

    @Inject
    public HomeController(ServiceManager serviceManager, Jaxb2Marshaller marshaller) {
        this.serviceManager = serviceManager;
        this.marshaller = marshaller;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getOwners(Model model, HttpServletRequest request) {
        User user = SecurityUtil.getAuthenticatedUser();
        
        Owner owner = null;
        Account account = null;
        
        if (user != null) {
            owner = user.getOwner();
            account = owner.getAccount();
        }
        
        model.addAttribute("owner", owner);
        model.addAttribute("account", account);
        model.addAllAttributes(LocaleUtil.getLanguagesAttributes(owner, request));
        
        return "home";
    }

}
