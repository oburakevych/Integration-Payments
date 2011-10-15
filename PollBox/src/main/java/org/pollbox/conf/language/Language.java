package org.pollbox.conf.language;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;
import org.pollbox.poll.owners.Owner;


@Entity
@Table(name = "LANGUAGE", uniqueConstraints = {@UniqueConstraint(columnNames={"NAME", "CODE"})})
@XmlRootElement(name = "language", namespace = "http://pollbox.org/conf")
public class Language {
    private Long id;
    
    @NotEmpty
    @Size(max=40)
    private String name;
    
    @NotEmpty
    @Size(max=6)
    private String code;
    
    private Owner owner;

    public Language() {}
    
    public Language(LanguageDef lang) {
        this(lang.getId(), lang.getName(), lang.getCode());
    }
    
    public Language(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 40, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CODE", length = 6, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @OneToMany(mappedBy = "language", targetEntity = Owner.class, fetch = FetchType.LAZY)
    @Transient
    @XmlTransient
    public Owner getOwner() {
        return owner;
    }
    
    public String toString() {
        return getName() + " [" + getCode() + "]";
    }
}
