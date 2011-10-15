package org.pollbox.poll.projects;

import java.util.Collection;

import org.pollbox.poll.managers.DaoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProjectsServiceImpl implements ProjectsService {
    @Autowired(required = true)
    DaoManager daoManager;

    @Transactional(readOnly = true)
    public Project getProjectById(Long id) {
        return this.daoManager.getProjectDao().getById(id);
    }

    @Transactional(readOnly = true)
    public ProjectList getProjects() {
        Collection<Project> projects = this.daoManager.getProjectDao().getProjects();
        
        return new ProjectList(projects);
    }

    @Transactional
    public Project save(Project project) {
        return this.daoManager.getProjectDao().save(project);
    }

    @Transactional
    public void delete(Project project) {
        this.daoManager.getProjectDao().delete(project);

    }

}
