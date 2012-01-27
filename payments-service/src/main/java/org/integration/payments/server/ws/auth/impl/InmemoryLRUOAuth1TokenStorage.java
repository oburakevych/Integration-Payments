package org.integration.payments.server.ws.auth.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.social.oauth1.OAuthToken;

public class InmemoryLRUOAuth1TokenStorage extends InmemoryLRUCredentialsStorage<OAuthToken> {
	public InmemoryLRUOAuth1TokenStorage() {
		super();
	}

	public InmemoryLRUOAuth1TokenStorage(int maxCacheSize) {
		super(maxCacheSize);
	}

	public InmemoryLRUOAuth1TokenStorage(int maxCacheSize, Map<UUID, OAuthToken> initCredentials) {
		super(maxCacheSize, initCredentials);
	}
}
