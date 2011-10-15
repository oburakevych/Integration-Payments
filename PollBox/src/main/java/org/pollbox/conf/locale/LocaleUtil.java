package org.pollbox.conf.locale;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.LocaleUtils;
import org.pollbox.conf.language.Language;
import org.pollbox.conf.language.LanguageDef;
import org.pollbox.poll.auth.SecurityUtil;
import org.pollbox.poll.auth.User;
import org.pollbox.poll.owners.Owner;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

public class LocaleUtil extends LocaleUtils {
    static java.util.logging.Logger log = java.util.logging.Logger.getLogger("LocaleUtil.class");

    /*
     * Default constructor. It should be never called. All methods of the utility class are statics.
     */
    private LocaleUtil() {}
    
    public static Locale getAuthUserLocale() {
        Locale locale = null;
        
        User user = SecurityUtil.getAuthenticatedUser();
        
        if (user != null) {
           locale = getLocale(user);
        }
        
        return locale;
    }
    
    public static Locale getLocale(User user) {
        Locale locale = null;
        
        if (user != null) {
           locale = user.getLocale();
        }
        
        return locale;
    }
    
    public static Locale getLocale(Owner owner) {
        Locale locale = null;
        
        if (owner != null) {
            Language lang = owner.getLanguage();
            
            if (lang != null) {
                locale = new Locale(lang.getCode());
            }
        }
        
        return locale;
    }

    public static Locale getLocale(HttpServletRequest request) {
        Locale locale = null;
        
        if (request != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            
            locale = null;
            
            if (localeResolver != null) {
                locale = localeResolver.resolveLocale(request);
            }
        }
        
        return locale;
    }
    
    public static Map<String, Object> getLanguagesAttributes(Owner owner, HttpServletRequest request) {
        Map<String, Object> attribs = new HashMap<String, Object>();
        
        Locale locale = getLocale(owner);
        
        if (locale == null) {
            locale = getLocale(request);
        }
        
        if (locale != null) {
            attribs.put("selectedLangCode", locale.getLanguage());
        }
        
        attribs.put("languages", LanguageDef.getLanguages());
        
        return attribs;
    }
}
