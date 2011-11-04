package org.integration.payments.server.ws.auth;

import java.util.UUID;

public interface CredentialsStorage<T> {
	public T get(UUID uuid);

	public void save(UUID uuid, T credentials);

	public void delete(UUID uuid);

	public boolean isExists(UUID uuid);
}
