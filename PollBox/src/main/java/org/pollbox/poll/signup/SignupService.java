package org.pollbox.poll.signup;

import org.pollbox.poll.accounts.Account;
import org.pollbox.poll.owners.Owner;
import org.pollbox.poll.auth.Authority;


public interface SignupService {
    public Signup bindSignupData(Owner owner, Account account, Authority authority);
    public Signup signupUser(Signup signup);
    
}
