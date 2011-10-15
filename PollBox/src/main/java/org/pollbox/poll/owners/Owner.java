package org.pollbox.poll.owners;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;
import org.pollbox.conf.language.Language;
import org.pollbox.poll.accounts.Account;
import org.pollbox.poll.auth.Authority;


@Entity
@Table(name = "OWNER")
@XmlRootElement(name = "owner", namespace = "http://pollbox.org/poll/model")
public class Owner implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    
    @NotEmpty
    @Size(max = 40)
    private String firstName;
    
    @NotEmpty
    @Size(max = 40)
    private String lastName;
    private Calendar birthDate;
    
    @NotEmpty
    @Size(max = 255)
    private String email;
    
    @Size(max = 20)
    private String phoneNumber;
    private String country;
    private String city;
    private String zip;
    private String street;
    private String building;
    private String buildingNumber;
    
    @NotNull
    private Calendar createdDate;
    private Long typeId;
    
    private String title;
    private String department;
    
    @NotEmpty
    @Size(max = 50)
    private String username;

    @NotEmpty
    private String password;
    private Boolean enabled;

    @NotNull
    private Account account;
    
    private Authority authority;
    
    private Language language;
    
    // Default constructor
    public Owner() {
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

    @Column(name = "FIRST_NAME", nullable=false, length=40)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable=false, length=40)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "EMAIL", nullable=false, length=255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "ZIP")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "BUILDING")
    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Column(name = "BUILDING_NUMBER")
    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "TYPE_ID")
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Column(name = "USERNAME", nullable = false, unique = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Column(name="ENABLED")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    @Transient
    @XmlTransient
    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "TITLE", length = 40)
    public String getTitle() {
        return title;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "DEPARTMENT", length = 40)
    public String getDepartment() {
        return department;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @ManyToOne(targetEntity = Language.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "LANGUAGE_ID")
    public Language getLanguage() {
        return language;
    }

    public String toString() {
        return "Owner info:"
            + "\n\tFirst Name:   " + getFirstName()
            + "\n\tLast Name:    " + getLastName()
            + "\n\tLanguage:     " + getLanguage()
            + "\n\tBirth Date:   " + getBirthDate()
            + "\n\tE-mail:       " + getEmail()
            + "\n\tUsername:     " + getUsername()
            + "\n\tTitle:        " + getTitle()
            + "\n\tDepartment:   " + getDepartment()
            + "\n\tPhone number: " + getPhoneNumber();
    }
}
