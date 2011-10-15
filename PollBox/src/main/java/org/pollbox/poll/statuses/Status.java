package org.pollbox.poll.statuses;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;
import org.pollbox.poll.accounts.Account;


@Entity
@Table(name="STATUS", uniqueConstraints = {@UniqueConstraint(columnNames={"NAME", "TYPE"})})
@XmlRootElement(name = "status", namespace = "http://pollbox.org/poll/status")
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    
    @NotEmpty
    @Size(max=20)
    private String name;
    
    @NotEmpty
    @Size(max=20)
    private String type;
    
    @Size(max=254)
    private String description;
    
    private Set<Account> accounts;
    
    // Default constructor
    public Status() {}
    
    /*
     * Use of this constructor is not permitted. Only predefined properties allowed.
     * Use Status(StatusDef status) instead
     * @see Status(StatusDef status)
     */
    private Status(Long id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }
    
    public Status(StatusDef status) {
        this(status.getId(), status.getName(), status.getDescription(), status.getType().name());
    }

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="TYPE", nullable = false, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "DESCRIPTION", length = 254)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "status", targetEntity = Account.class, fetch = FetchType.LAZY)
    @Transient
    @XmlTransient
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
