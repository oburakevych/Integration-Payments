package org.integration.payments.server.ws.auth.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.map.LRUMap;
import org.integration.payments.server.ws.auth.CredentialsStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Inmemory LRU OAuthCredentials storage
 */
public class InmemoryLRUCredentialsStorage<T> implements CredentialsStorage<T> {

	private static final int DEFAULT_MAX_CACHE_SIZE = 10000;

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private LRUMap cache;

	public InmemoryLRUCredentialsStorage() {
		this(DEFAULT_MAX_CACHE_SIZE);
	}

	public InmemoryLRUCredentialsStorage(int maxCacheSize) {
		cache = new LRUMap(maxCacheSize);

		if (log.isInfoEnabled()) {
			log.info("Initialized LRUOAuth1AccessCredentialsStorage, maxCacheSize:" + maxCacheSize);
		}
	}

	public InmemoryLRUCredentialsStorage(int maxCacheSize, Map<UUID, T> initCredentials) {
		this(maxCacheSize);

		for (Map.Entry<UUID, T> credentialsEntry: initCredentials.entrySet()) {
			save(credentialsEntry.getKey(), credentialsEntry.getValue());
		}
	}

	@Override
	public T get(UUID uuid) {
		@SuppressWarnings("unchecked")
		T credentials = (T) cache.get(uuid);

		if (log.isTraceEnabled()) {
			log.trace("Retrivied credentials:{uuid:" + uuid + "credentials:" + credentials + "}");
		}

		return credentials;
	}

	@Override
	public void save(UUID uuid, T credentials) {
		cache.put(uuid, credentials);

		if (log.isTraceEnabled()) {
			log.trace("Saved credentials:{uuid:" + uuid + ", credentials:" + credentials + "}");
		}
	}

	@Override
	public void delete(UUID uuid) {
		cache.remove(uuid);

		if (log.isTraceEnabled()) {
			log.trace("Removed credentials for uuid:" + uuid);
		}
	}

	@Override
	public boolean exists(UUID uuid) {
		return cache.containsKey(uuid);
	}
	
	@Override
	public Set<UUID> getCachedKeys() {
	    return Collections.unmodifiableSet(cache.keySet());
	}
}
