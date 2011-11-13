package org.integration.payments.server.polling;

import org.integration.payments.server.dao.PluginUsageInfoDao;
import org.integration.payments.server.dao.UserFeedbackDao;
import org.integration.payments.server.dao.UserProfileDao;
import org.integration.payments.server.om.UserFeedback;

public class PollingService {
	@SuppressWarnings("unused")
	private UserProfileDao userProfileDao;

	private UserFeedbackDao userFeedbackDao;

	@SuppressWarnings("unused")
	private PluginUsageInfoDao pluginUsageInfoDao;

	public void setUserProfileDao(UserProfileDao userProfileDao) {
		this.userProfileDao = userProfileDao;
	}

	public void setUserFeedbackDao(UserFeedbackDao userFeedbackDao) {
		this.userFeedbackDao = userFeedbackDao;
	}

	public void setPluginUsageInfoDao(PluginUsageInfoDao pluginUsageInfoDao) {
		this.pluginUsageInfoDao = pluginUsageInfoDao;
	}

	public PollingService() {
	}

	public void saveUserFeedback(UserFeedback feedback) {
		UserFeedback oFeedback = userFeedbackDao.get(feedback.getCompanyAccountId());

		if (oFeedback == null) {
			userFeedbackDao.create(feedback);
		} else {
			userFeedbackDao.update(feedback);
		}
	}
}
