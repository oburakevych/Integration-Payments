package org.integration.payments.server.ws.tradeshift.impl;

import static org.integration.payments.server.ws.tradeshift.TradeshiftApiConstants.DEFAULT_CHARSET;
import static org.integration.payments.server.ws.tradeshift.TradeshiftApiConstants.TENANTID_HEADER_NAME;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.integration.payments.server.ws.tradeshift.TradeshiftApiService;
import org.integration.payments.server.ws.tradeshift.dto.AppSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestOperations;

public class TradeshiftApiServiceImpl implements TradeshiftApiService {

	private static final List<Charset> ACCEPTABLE_CHARSETS = Arrays.asList(Charset.forName(DEFAULT_CHARSET));

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private final String apiBaseUrl;
	private final RestOperations restOperations;
	private Map<String, String> defultRequestHeaders;

	public TradeshiftApiServiceImpl(String apiBaseUrl, RestOperations restOperations) {
		this.apiBaseUrl = apiBaseUrl;
		this.restOperations = restOperations;

		log.info("Tradeshift Api service have been successfully initialized:{apiBaseUrl:" + apiBaseUrl 
			+ ", defultRequestHeaders:" + defultRequestHeaders + "}");
	}

	public void setDefultRequestHeaders(Map<String, String> defultRequestHeaders) {
		this.defultRequestHeaders = defultRequestHeaders;
	}

	@Override
	public AppSettings getAppSettings(UUID companyAccountId) {
		Map<String, String> headers = new HashMap<String, String>(defultRequestHeaders);

		headers.put(TENANTID_HEADER_NAME, companyAccountId.toString());

        HttpHeaders httpHeaders = buildHttpHeaders(headers, MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeaders);

		ResponseEntity<AppSettings> responseEntity = this.restOperations.exchange(apiBaseUrl + "/external/account/appsettings", HttpMethod.GET, requestEntity, AppSettings.class);

		return responseEntity.getBody();
	}

	/**
	 * external/consumer/accounts/{companyaccountid}/resendtoken
	 */
	@Override
	public void resendOAuthAccessToken(UUID companyAccountId) {
		HttpHeaders httpHeaders = buildHttpHeaders(defultRequestHeaders, MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeaders);

		this.restOperations.exchange(apiBaseUrl + "/external/consumer/accounts/{companyaccountid}/resendtoken", HttpMethod.POST, requestEntity, String.class, companyAccountId.toString());
	}

	// ~ utilities
    private static HttpHeaders buildHttpHeaders(Map<String, String> headers, MediaType acceptableMediaType) {
        HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.setAccept(Arrays.asList(acceptableMediaType));
        requestHeaders.setAcceptCharset(ACCEPTABLE_CHARSETS);

        if (!CollectionUtils.isEmpty(headers)) {
        	for (Map.Entry<String, String> headerEntry: headers.entrySet()) {
        		requestHeaders.set(headerEntry.getKey(), headerEntry.getValue());
        	}
        }

        return requestHeaders;
    }

    @Override
    public byte[] getDocument(UUID companyAccountId, UUID documentId, String locale) {
    	Map<String, String> headers = new HashMap<String, String>(defultRequestHeaders);

		headers.put(TENANTID_HEADER_NAME, companyAccountId.toString());

        HttpHeaders httpHeaders = buildHttpHeaders(headers, MediaType.TEXT_XML);
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeaders);

        ResponseEntity<byte[]> responseEntity = this.restOperations.exchange(apiBaseUrl + "/external/documents/{documentId}", HttpMethod.GET, requestEntity, byte[].class, documentId.toString());

        return responseEntity.getBody();
    }
}
