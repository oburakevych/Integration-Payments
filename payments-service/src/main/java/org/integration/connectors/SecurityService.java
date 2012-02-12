package org.integration.connectors;

import org.springframework.social.oauth1.OAuthToken;

public interface SecurityService {
    void save(String accountId, OAuthToken credentials);
    void update(String accountId, OAuthToken credentials);
    OAuthToken get(String accountId);
    void delete(String accountId);
    boolean exists(String accountId);
}
