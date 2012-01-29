package org.integration.connectors.dropbox.account;

import java.math.BigInteger;


public class DropboxUserProfile {
    private String country;
    private final String referralLink;
    private String displayName;
    private final String email;
    private BigInteger uid;
    private BigInteger sharedQuota;
    private BigInteger quota;
    private BigInteger normalQuota;

    public DropboxUserProfile(BigInteger uid, String displayName, String email, String country, String referralLink, BigInteger sharedQuota, BigInteger quota, BigInteger normalQuota) {
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
        this.country = country;
        this.referralLink = referralLink;
        this.sharedQuota = sharedQuota;
        this.quota = quota;
        this.normalQuota = normalQuota;
    }
    
    @Override
    public String toString() {
        final String SEPARATOR = ", ";
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(getDisplayName() + SEPARATOR);
        builder.append(getEmail() + SEPARATOR);
        builder.append(getUid() + SEPARATOR);
        builder.append(getReferralLink() + SEPARATOR);
        builder.append(getQuota());
        builder.append("}");
        
        return builder.toString();
    }

    public String getCountry() {
        return country;
    }

    public String getDisplayName() {
        return displayName;
    }

    public BigInteger getSharedQuota() {
        return sharedQuota;
    }

    public BigInteger getQuota() {
        return quota;
    }

    public BigInteger getNormalQuota() {
        return normalQuota;
    }

    public BigInteger getUid() {
        return uid;
    }

    public String getReferralLink() {
        return referralLink;
    }

    public String getEmail() {
        return email;
    }
}
