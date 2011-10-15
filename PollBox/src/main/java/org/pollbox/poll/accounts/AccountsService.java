package org.pollbox.poll.accounts;

import org.pollbox.poll.owners.Owner;
import org.springframework.security.access.prepost.PreAuthorize;


public interface AccountsService {
    @PreAuthorize("hasAnyRole('ROLE_OWNER', 'ROLE_SUPERVISOR', 'ROLE_USER', 'ROLE_OPERATOR')")
    public Account getAccountById(Long id);

    public Account getAccount(Owner owner);

    public Account getAccount(String username);

    public void save(Account account);

    public void delete(Account account);

}
