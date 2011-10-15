package org.pollbox.poll.owners;

import java.util.Collection;


public interface OwnerDao {
	public Owner getOwnerById(Long id);
	
	public Owner getOwnerByUsername(String username);
	
	public Collection<Owner> getOwners();
		
	public void save(Owner owner);
	
	public void delete(Owner owner);

}
