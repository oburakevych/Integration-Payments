package org.integration.payments.server.ws.tradeshift.impl;

import static org.junit.Assert.assertNotNull;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.io.IOException;
import java.util.UUID;

import org.integration.payments.server.document.Dispatch;
import org.integration.payments.server.document.DocumentMetadata;
import org.integration.payments.server.document.DocumentServiceTest;
import org.integration.payments.server.util.IOUtils;
import org.integration.payments.server.ws.tradeshift.TradeshiftApiService;
import org.integration.payments.server.ws.tradeshift.dto.AppSettings;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.unitils.reflectionassert.ReflectionComparatorMode;


@ContextConfiguration(locations={"/test-applicationContext-ws.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TradeshiftApiServiceImplIntegrationTest {
    private static final UUID DOCUMENT_ID = UUID.fromString("2bf1c6a9-fa08-4cd7-969a-4c6bae072a33"); 

	@Value("${tradeshift.api.tenantId}")
	private String tenantId;

	@Autowired
	private TradeshiftApiService tradeshiftApiService;

	@Test
	public void getAppSettings() {
		UUID companyAccountId = UUID.fromString(tenantId);

		AppSettings expectedAppSettings = new AppSettings();

		AppSettings actualAppSettings = tradeshiftApiService.getAppSettings(companyAccountId);

		assertReflectionEquals(expectedAppSettings, actualAppSettings, new ReflectionComparatorMode[] {ReflectionComparatorMode.LENIENT_DATES});
	}
	
	@Test
	public void getDocument() throws IOException {
		UUID companyAccountId = UUID.fromString(tenantId);
	    //TODO: pick up a DocumentID from a property file
	    UUID documentId = DOCUMENT_ID;

	    byte[] rawXml = tradeshiftApiService.getDocument(companyAccountId, documentId, null);

	    assertNotNull(rawXml);
	    
	    // Save the pulled file. It will be used by DocumentServiceTest tests
	    //TODO: take the resources path and test file names from a property file
	    IOUtils.writeToFile(DocumentServiceTest.RESOURCES_PATH + "/" + DocumentServiceTest.TEST_UBL_FILE_NAME, rawXml, false);
	}
	
	@Test
	@Ignore
	public void getDispatch() {
	    DocumentMetadata dispatch = tradeshiftApiService.getDocumentMetadata(DOCUMENT_ID);
	    
	    assertNotNull(dispatch);
	    
	}
}
