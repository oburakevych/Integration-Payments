package org.integration.payments.server.ws.auth;

public class OAuth1AccessCredentials {
	private final String accessToken;
	
	private final String accessTokenSecret;

	public OAuth1AccessCredentials(String accessToken, String accessTokenSecret) {
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");

		sb.append("accessToken:").append(accessToken);
		sb.append(", accessTokenSecret:").append(accessTokenSecret);

		return sb.append("}").toString();
	}
}
