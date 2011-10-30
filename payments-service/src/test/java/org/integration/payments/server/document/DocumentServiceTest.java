package org.integration.payments.server.document;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;
import oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory;

import org.integration.payments.server.document.ubl.canonical.Canonical;
import org.integration.payments.server.util.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import static org.junit.Assert.*;


@ContextConfiguration(locations={"/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentServiceTest {
    @Autowired DocumentService documentService;
    
    @Test
    public void document_can_be_validated() throws IOException, SAXException, ParserConfigurationException {
        Document xmlDoc = getXmlDomDocumentRepresentation("src/test/resources/ubl_invoice.xml");
        
        assertEquals("12701", Canonical.NUMBER.getUblString(xmlDoc));
        assertEquals("5002701", Canonical.BUYERS_ORDER_ID.getUblString(xmlDoc));
        assertEquals("Tradeshift", Canonical.RECEIVER_PARTY_NAME.getUblString(xmlDoc));
        assertEquals("DK", Canonical.RECEIVER_COUNTRY_CODE.getUblString(xmlDoc));
        assertEquals("250.00", Canonical.PAYABLE_AMOUNT.getUblString(xmlDoc));
        assertEquals("DKK", Canonical.CURRENCY_CODE.getUblString(xmlDoc));
    }
    
    @Test
    public void document_can_be_converted_to_invoice() throws IOException {
        byte[] xml = IOUtils.getResourceAsByteArray("src/test/resources/ubl_invoice.xml");
        
        InvoiceType invoice = documentService.convertToInvoice(xml);
    }
    
    @Test
    public void document_can_be_converted_to_xml() throws IOException {
        byte[] xml = IOUtils.getResourceAsByteArray("src/test/resources/ubl_invoice.xml");
        
        InvoiceType invoice = documentService.convertToInvoice(xml);
        
        byte[] convertedXml = documentService.convertToXml(invoice);
    }
    
    public void document_can_be_retrieved() {
        
    }
    
    public void document_profile_matches() {
        
    }
    
    public void document_can_be_converted_to_application_response() {
        
    }
    
    public void invoice_can_be_converted_to_ubl_document() {
        
    }
    
    public void application_response_can_be_converted_to_ubl_document() {
        
    }
    
    public void invoice_can_be_validated() {
        
    }
    
    public void application_response_can_be_validated() {
        
    }
    
    private Document getXmlDomDocumentRepresentation(String path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        DocumentBuilder builder = newInstance.newDocumentBuilder();
        Document doc = builder.parse(IOUtils.toInputStream(new File("src/test/resources/ubl_invoice.xml")));
        
        return doc;
    }
}
