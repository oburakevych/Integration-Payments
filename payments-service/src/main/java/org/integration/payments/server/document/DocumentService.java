package org.integration.payments.server.document;

import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;

import org.integration.payments.server.util.JAXBUtils;


public class DocumentService {
    public InvoiceType convertToInvoice(byte[] xmlDoc) {
        InvoiceType invoice = JAXBUtils.unmarshall(xmlDoc, InvoiceType.class);
        
        System.out.println("Yo!");
        
        return invoice;
    }
    
    public byte[] convertToXml(Object object) {
        byte[] xml =  JAXBUtils.marshall(object);
        
        return xml;
    }   
}
