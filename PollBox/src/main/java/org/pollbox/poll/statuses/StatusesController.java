package org.pollbox.poll.statuses;

import java.io.StringReader;

import javax.inject.Inject;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.pollbox.poll.managers.ServiceManager;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/statuses")
public class StatusesController {
    private final ServiceManager serviceManager;
    private final Jaxb2Marshaller marshaller;

    @Inject
    public StatusesController(ServiceManager serviceManager, Jaxb2Marshaller marshaller) {
        this.serviceManager = serviceManager;
        this.marshaller = marshaller;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getStatuses(Model model) {
        StatusList statusList = serviceManager.getStatusesService().getStatuses();

        model.addAttribute("statusList", statusList);

        return "statuses";
    }

    @RequestMapping("/{id}")
    public String getStatus(@PathVariable Long id, Model model) {
        Status status = serviceManager.getStatusesService().getStatusById(id);

        model.addAttribute("status", status);

        return "status";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addStatus(@RequestBody String body, Model model) {
        Source source = new StreamSource(new StringReader(body));

        Status status = (Status) marshaller.unmarshal(source);

        status = serviceManager.getStatusesService().save(status);

        model.addAttribute("status", status);

        return "status";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateStatus(@RequestBody String body, Model model) {
        Source source = new StreamSource(new StringReader(body));

        Status status = (Status) marshaller.unmarshal(source);

        status = serviceManager.getStatusesService().save(status);

        model.addAttribute("status", status);

        return "status";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteStatus(@PathVariable Long id, Model model) {
        Status status = serviceManager.getStatusesService().getStatusById(id);

        if (status != null) {
            serviceManager.getStatusesService().delete(status);
        }

        StatusList statusList = serviceManager.getStatusesService().getStatuses();

        model.addAttribute("statusList", statusList);

        return "statuses";
    }
}
