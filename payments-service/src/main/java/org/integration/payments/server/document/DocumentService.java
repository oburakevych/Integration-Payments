package org.integration.payments.server.document;

import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;

import org.integration.payments.server.util.JAXBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DocumentService {
    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);
    
    private DocumentFactory documentFactory;
    
    public Invoice convertToInvoice(byte[] xmlDoc) {
        InvoiceType invoiceType = JAXBUtils.unmarshall(xmlDoc, InvoiceType.class);
        
        Invoice invoice = documentFactory.newInstatnce(invoiceType);
        
        return invoice;
    }
    
    public byte[] convertToXml(Document document) {
        byte[] xml =  JAXBUtils.marshall(document.getContent());
        
        return xml;
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        this.documentFactory = documentFactory;
    }

    public DocumentFactory getDocumentFactory() {
        return documentFactory;
    }   
}
