package org.integration.payments.server.polling;

import java.util.UUID;

import org.integration.payments.server.om.UserFeedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("polling")
public class PollingController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PollingService pollingService;

	public void setPollingService(PollingService pollingService) {
		this.pollingService = pollingService;
	}

	@RequestMapping(value = "/user/feedback", method = RequestMethod.POST)
	public void userFeedback(@RequestParam("companyAccountId") UUID companyAccountId,
		@RequestParam("feedback_pg") String feedbackPg,
		@RequestParam("feedback_pg_int") String feedbackPgInt,
		@RequestParam("feedback_txt") String feedbackTxt) {

		UserFeedback feedback = new UserFeedback();

		feedback.setCompanyAccountId(companyAccountId);
		feedback.setFeedbackPg(feedbackPg);
		feedback.setFeedbackPgInt(feedbackPgInt);
		feedback.setFeedbackTxt(feedbackTxt);

		pollingService.saveUserFeedback(feedback);
	}
}
