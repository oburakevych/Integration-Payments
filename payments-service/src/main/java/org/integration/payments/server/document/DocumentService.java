package org.integration.payments.server.document;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;


public class DocumentService {
    private Jaxb2Marshaller marshaller;
    
    

    public void setMarshaller(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Jaxb2Marshaller getMarshaller() {
        return marshaller;
    }
   
}
