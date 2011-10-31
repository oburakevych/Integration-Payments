package org.integration.payments.server.document;

import oasis.names.specification.ubl.schema.xsd.invoice_2.InvoiceType;


public class Invoice extends Document {
    private InvoiceType content;

    @Override
    public InvoiceType getContent() {
        return content;
    }

    @Override
    public void setContent(Object content) {
        if (content instanceof InvoiceType) {
            this.content = (InvoiceType) content;
        }
    }

}
