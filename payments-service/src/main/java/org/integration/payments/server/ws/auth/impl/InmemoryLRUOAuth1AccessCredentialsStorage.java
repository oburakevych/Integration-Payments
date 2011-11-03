package org.integration.payments.server.ws.auth.impl;

import java.util.Map;
import java.util.UUID;

import org.integration.payments.server.ws.auth.OAuth1AccessCredentials;

public class InmemoryLRUOAuth1AccessCredentialsStorage extends InmemoryLRUCredentialsStorage<OAuth1AccessCredentials> {
	public InmemoryLRUOAuth1AccessCredentialsStorage() {
		super();
	}

	public InmemoryLRUOAuth1AccessCredentialsStorage(int maxCacheSize) {
		super(maxCacheSize);
	}

	public InmemoryLRUOAuth1AccessCredentialsStorage(int maxCacheSize, Map<UUID, OAuth1AccessCredentials> initCredentials) {
		super(maxCacheSize, initCredentials);
	}
}
