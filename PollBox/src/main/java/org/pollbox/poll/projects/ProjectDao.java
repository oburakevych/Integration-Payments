package org.pollbox.poll.projects;

import java.util.Collection;


public interface ProjectDao {
	public Project getById(Long id);
	
	public Collection<Project> getProjects();

	public Project save(Project project);
	
	public void delete(Project project);
}
