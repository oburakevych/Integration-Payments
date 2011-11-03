package org.integration.payments.server.document;

import java.util.HashMap;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.joda.time.DateTime;


@XmlRootElement(name="DocumentMetadata")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="DocumentMetadata")
public class DocumentMetadata {
	@XmlElement(name = "DocumentId")
	private UUID documentId;
	
	@XmlElement(name="DocumentType")
	private DocumentType documentType;
	
	@XmlElement(name="ConversationId")
	private UUID conversationId;
	
	@XmlElement(name="State")
	private String state;
	
	@XmlElement(name="Created")
	private DateTime created;
	
	@XmlElement(name="LastEdit")
	private DateTime lastEdit;

	@XmlElement(name="Hash")
	private String hash;
	
	@XmlElement(name="DispatchChannel")
	private String dispatchChannel;
	
	@XmlElement(name="ConnectionId")
	private UUID connectionId;

	@XmlElementWrapper(name="Properties")
    private HashMap<String, String> properties;
	
	@XmlElement(name="Tags")
    private TagList tags = new TagList();

	@XmlElement(name="DispatchState")
	private String dispatchState;
	
	@XmlElement(name="Received")
	private boolean received;

    @XmlElement(name="ProcessState")
    private String processState;
    
    @XmlElement(name="DeliveryState")
    private String deliveryState;

    @XmlElement(name="OtherPartyState")
    private String otherPartyState;
    
    @XmlElement(name="Signature")
    private String signature;
    
    @XmlElement(name="SignatureValidationReport")
    private String signatureReport;

    public DocumentMetadata() {
		super();
	}
	
	public UUID getDocumentId() {
		return documentId;
	}

	public void setDocumentId(UUID documentId) {
		this.documentId = documentId;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public UUID getConversationId() {
		return conversationId;
	}

	public void setConversationId(UUID conversationId) {
		this.conversationId = conversationId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public DateTime getLastEdit() {
		return lastEdit;
	}

	public void setLastEdit(DateTime lastEdit) {
		this.lastEdit = lastEdit;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getDispatchChannel() {
		return dispatchChannel;
	}

	public void setDispatchChannel(String dispatchChannel) {
		this.dispatchChannel = dispatchChannel;
	}

	public UUID getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(UUID connectionId) {
		this.connectionId = connectionId;
	}

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

	public String getDispatchState() {
		return dispatchState;
	}

	public void setDispatchState(String dispatchStatus) {
		this.dispatchState = dispatchStatus;
	}

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean received) {
		this.received = received;
	}

    public void setProcessState(String processState) {
        this.processState = processState;
    }
    
    public String getProcessState() {
        return processState;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public void setOtherPartyState(String otherPartyState) {
        this.otherPartyState = otherPartyState;
    }
    
    public String getOtherPartyState() {
        return otherPartyState;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignatureReport() {
        return signatureReport;
    }

    public void setSignatureReport(String signatureReport) {
        this.signatureReport = signatureReport;
    }

    public void setTags(TagList tags) {
        this.tags = tags;
    }

    public TagList getTags() {
        return tags;
    }
    
    
}
