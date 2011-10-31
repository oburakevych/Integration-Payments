package org.integration.payments.server.document;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.integration.payments.server.document.ubl.canonical.Canonical;
import org.integration.payments.server.util.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


@ContextConfiguration(locations={"/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentServiceTest {
    private static final Logger log = LoggerFactory.getLogger(DocumentServiceTest.class);
    
    private @Autowired DocumentService documentService;
    
    @Test
    public void canonical_document_xpath_can_be_validated() throws IOException, SAXException, ParserConfigurationException {
        Document xmlDoc = getXmlDomDocumentRepresentation("src/test/resources/ubl_invoice.xml");
        
        assertEquals("12701", Canonical.NUMBER.getUblString(xmlDoc));
        assertEquals("5002701", Canonical.BUYERS_ORDER_ID.getUblString(xmlDoc));
        assertEquals("Tradeshift", Canonical.RECEIVER_PARTY_NAME.getUblString(xmlDoc));
        assertEquals("DK", Canonical.RECEIVER_COUNTRY_CODE.getUblString(xmlDoc));
        assertEquals("250.00", Canonical.PAYABLE_AMOUNT.getUblString(xmlDoc));
        assertEquals("DKK", Canonical.CURRENCY_CODE.getUblString(xmlDoc));
    }
    
    @Test
    public void ubl_can_be_converted_to_invoice() throws IOException {
        byte[] xml = IOUtils.getResourceAsByteArray("src/test/resources/ubl_invoice.xml");
        
        assertNotNull(xml);
        
        Invoice invoice = documentService.convertToInvoice(xml);
        
        assertNotNull(invoice);
        assertNotNull(invoice.getContent());
    }
    
    @Test
    public void invoice_can_be_converted_to_ubl() throws IOException {
        byte[] xml = IOUtils.getResourceAsByteArray("src/test/resources/ubl_invoice.xml");
        
        assertNotNull(xml);
        
        Invoice invoice = documentService.convertToInvoice(xml);
        
        assertNotNull(invoice);
        assertNotNull(invoice.getContent());
        
        byte[] convertedXml = documentService.convertToXml(invoice);
        
        assertNotNull(convertedXml);
    }
    
    @Test
    public void invoice_can_be_validated() throws IOException {
        byte[] xml = IOUtils.getResourceAsByteArray("src/test/resources/ubl_invoice.xml");

        Invoice invoice = documentService.convertToInvoice(xml);
        
        assertNotNull(invoice);
        assertNotNull(invoice.getContent());
        
        assertNotNull(invoice.getContent().getID());
        assertEquals("12701", invoice.getContent().getID().getValue());
        
        assertNotNull(invoice.getContent().getOrderReference());
        assertNotNull(invoice.getContent().getOrderReference().getID());
        assertEquals("5002701", invoice.getContent().getOrderReference().getID().getValue());
        
        assertNotNull(invoice.getContent().getAccountingCustomerParty());
        assertNotNull(invoice.getContent().getAccountingCustomerParty().getParty());
        assertNotNull(invoice.getContent().getAccountingCustomerParty().getParty().getPartyName());
        assertTrue(invoice.getContent().getAccountingCustomerParty().getParty().getPartyName().size() > 0);
        assertNotNull(invoice.getContent().getAccountingCustomerParty().getParty().getPartyName().get(0).getName());
        assertEquals("Tradeshift", invoice.getContent().getAccountingCustomerParty().getParty().getPartyName().get(0).getName().getValue());
        
        assertNotNull(invoice.getContent().getAccountingCustomerParty().getParty().getPostalAddress());
        assertNotNull(invoice.getContent().getAccountingCustomerParty().getParty().getPostalAddress().getCountry());
        assertNotNull(invoice.getContent().getAccountingCustomerParty().getParty().getPostalAddress().getCountry().getIdentificationCode());
        assertEquals("DK", invoice.getContent().getAccountingCustomerParty().getParty().getPostalAddress().getCountry().getIdentificationCode().getValue());
        
        assertNotNull(invoice.getContent().getLegalMonetaryTotal());
        assertNotNull(invoice.getContent().getLegalMonetaryTotal().getPayableAmount());
        assertEquals(new BigDecimal("250.00"), invoice.getContent().getLegalMonetaryTotal().getPayableAmount().getValue());
        
        assertNotNull(invoice.getContent().getDocumentCurrencyCode());
        assertEquals("DKK", invoice.getContent().getDocumentCurrencyCode().getValue());
    }
    
    public void application_response_can_be_validated() {
        
    }
    
    public void ubl_can_be_converted_to_application_response() {
        
    }
    
    public void application_response_can_be_converted_to_document() {
        
    }
    
    private Document getXmlDomDocumentRepresentation(String path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        DocumentBuilder builder = newInstance.newDocumentBuilder();
        Document doc = builder.parse(IOUtils.toInputStream(new File("src/test/resources/ubl_invoice.xml")));
        
        return doc;
    }
}
