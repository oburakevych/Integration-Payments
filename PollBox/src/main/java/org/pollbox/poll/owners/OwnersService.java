package org.pollbox.poll.owners;

import org.pollbox.poll.auth.AuthUser;
import org.springframework.security.access.prepost.PreAuthorize;


public interface OwnersService {
    public Owner getOwnerById(Long id);

    public Owner getOwnerByUsername(String username);

    public OwnerList getOwners();

    public void save(Owner owner);

    public void delete(Owner owner);

}
