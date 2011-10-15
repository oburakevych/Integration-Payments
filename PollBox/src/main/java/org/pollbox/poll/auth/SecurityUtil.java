package org.pollbox.poll.auth;

import org.pollbox.poll.owners.Owner;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class SecurityUtil {
    static java.util.logging.Logger log = java.util.logging.Logger.getLogger("SecurityUtil.class");

    /*
     * Default constructor. It should be never called. All methods of the utility class are statics.
     */
    private SecurityUtil() {
    }
    
    public static boolean isAccountAuthorisedForResource(Long accountId) {
       return authoriseAccountForResource(accountId) == AccessDecisionVoter.ACCESS_GRANTED;
    }
    
    public static boolean isUsernameAuthorisedForResource(String username) {
        return authoriseUsernameForResource(username) == AccessDecisionVoter.ACCESS_GRANTED;
     }
    
    public static boolean isOwnerAuthorisedForResource(Owner owner) {
        return authoriseOwnerForResource(owner) == AccessDecisionVoter.ACCESS_GRANTED;
     }
    
    public static User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = null;

        if (principal instanceof User) {
            user = (User) principal;
        }
        
        return user;
    }

    private static int authoriseAccountForResource(Long accountId) {
        User user = getAuthenticatedUser();
        
        return authoriseUserForResource(user, accountId);
    }
    
    private static int authoriseOwnerForResource(Owner owner) {
        User user = getAuthenticatedUser();
        
        return authoriseUserForResource(user, owner.getAccount().getId());
    }
    
    private static int authoriseUsernameForResource(String username) {
        User user = getAuthenticatedUser();
        
        return authoriseUsernameForResource(user, username);
    }
    
    private static int authoriseUserForResource(User user, Long accountId) {
        int result = AccessDecisionVoter.ACCESS_ABSTAIN;

        if (user == null) {
            throw new UsernameNotFoundException("No authenticated principal object exists");
        }

        if (accountId.equals(user.getAccountId())) {
            result = AccessDecisionVoter.ACCESS_GRANTED;
        } else {
            result = AccessDecisionVoter.ACCESS_DENIED;
        }

        return result;
    }

    private static int authoriseUsernameForResource(User user, String username) {
        int result = AccessDecisionVoter.ACCESS_ABSTAIN;

        if (user == null) {
            throw new UsernameNotFoundException("No authenticated principal object exists");
        }

        if (username.equals(user.getUsername())) {
            result = AccessDecisionVoter.ACCESS_GRANTED;
        } else {
            result = AccessDecisionVoter.ACCESS_DENIED;
        }

        return result;
    }

}
