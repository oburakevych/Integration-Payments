package org.integration.payments.server.document;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.joda.time.DateTime;


@XmlRootElement(name = "Dispatch")
@XmlType(name = "Dispatch")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dispatch {
	@XmlElement(name = "DispatchId")
	private UUID dispatchID;
	
	@XmlElement(name = "ObjectId")
	private UUID objectId;
	
	@XmlElement(name = "Created")
	private DateTime created;
	
	@XmlElement(name = "SenderCompanyAccountId")
	private UUID senderCompanyAccountId;
	
	@XmlElement(name = "SenderUserId")
	private UUID senderUserId;
	
	@XmlElement(name = "DispatchState")
	private String dispatchState;
	
	@XmlElement(name = "LastStateChange")
	private DateTime lastStateChange;
	
	@XmlElement(name = "ReceiverConnectionId")
	private UUID receiverConnectionId;
	
	@XmlElement(name = "DispatchChannel")
	private String dispatchChannel;
	
	@XmlElement(name = "FailureMessage")
	private String failureMessage;
	
	public UUID getDispatchID() {
		return dispatchID;
	}

	public void setDispatchID(UUID dispatchID) {
		this.dispatchID = dispatchID;
	}

	public UUID getObjectId() {
		return objectId;
	}

	public void setObjectId(UUID objectId) {
		this.objectId = objectId;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public UUID getSenderCompanyAccountId() {
		return senderCompanyAccountId;
	}

	public void setSenderCompanyAccountId(UUID senderTenantId) {
		this.senderCompanyAccountId = senderTenantId;
	}

	public UUID getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(UUID senderUserId) {
		this.senderUserId = senderUserId;
	}

	public String getDispatchState() {
		return dispatchState;
	}

	public void setDispatchState(String dispatchState) {
		this.dispatchState = dispatchState;
	}

	public DateTime getLastStateChange() {
		return lastStateChange;
	}

	public void setLastStateChange(DateTime lastStateChange) {
		this.lastStateChange = lastStateChange;
	}

	public UUID getReceiverConnectionId() {
		return receiverConnectionId;
	}

	public void setReceiverConnectionId(UUID receiverConnectionId) {
		this.receiverConnectionId = receiverConnectionId;
	}

	public String getDispatchChannel() {
		return dispatchChannel;
	}

	public void setDispatchChannel(String dispatchChannel) {
		this.dispatchChannel = dispatchChannel;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}
}