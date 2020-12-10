package com.ochiengolanga.tuts.bootgraphql.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class ComponentLogs {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	public static final String FORMAT_LOG_HEADER = "{}={} Method={} ::: ";
	public static final String TASK_INFO_LOG = FORMAT_LOG_HEADER + "Task: '{}' completed in '{}' ms";
	public static final String START_LOG_HEADER = "START {}={}, Method={}, Params={}";
	public static final String EXIT_LOG_HEADER_ERROR = "EXIT {}={}, Method={}, Error={}";
	public static final String EXIT_LOG_HEADER_RESULT = "EXIT {}={}, Method={}, Result={}";
	public static final String GENERIC_ERROR = "EXIT, Error";

	protected String objectName;
	protected String objectType;

	protected void logTaskInfoAndStop(String methodName, StopWatch stopWatch) {
		if (stopWatch != null && stopWatch.isRunning()) {
			stopWatch.stop();
		}
		logTaskInfo(methodName, stopWatch);
	}

	protected void logTaskInfo(String methodName, StopWatch stopWatch) {
		if (stopWatch != null && stopWatch.getTaskCount() > 0) {
			logTaskInfo(methodName, stopWatch.getLastTaskInfo());
		}
	}

	protected void logTaskInfo(String methodName, StopWatch.TaskInfo taskInfo) {
		if (taskInfo != null)
			logger.debug(TASK_INFO_LOG, objectType, objectName, methodName, taskInfo.getTaskName(),
					taskInfo.getTimeMillis());
	}

	protected void logStart(String methodName, Object... params) {
		logger.info(START_LOG_HEADER, objectType, objectName, methodName, params);
	}

	protected void logExitWithErrorsAndStack(String methodName, Exception e) {
		logger.error(String.format("%s : %s : %s", objectType, objectName, methodName), e);
		if (!(e instanceof CustomException))
			logger.error(EXIT_LOG_HEADER_ERROR, objectType, objectName, methodName, GENERIC_ERROR);
		else {
			CustomException ce = (CustomException) e;
			String cause = e.getMessage();
			String errorMessage = String.format("CustomException:%d, message = %s", ce.getErrorCode(), cause);
			logger.error(EXIT_LOG_HEADER_ERROR, objectType, objectName, methodName, errorMessage);
		}
	}

	protected void logExit(String methodName, Object... result) {
		logger.info(EXIT_LOG_HEADER_RESULT, objectType, objectName, methodName, result);
	}

	protected void logExitWithErrors(String methodName, Exception e) {
		if (!(e instanceof CustomException))
			logger.error(EXIT_LOG_HEADER_ERROR, objectType, objectName, methodName, GENERIC_ERROR);
		else {
			CustomException ce = (CustomException) e;
			String cause = e.getMessage();
			String errorMessage = String.format("CustomException:%d, message = %s", ce.getErrorCode(), cause);
			logger.error(EXIT_LOG_HEADER_ERROR, objectType, objectName, methodName, errorMessage);
		}
	}
}
