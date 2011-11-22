package org.integration.payments.server.polling;

import org.integration.payments.server.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(Constants.TEST_URI_SECRET_PREFIX + "/test/polling")
public class PollingTestController {
	
	private static final String PAGE_VIEW = "test/polling";

	private static final String FEEDBACKS_PAGE_VIEW = "test/feedbacks";

	@RequestMapping(method = RequestMethod.GET)
	public String view() {
		return PAGE_VIEW;
	}

	@RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
	public ModelAndView viewFeedbacks() {
		return new ModelAndView(FEEDBACKS_PAGE_VIEW);
	}
}
