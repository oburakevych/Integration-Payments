package org.integration.payments.server.ws.dropbox.impl;

import static org.integration.payments.server.ws.dropbox.DropboxApiConstants.*;
import static org.integration.payments.server.ws.tradeshift.TradeshiftApiConstants.TENANTID_HEADER_NAME;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.integration.connectors.dropbox.account.DropboxUserProfile;
import org.integration.connectors.dropbox.files.DropboxFile;
import org.integration.connectors.dropbox.files.Entry;
import org.integration.payments.server.ws.dropbox.DropboxApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestOperations;

public class DropboxApiServiceImpl implements DropboxApiService {
	private static final List<Charset> ACCEPTABLE_CHARSETS = Arrays.asList(Charset.forName(DEFAULT_CHARSET));

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private final String apiBaseUrl;
	private final String apiContentBaseUrl; 
	private final RestOperations restOperations;
	private Map<String, String> defultRequestHeaders;

	public DropboxApiServiceImpl(String apiBaseUrl, String apiContentBaseUrl, RestOperations restOperations) {
		this.apiBaseUrl = apiBaseUrl;
		this.apiContentBaseUrl = apiContentBaseUrl;
		this.restOperations = restOperations;

		log.info("Dropbox Api service have been successfully initialized: [apiBaseUrl: {}, defultRequestHeaders: {}]" + apiBaseUrl, defultRequestHeaders);
	}

	public void setDefultRequestHeaders(Map<String, String> defultRequestHeaders) {
		this.defultRequestHeaders = defultRequestHeaders;
	}

    @Override
    public DropboxUserProfile getUserProfile(UUID companyAccountId) {
        Map<String, String> headers = new HashMap<String, String>(defultRequestHeaders);
        
        headers.put(TENANTID_HEADER_NAME, companyAccountId.toString());

        HttpHeaders httpHeaders = buildHttpHeaders(headers, MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeaders);

        ResponseEntity<DropboxUserProfile> responseEntity = this.restOperations.exchange(apiBaseUrl + "account/info", HttpMethod.GET, requestEntity, DropboxUserProfile.class);

        return responseEntity.getBody();
    }

    @Override
    public Entry getMetadataEntry(UUID companyAccountId, String root, String path) {
        Map<String, String> headers = new HashMap<String, String>(defultRequestHeaders);
        
        headers.put(TENANTID_HEADER_NAME, companyAccountId.toString());

        HttpHeaders httpHeaders = buildHttpHeaders(headers, MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeaders);

        ResponseEntity<Entry> responseEntity = this.restOperations.exchange(apiBaseUrl + "metadata/{root}/{path}", HttpMethod.GET, requestEntity, Entry.class, root, encode(path));

        return responseEntity.getBody();
    }
    
    @Override
    public Entry mkDir(UUID companyAccountId, String root, String path) {
        Map<String, String> headers = new HashMap<String, String>(defultRequestHeaders);
        
        headers.put(TENANTID_HEADER_NAME, companyAccountId.toString());

        HttpHeaders httpHeaders = buildHttpHeaders(headers, MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeaders);

        ResponseEntity<Entry> responseEntity = this.restOperations.exchange(apiBaseUrl + "fileops/create_folder?root={root}&path={path}", HttpMethod.POST, requestEntity, Entry.class, root, encode(path));
        
        return responseEntity.getBody();
    }
    
    @Override
    public DropboxFile getFile(UUID companyAccountId, String root, String path) {
        Map<String, String> headers = new HashMap<String, String>(defultRequestHeaders);
        
        headers.put(TENANTID_HEADER_NAME, companyAccountId.toString());

        HttpHeaders httpHeaders = buildHttpHeaders(headers, MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeaders);

        ResponseEntity<DropboxFile> responseEntity = this.restOperations.exchange(apiContentBaseUrl + "files/{root}/{path}", HttpMethod.GET, requestEntity, DropboxFile.class, root, encode(path));

        return responseEntity.getBody();
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
    
    public static String encode(String input) {
        StringBuilder resultStr = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (isUnsafe(ch)) {
                resultStr.append('%');
                resultStr.append(toHex(ch / 16));
                resultStr.append(toHex(ch % 16));
            } else {
                resultStr.append(ch);
            }
        }
        return resultStr.toString();
    }

    private static char toHex(int ch) {
        return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
    }

    private static boolean isUnsafe(char ch) {
        if (ch > 128 || ch < 0)
            return true;
        return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
    }
    
    public static void main(String args[]) {
        String path = "/outbox/test.xml";
        
        System.out.println(encode("https://api-content.dropbox.com/1/files/dropbox/") + encode(path));
    }

}
