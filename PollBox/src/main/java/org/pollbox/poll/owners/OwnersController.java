package org.pollbox.poll.owners;

import java.util.Collection;
import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Valid;

import org.pollbox.conf.language.LanguageDef;
import org.pollbox.poll.accounts.Account;
import org.pollbox.poll.auth.Authority;
import org.pollbox.poll.auth.Role;
import org.pollbox.poll.auth.SecurityUtil;
import org.pollbox.poll.auth.User;
import org.pollbox.poll.managers.ServiceManager;
import org.pollbox.poll.signup.Signup;
import org.pollbox.poll.statuses.Status;
import org.pollbox.poll.statuses.StatusDef;
import org.pollbox.poll.statuses.Type;
import org.springframework.context.MessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@RequestMapping("/owners")
@SessionAttributes(types = {Owner.class}, value = "accountStatuses") 
public class OwnersController {
    private final ServiceManager serviceManager;
    private final Jaxb2Marshaller marshaller;

    @Inject
    public OwnersController(ServiceManager serviceManager, Jaxb2Marshaller marshaller) {
        this.serviceManager = serviceManager;
        this.marshaller = marshaller;
    }
/*    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new OwnerValidator());
    }
*/

    @RequestMapping(method = RequestMethod.GET)
    public String getOwners(Model model) {
        OwnerList ownerList = serviceManager.getOwnersService().getOwners();

        model.addAttribute("ownerList", ownerList);

        return "owners";
    }

    @RequestMapping(value = "/{ownerId}", method = RequestMethod.GET)
    public String getOwner(@PathVariable Long ownerId, Model model) {
        Owner owner = serviceManager.getOwnersService().getOwnerById(ownerId);
        
        Collection<StatusDef> accountStatuses = StatusDef.getStatuses(Type.ACCOUNT);

        model.addAttribute("owner", owner);
        model.addAttribute("accountStatuses", accountStatuses);

        return "owner";
    }
    
    @RequestMapping(value = "/{ownerId}/update", method = {RequestMethod.GET})
    public String getEditOwnerForm(@PathVariable Long ownerId, Model model) {
        User user = SecurityUtil.getAuthenticatedUser();
        
        Owner owner = serviceManager.getOwnersService().getOwnerByUsername(user.getUsername());
        
        Collection<StatusDef> accountStatuses = StatusDef.getStatuses(Type.ACCOUNT);

        model.addAttribute("owner", owner);
        model.addAttribute("accountStatuses", accountStatuses);
        model.addAttribute("languages", LanguageDef.getLanguages());

        return "owners.update";
    }

    @RequestMapping(value = "/{ownerId}", method = {RequestMethod.PUT})
    public String updateOwner(@PathVariable Long ownerId, @Valid Owner owner, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "owners.update";
        }

        serviceManager.getOwnersService().save(owner);

        return "owner";
    }
    
    @RequestMapping(value = "/new", method = {RequestMethod.GET})
    public String getNewOwnerForm(Model model) {
        User user = SecurityUtil.getAuthenticatedUser();
        
        Account account = serviceManager.getAccountsService().getAccountById(user.getAccountId());
        
        Owner owner = new Owner();
        
        owner.setAccount(account);
        
        model.addAttribute("isSignup", false);
        model.addAttribute("owner", owner);
        model.addAttribute("roles", Role.getRoles());
        model.addAttribute("languages", LanguageDef.getLanguages());

        return "owners.new";
    }
    
    @RequestMapping(value = "/new", method = {RequestMethod.POST})
    public String addOwner(@Valid Owner owner, BindingResult result, SessionStatus status) {
        if (result.hasErrors()) {
            return "owner";
        }
        
        Account account = owner.getAccount();
        Authority authority = owner.getAuthority();

        Signup signup = serviceManager.getSignupService().bindSignupData(owner, account, authority);
        signup = serviceManager.getSignupService().signupUser(signup);

        return "redirect:/account";
    }

    @RequestMapping(value = "/{ownerId}", method = RequestMethod.DELETE)
    public String deleteOwner(@PathVariable Long ownerId, Model model) {
        Owner owner = serviceManager.getOwnersService().getOwnerById(ownerId);

        if (owner != null) {
            serviceManager.getOwnersService().delete(owner);
        }

        OwnerList ownerList = serviceManager.getOwnersService().getOwners();

        model.addAttribute("ownerList", ownerList);

        return "owners";
    }
}
