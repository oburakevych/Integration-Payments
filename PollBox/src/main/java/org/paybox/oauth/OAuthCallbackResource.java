package org.paybox.oauth;

import java.util.UUID;

import javax.inject.Inject;

import org.paybox.payments.moneybookers.MoneyBookersCredentialManager;
import org.pollbox.poll.accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("oauth/callback")
public class OAuthCallbackResource {
    @Autowired private MoneyBookersCredentialManager moneyBookersCredentialManager;
    
    private final Jaxb2Marshaller marshaller;

    @Inject
    public OAuthCallbackResource(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }
    
    @RequestMapping(value = "moneybookers", method = RequestMethod.POST)
    public void activatedMoneybookers(@RequestParam("companyAccountId") UUID companyAccountId, @RequestParam("oauth_token") String token, @RequestParam("oauth_token_secret") String tokenSecret) {
        System.out.println("activatedMoneybookers called!!!");
        System.out.println("companyAccountId=" + companyAccountId);
        moneyBookersCredentialManager.store(companyAccountId, new OAuthCredentials(token, tokenSecret));
    }
    
    @RequestMapping(value = "moneybookers", method = RequestMethod.DELETE)
    public void deactivatedMoneybookers(@RequestParam("companyAccountId") UUID companyAccountId) {
        moneyBookersCredentialManager.remove(companyAccountId);
    }
    
    @RequestMapping(value = "moneybookers", method = RequestMethod.GET)
    public String getMoneyBookers(Model model) {
        System.out.println("activatedMoneybookers called!!!");
        
        
        
        model.addAttribute("account", new Account());
        
        return "actmoneybookers";
    }
}
