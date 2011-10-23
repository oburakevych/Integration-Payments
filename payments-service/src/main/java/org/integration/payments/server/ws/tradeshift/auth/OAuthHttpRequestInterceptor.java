package org.integration.payments.server.ws.tradeshift.auth;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
1 * Client out-bound request
1 > GET https://api-sandbox.tradeshift.com/tradeshift/rest/external/account/appsettings
1 > X-Tradeshift-TenantId: 12a76c27-2f28-4a1b-972d-6cc9dd30bf27
1 > User-Agent: TradeshiftRestExplorer/1.0
1 > Accept: application/json
1 > Authorization: 
OAuth 
oauth_signature="U76LPDMcv1WX67in%2B4RHNh8%2Bstc%3D", 
oauth_version="1.0", 
oauth_nonce="4a9bffde-bf27-4551-855c-a65450c2e68f", 
oauth_consumer_key="OwnAccount", 
oauth_signature_method="HMAC-SHA1", 
oauth_token="ZbvBvXQ2%2BS%40hF4PRRvMU4KzBcaFJEq", 
oauth_timestamp="1319235492"
1 > 
 */
public class OAuthHttpRequestInterceptor implements ClientHttpRequestInterceptor {
	

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {

		

		return execution.execute(request, body);
	}
}
