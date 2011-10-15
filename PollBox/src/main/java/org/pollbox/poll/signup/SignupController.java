package org.pollbox.poll.signup;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.pollbox.conf.language.Language;
import org.pollbox.conf.language.LanguageDef;
import org.pollbox.poll.accounts.Account;
import org.pollbox.poll.owners.Owner;
import org.pollbox.poll.auth.Authority;
import org.pollbox.poll.auth.Role;
import org.pollbox.poll.managers.ServiceManager;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@RequestMapping("/signup")
@SessionAttributes(types = Authority.class)
public class SignupController {
    private final ServiceManager serviceManager;
    private final Jaxb2Marshaller marshaller;

    
    private Logger log = Logger.getLogger(getClass().getName());

    @Inject
    public SignupController(ServiceManager serviceManager, Jaxb2Marshaller marshaller) {
        this.serviceManager = serviceManager;
        this.marshaller = marshaller;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getSignupForm(Model model) {
        Owner owner = new Owner();
        Account account = new Account();
        Authority authority = new Authority();
        Language language = new Language(LanguageDef.getDefault());
        
        authority.setAuthority(Role.DEFAULT.getCode());
        
        owner.setAccount(account);
        owner.setAuthority(authority);
        owner.setLanguage(language);
        
        model.addAttribute("isSignup", true);
        model.addAttribute("owner", owner);
        model.addAttribute("default_role", Role.DEFAULT);
        model.addAttribute("languages", LanguageDef.getLanguages());
        
        return "signup";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String signup(@ModelAttribute Owner owner, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "signup";
        }
        
        Account account = owner.getAccount();
        Authority authority = owner.getAuthority();
        
        Signup signup = serviceManager.getSignupService().bindSignupData(owner, account, authority);
        signup = serviceManager.getSignupService().signupUser(signup);
        
        return "redirect:login";
    }
}
