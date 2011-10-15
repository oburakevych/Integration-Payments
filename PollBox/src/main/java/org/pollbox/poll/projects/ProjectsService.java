package org.pollbox.poll.projects;

import org.springframework.security.access.prepost.PreAuthorize;


public interface ProjectsService {
    public Project getProjectById(Long id);

    public ProjectList getProjects();

    public Project save(Project project);

    public void delete(Project project);

}
