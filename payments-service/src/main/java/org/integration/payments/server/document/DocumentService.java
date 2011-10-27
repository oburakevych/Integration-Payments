package org.integration.payments.server.document;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;


public class DocumentService {
    private Jaxb2Marshaller marshaller;
    
    public InvoiceType convertToInvoice(InputStream is) {
        InvoiceType invoiceType = null;
        
        try {
            invoiceType = (InvoiceType) marshaller.unmarshal(new StreamSource(is));
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        
        return invoiceType;
    }

    public void setMarshaller(Jaxb2Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Jaxb2Marshaller getMarshaller() {
        return marshaller;
    }
   
}
