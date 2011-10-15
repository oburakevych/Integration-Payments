package org.pollbox.poll.signup;

import org.pollbox.poll.accounts.Account;
import org.pollbox.poll.owners.Owner;
import org.pollbox.poll.auth.Authority;


public class Signup {
    private Owner owner;
    private Account account;
    private Authority authority;
    
    public Signup() {}
    
    public Signup(Owner owner, Account account, Authority authority) {
        this.owner = owner;
        this.account = account;
        this.authority = authority;
    }
    
    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public Authority getAuthority() {
        return authority;
    }
    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

}
