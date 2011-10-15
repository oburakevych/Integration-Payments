package org.pollbox.poll.accounts;

import java.io.StringReader;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.pollbox.poll.owners.Owner;
import org.pollbox.poll.auth.SecurityUtil;
import org.pollbox.poll.auth.User;
import org.pollbox.poll.managers.ServiceManager;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/account")
public class AccountsController {
    private final ServiceManager serviceManager;
    private final Jaxb2Marshaller marshaller;
    private Logger log = Logger.getLogger(getClass().getName());

    @Inject
    public AccountsController(ServiceManager serviceManager, Jaxb2Marshaller marshaller) {
        this.serviceManager = serviceManager;
        this.marshaller = marshaller;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAccounts(Model model) {
        User user = SecurityUtil.getAuthenticatedUser();
        
        Account account = serviceManager.getOwnersService().getOwnerByUsername(user.getUsername()).getAccount();
        Owner owner = serviceManager.getOwnersService().getOwnerByUsername(user.getUsername());

        model.addAttribute("account", account);
        model.addAttribute("owner", owner);

        return "account";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addAccount(@RequestBody String body, Model model) {
        Source source = new StreamSource(new StringReader(body));

        Account account = (Account) marshaller.unmarshal(source);

        if (!SecurityUtil.isAccountAuthorisedForResource(account.getId())) {
            throw new AccessDeniedException("Current user has no rights to access this resorce");
        }
        
        serviceManager.getAccountsService().save(account);

        model.addAttribute("account", account);

        return "account";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateAccount(@RequestBody String body, Model model) {
        Source source = new StreamSource(new StringReader(body));

        Account account = (Account) marshaller.unmarshal(source);

        serviceManager.getAccountsService().save(account);
        
        if (!SecurityUtil.isAccountAuthorisedForResource(account.getId())) {
            throw new AccessDeniedException("Current user has no rights to access this resorce");
        }

        model.addAttribute("account", account);

        return "account";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteAccount(Model model) {
        User user = SecurityUtil.getAuthenticatedUser();
        
        Account account = serviceManager.getOwnersService().getOwnerByUsername(user.getUsername()).getAccount();

        if (!SecurityUtil.isAccountAuthorisedForResource(account.getId())) {
            throw new AccessDeniedException("Current user has no rights to access this resorce");
        }

        if (account != null) {
            serviceManager.getAccountsService().delete(account);
        }

        return "redirect:account";
    }
}
