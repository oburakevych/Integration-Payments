package org.paybox.payments.transactions;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "transactionList")
public class TransactionList {
    private int count;
    private Collection<Transaction> transactions;
    
    public TransactionList() {}
    
    public TransactionList(Collection<Transaction> transaction) {
        setTransactions(transaction);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElement(name = "transaction")
    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<Transaction> transactions) {
        this.transactions = transactions;
        this.count = transactions == null ? 0 : transactions.size();         
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        count = transactions.size();
    }
}
