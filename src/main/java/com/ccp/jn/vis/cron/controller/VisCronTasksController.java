package com.ccp.jn.vis.cron.controller;

import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.implementations.db.bulk.elasticsearch.CcpElasticSerchDbBulk;
import com.ccp.implementations.db.dao.elasticsearch.CcpElasticSearchDao;
import com.ccp.implementations.db.query.elasticsearch.CcpElasticSearchQueryExecutor;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.email.sendgrid.CcpSendGridEmailSender;
import com.ccp.implementations.file.bucket.gcp.CcpGcpFileBucket;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.instant.messenger.telegram.CcpTelegramInstantMessenger;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;
import com.ccp.jn.async.business.JnAsyncBusinessNotifyError;
import com.ccp.jn.cron.controller.CcpCronTasksController;
import com.ccp.jn.vis.async.business.factory.CcpVisAsyncBusinessFactory;

public class VisCronTasksController {
	public static void main(String[] args) throws Exception {
		CcpDependencyInjection.loadAllDependencies
		(
				new CcpElasticSearchQueryExecutor(),
				new CcpTelegramInstantMessenger(),
				new CcpVisAsyncBusinessFactory(),
				new CcpElasticSearchDbRequest(),
				new CcpSendGridEmailSender(),
				new CcpElasticSerchDbBulk(),
				new CcpElasticSearchDao(),
				new CcpGsonJsonHandler(),
				new CcpApacheMimeHttp(),
				new CcpGcpFileBucket()
		);
		
		JnAsyncBusinessNotifyError jnAsyncBusinessNotifyError = new JnAsyncBusinessNotifyError();
		String parameters = args[1];
		String taskName = args[0];
		CcpCronTasksController.main(jnAsyncBusinessNotifyError, taskName, parameters);
	}
}
