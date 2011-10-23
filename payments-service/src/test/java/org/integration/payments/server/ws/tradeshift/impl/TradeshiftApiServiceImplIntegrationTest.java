package org.integration.payments.server.ws.tradeshift.impl;

import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import org.integration.payments.server.ws.tradeshift.TradeshiftApiService;
import org.integration.payments.server.ws.tradeshift.dto.AppSettings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.unitils.reflectionassert.ReflectionComparatorMode;

@ContextConfiguration(locations={"/test-applicationContext-ws.xml"})
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
}
