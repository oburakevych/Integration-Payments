package org.pollbox.conf.language;


public interface LanguageDao {
    public Language getLanguage(Long id);
    public Language getLanguage(String code);
    public Language getLanguage(String name, String code);
    public void save(Language language);
    public void delete(Language language);
}
