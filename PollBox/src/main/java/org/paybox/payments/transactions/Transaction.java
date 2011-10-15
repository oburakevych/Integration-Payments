package org.paybox.payments.transactions;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.paybox.payments.account.CompanyAccount;
import org.pollbox.poll.accounts.Account;

@Entity
@Table(name = "TRANSACTION")
@XmlRootElement(name = "transaction", namespace = "http://paybox.org/payments/model")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private CompanyAccount companyAccount;
    private String amount;
    private String currency;

    private Calendar paymentDate;
    private Calendar captureDate;
    private String status;
    private UUID invoiceId;
    private String reference;
    private String orderId;
    private String message;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @ManyToOne(targetEntity = CompanyAccount.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPANY_ACCOUNT_ID")
    @XmlTransient
    public CompanyAccount getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(CompanyAccount companyAccount) {
        this.companyAccount = companyAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Calendar paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(Calendar captureDate) {
        this.captureDate = captureDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
