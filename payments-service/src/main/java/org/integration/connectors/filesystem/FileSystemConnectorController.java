package org.integration.connectors.filesystem;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("connector/fs")
public class FileSystemConnectorController {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FileSystemConnectorService connectorService;
    
    @RequestMapping(value = "documentfile", method = RequestMethod.PUT, consumes = "*/*")
    public void transfer(@RequestParam("companyAccountId") UUID companyAccountId, @RequestParam(value="filename") String fileName, @RequestBody byte[] content, HttpServletResponse response) {
        log.info("TRANSFERRING!!!!!!!!!!!!!!!!!!!");
        
        connectorService.transferDocumentFile(companyAccountId, "FS", fileName, null, content);
        
        response.setStatus(HttpStatus.CREATED.value());
    }

    public void setConnectorService(FileSystemConnectorService connectorService) {
        this.connectorService = connectorService;
    }

    public FileSystemConnectorService getConnectorService() {
        return connectorService;
    }

}
