package org.pollbox.poll.accounts;

import org.pollbox.poll.owners.Owner;


public interface AccountDao {
	public Account getAccountById(Long id);
	
	public Account getAccount(Owner owner);
	
	public Account getAccount(String username);

	public void save(Account account);
	
	public void delete(Account account);
}
