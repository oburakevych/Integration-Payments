package org.pollbox.conf.locale;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.pollbox.conf.language.Language;
import org.pollbox.poll.auth.SecurityUtil;
import org.pollbox.poll.auth.User;
import org.pollbox.poll.owners.Owner;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.WebUtils;


public class LocaleResolver extends CookieLocaleResolver {
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = null;
        
        // Use locale from the language of the registered user by default
        locale = getOwnersLocale();
        
        if (locale != null ) {
            return locale;
        }
        
        // Check request for pre-parsed or preset locale.
        locale = (Locale) request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME);
        if (locale != null) {
            return locale;
        }

        // Retrieve and parse cookie value.
        Cookie cookie = WebUtils.getCookie(request, getCookieName());
        if (cookie != null) {
            locale = StringUtils.parseLocaleString(cookie.getValue());
            if (logger.isDebugEnabled()) {
                logger.debug("Parsed cookie value [" + cookie.getValue() + "] into locale '" + locale + "'");
            }
            if (locale != null) {
                request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME, locale);
                return locale;
            }
        }
        
        locale = getAcceptHeaderLocale(request);
        
        if (locale != null) {
            request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME, locale);
            return locale;    
        }

        return determineDefaultLocale(request);
    }
    
    public Locale getOwnersLocale() {
        Locale locale = null;
        
        User user = SecurityUtil.getAuthenticatedUser();
        
        if (user != null) {
            locale = user.getLocale();
            
            if (locale != null) {
                return locale;
            }
            
            Owner owner = user.getOwner();
            
            if (owner != null) {
                Language lang = owner.getLanguage();
                
                if (lang != null) {
                    locale = new Locale(lang.getCode());
                }
            }
        }
        
        return locale;
    }

    public Locale getAcceptHeaderLocale(HttpServletRequest request) {
        return request.getLocale();
    }
}
