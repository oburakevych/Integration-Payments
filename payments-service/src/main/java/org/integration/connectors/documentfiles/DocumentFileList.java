package org.integration.connectors.documentfiles;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="DocumentFileList")
public class DocumentFileList extends PagedList<DocumentFile> {
	@XmlElement(name="Items")
	private List<DocumentFile> items = new ArrayList<DocumentFile>();
	
	public List<DocumentFile> getItems() {
		return items;
	}

    @Override
    public void setItems(List<DocumentFile> items) {
        this.items = items;
    }
}
