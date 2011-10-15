package org.pollbox.poll.projects;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "projectList")
public class ProjectList {
    private int count;
    private Collection<Project> projects;
    
    public ProjectList() {}
    
    public ProjectList(Collection<Project> projects) {
        setProjects(projects);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElement(name = "project")
    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
        this.count = projects == null ? 0 : projects.size();         
    }
    
    public void addProject(Project project) {
        projects.add(project);
        count = projects.size();
    }
}
