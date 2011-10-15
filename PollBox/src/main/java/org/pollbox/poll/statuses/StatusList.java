package org.pollbox.poll.statuses;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "statusList")
public class StatusList {
    private int count;
    private Collection<Status> statuses;

    public StatusList() {};

    public StatusList(Collection<Status> statuses) {
        setStatuses(statuses);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElement(name = "status")
    public Collection<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(Collection<Status> statuses) {
        this.statuses = statuses;
        this.count = statuses == null ? 0 : statuses.size(); 
    }

    public void add(Status status) {
        this.statuses.add(status);
        setCount(this.statuses.size());
    }
}
