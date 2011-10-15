package org.pollbox.poll.projects;

import java.io.StringReader;

import javax.inject.Inject;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.pollbox.poll.accounts.Account;
import org.pollbox.poll.auth.SecurityUtil;
import org.pollbox.poll.auth.User;
import org.pollbox.poll.managers.ServiceManager;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/projects")
public class ProjectsController {
    private final ServiceManager serviceManager;
    private final Jaxb2Marshaller marshaller;

    @Inject
    public ProjectsController(ServiceManager serviceManager, Jaxb2Marshaller marshaller) {
        this.serviceManager = serviceManager;
        this.marshaller = marshaller;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getProjects(Model model) {
        User user = SecurityUtil.getAuthenticatedUser();
        
        Account account = serviceManager.getAccountsService().getAccountById(user.getAccountId());
        
        ProjectList projectList = new ProjectList(account.getProjects());

        model.addAttribute("projectList", projectList);

        return "projects";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProject(@PathVariable Long id, Model model) {
        Project project = serviceManager.getProjectsService().getProjectById(id);

        model.addAttribute("project", project);

        return "project";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addProject(@RequestBody String body, Model model) {
        Source source = new StreamSource(new StringReader(body));

        Project project = (Project) marshaller.unmarshal(source);

        project = serviceManager.getProjectsService().save(project);

        model.addAttribute("project", project);

        return "project";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateProject(@RequestBody String body, Model model) {
        Source source = new StreamSource(new StringReader(body));

        Project project = (Project) marshaller.unmarshal(source);

        project = serviceManager.getProjectsService().save(project);

        model.addAttribute("project", project);

        return "project";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteProject(@PathVariable Long id, Model model) {
        Project project = serviceManager.getProjectsService().getProjectById(id);

        if (project != null) {
            serviceManager.getProjectsService().delete(project);
        }

        ProjectList projectList = serviceManager.getProjectsService().getProjects();

        model.addAttribute("projectList", projectList);

        return "projects";
    }    

}
