package org.integration.paymentgateway.dibs.flexwin.conf;

import java.util.Set;

import org.integration.payments.server.document.Document;


/**
 * The interface which defines method to configure the DIBS FlexWin and integrate with MVC.
 *  
 * @author ob
 *
 */
public interface FlexWinConfigurator {
    /**
     * Method creates the default FlexWin configuration common for all FlexWin implementations.
     *   
     * @param document
     * @param merchantId
     * @param orderId
     * @param acceptUrl
     * @param cancelUrl
     * @param callbackUrl
     * @param sessionId
     * 
     * @return a configured FlexWinBean object
     * 
     */
	public FlexWinBean getDefaultFlexWin(Document document, String merchantId, String orderId, String acceptUrl, String cancelUrl, String callbackUrl, String sessionId);
    
    /**
     * Method return a set of FlexWinParam which this implementation supports.
     * 
     * @return a set of parameters 
     * 
     */
    public Set<FlexWinParam> getParams();
}
