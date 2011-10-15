package org.pollbox.poll.auth;


public interface AuthorityDao {
    public Authority getAuthority(Long id);
    public Authority getAuthority(String username);
    public Authority save(Authority authority);
    public void delete(Authority authority);
}
