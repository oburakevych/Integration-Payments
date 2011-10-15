package org.pollbox.poll.accounts;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;
import org.pollbox.poll.owners.Owner;
import org.pollbox.poll.projects.Project;
import org.pollbox.poll.statuses.Status;


@Entity
@Table(name = "ACCOUNT")
@XmlRootElement(name = "account", namespace = "http://pollbox.org/poll/model")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    
    @NotEmpty
    @Size(max=20)
    private String name;
    
    @NotNull
    private Calendar dateCreated;
    private Status status;

    private Set<Owner> owners;
    private Set<Project> projects;

    // Default constructor
    public Account() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable=false, length=20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    @OneToMany(targetEntity = Owner.class, mappedBy = "account", fetch = FetchType.EAGER)
    @XmlTransient
    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    @OneToMany(targetEntity = Project.class, mappedBy = "account", fetch = FetchType.EAGER)
    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
    
    @ManyToOne(targetEntity = Status.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "STATUS_ID")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String toString() {
        return "Account info: "
            + "\n\tAccount Name:       " + getName()
            + "\n\tAccount Created on: " + getDateCreated();
    }

}
