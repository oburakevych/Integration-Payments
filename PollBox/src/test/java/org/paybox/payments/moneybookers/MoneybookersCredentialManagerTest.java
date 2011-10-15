package org.paybox.payments.moneybookers;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.paybox.oauth.OAuthCredentialManager;
import org.paybox.oauth.OAuthCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MoneybookersCredentialManagerTest {
    @Autowired private MoneyBookersCredentialManager credentialManager;
    private static final UUID TEST_ACCOUNT_ID = UUID.fromString("10ef0b35-7f42-42d0-a9e3-c2e4e7c4e504"); 
    
    @Test
    public void can_retrieve_credentials() {
        OAuthCredentials credentials = credentialManager.retrieve(TEST_ACCOUNT_ID);
        
        assertNotNull(credentials);
    }
}
