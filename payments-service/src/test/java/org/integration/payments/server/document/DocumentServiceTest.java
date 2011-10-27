package org.integration.payments.server.document;

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
    
    @Test
    @Ignore
    public void document_can_be_validated() {
        documentService.convertToInvoice(null);
        System.out.println("YO!");
    }
    
    public void document_profile_matches() {
        
    }
    
    public void document_can_be_converted_to_invoice() {
        
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
