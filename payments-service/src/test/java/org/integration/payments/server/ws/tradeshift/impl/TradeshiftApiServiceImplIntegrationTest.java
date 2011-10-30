package org.integration.payments.server.ws.tradeshift.impl;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.util.UUID;

import org.integration.payments.server.ws.tradeshift.TradeshiftApiService;
import org.integration.payments.server.ws.tradeshift.dto.AppSettings;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import static org.junit.Assert.*;



@ContextConfiguration(locations={"/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TradeshiftApiServiceImplIntegrationTest {
	@Autowired
	private TradeshiftApiService tradeshiftApiService;

	@Test
	public void getAppSettings() {
		AppSettings expectedAppSettings = new AppSettings();

		AppSettings actualAppSettings = tradeshiftApiService.getAppSettings();

		assertReflectionEquals(expectedAppSettings, actualAppSettings, new ReflectionComparatorMode[] {ReflectionComparatorMode.LENIENT_DATES});
	}
	
	@Test
	public void getDocument() {
	    UUID docId = UUID.fromString("2bf1c6a9-fa08-4cd7-969a-4c6bae072a33");
	    byte[] rawXml = tradeshiftApiService.getDocument(docId, null);
	    
	    assertNotNull(rawXml);
	}
}
