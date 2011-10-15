package org.pollbox.poll.accounts;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "accountList")
public class AccountList {
    private int count;
    private Collection<Account> accounts;
    
    public AccountList() {}
    
    public AccountList(Collection<Account> accounts) {
        setAccounts(accounts);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElement(name = "account")
    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
        this.count = accounts == null ? 0 : accounts.size();         
    }
    
    public void addAccount(Account account) {
        accounts.add(account);
        count = accounts.size();
    }
}
