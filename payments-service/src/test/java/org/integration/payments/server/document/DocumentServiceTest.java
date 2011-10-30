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

import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;
import oasis.names.specification.ubl.schema.xsd.invoice_2.ObjectFactory;

import org.integration.payments.server.util.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations={"/test-root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentServiceTest {
    @Autowired DocumentService documentService;
    
    public void document_can_be_validated() throws IOException {

    }
    
    @Test
    public void document_can_be_converted_to_invoice() throws IOException {
        InputStream is = new FileInputStream("src/test/resources/TSUBL_Invoice_01.xml");
        
        byte[] xml = IOUtils.getResourceAsByteArray(is);
        
        InvoiceType invoice = documentService.convertToInvoice(xml);
    }
    
    @Test
    public void document_can_be_converted_to_xml() throws IOException {
        InputStream is = new FileInputStream("src/test/resources/TSUBL_Invoice_01.xml");
        
        byte[] xml = IOUtils.getResourceAsByteArray(is);
        
        InvoiceType invoice = documentService.convertToInvoice(xml);
        
        byte[] convertedXml = documentService.convertToXml(invoice);
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
}
