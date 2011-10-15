package org.pollbox.conf.language;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pollbox.poll.statuses.StatusDef;

public enum LanguageDef {
    ENGLISH(10L, "en", "English"),
    UKRAINIAN(20L, "uk", "Ukrainian");

    private Long id;
    private String code;
    private String name;
    
    private final static Map<Long, LanguageDef> itemMap = new HashMap<Long, LanguageDef>();
    
    static {
        for(LanguageDef item : values()) {
            itemMap.put(item.getId(), item);
        }
    }
    
    private LanguageDef(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
    
    public static List<LanguageDef> getLanguages() {
        return Arrays.asList(LanguageDef.values());
    }
    
    public static LanguageDef getLanguageDef(Long id) {
        LanguageDef item = itemMap.get(id);
        
        if (item == null) {
            throw new IllegalArgumentException("No enum constant of class " + LanguageDef.class.getSimpleName() + " with id " + id);
        }
        
        return item;
    }
    
    public static Language getLanguage(Long id) {
        LanguageDef langDef = getLanguageDef(id);
        
        Language language = null;
        
        if (langDef != null) {
            language = new Language(langDef);    
        }
        
        return language;
    }
    
    public static LanguageDef getDefault() {
        return ENGLISH;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
