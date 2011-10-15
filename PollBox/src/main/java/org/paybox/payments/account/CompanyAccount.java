package org.paybox.payments.account;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;
import org.paybox.payments.transactions.Transaction;
import org.pollbox.poll.owners.Owner;
import org.pollbox.poll.projects.Project;
import org.pollbox.poll.statuses.Status;


@Entity
@Table(name = "COMPANY_ACCOUNT")
@XmlRootElement(name = "company_account", namespace = "http://paybox.org/payments/model")
public class CompanyAccount implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long id;
        
        @NotEmpty
        @Size(max=20)
        private String name;
        
        @NotNull
        private Calendar dateCreated;
        private String status;
        private AccountConfig accountConfig;
        private List<Transaction> transactions;
        
        @OneToMany(targetEntity = Transaction.class, mappedBy = "companyAccount", fetch = FetchType.EAGER)
        public List<Transaction> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
        }

        @OneToOne (targetEntity = AccountConfig.class, mappedBy = "companyAccount", fetch = FetchType.EAGER,
                cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        public AccountConfig getAccountConfig() {
            return accountConfig;
        }

        public void setAccountConfig(AccountConfig accountConfig) {
            this.accountConfig = accountConfig;
        }

        // Default constructor
        public CompanyAccount() {
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        
        public String toString() {
            return "CompanyAccount info: "
                + "\n\tAccount Name:       " + getName()
                + "\n\tAccount Created on: " + getDateCreated()
                + "\n\tAccount Status: " + getStatus();
        }

}
