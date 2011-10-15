package org.pollbox.poll.owners;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ownerList")
public class OwnerList {
    private int count;
    private Collection<Owner> owners;

    public OwnerList() {
    };

    public OwnerList(Collection<Owner> owners) {
        setOwners(owners);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElement(name = "owner")
    public Collection<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Collection<Owner> owners) {
        this.owners = owners;
        this.count = owners == null ? 0 : owners.size(); 
    }

    public void addOwner(Owner owner) {
        owners.add(owner);
        setCount(owners.size());
    }
}
