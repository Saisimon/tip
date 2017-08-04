package net.saisimon.orm;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;

/**
 * 日志对象
 * 
 * @author Saisimon
 * 
 */
public class LoggingInfo implements Serializable {
	
	private static final long serialVersionUID = -4940242052969216071L;
	
	private Long id;
	private String message;
	private String level;
	private String loggerName;
	private String threadName;
	private String callerData;
	private String exceptionName;
	private String exceptionMessage;
	private String topStackTrace;
	private Map<String, String> mdcProperty;
	private String createTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLoggerName() {
		return loggerName;
	}
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getTopStackTrace() {
		return topStackTrace;
	}
	public void setTopStackTrace(String topStackTrace) {
		this.topStackTrace = topStackTrace;
	}
	public String getCallerData() {
		return callerData;
	}
	public void setCallerData(String callerData) {
		this.callerData = callerData;
	}
	public Map<String, String> getMdcProperty() {
		return mdcProperty;
	}
	public void setMdcProperty(Map<String, String> mdcProperty) {
		this.mdcProperty = mdcProperty;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public static LoggingInfo build(LoggingEvent loggingEvent) {
		LoggingInfo loggingInfo = new LoggingInfo();
		if (null != loggingEvent) {
			loggingInfo.setMessage(loggingEvent.getMessage());
			loggingInfo.setLevel(loggingEvent.getLevel().toString());
			loggingInfo.setLoggerName(loggingEvent.getLoggerName());
			loggingInfo.setThreadName(loggingEvent.getThreadName());
			if (null != loggingEvent.getThrowableProxy()) {
				loggingInfo.setExceptionName(loggingEvent.getThrowableProxy().getClassName());
				loggingInfo.setExceptionMessage(loggingEvent.getThrowableProxy().getMessage());
				StackTraceElementProxy[] stackTraces = loggingEvent.getThrowableProxy().getStackTraceElementProxyArray();
				if (null != stackTraces && stackTraces.length > 0) {
					loggingInfo.setTopStackTrace(stackTraces[0].getStackTraceElement().toString());
				}
			}
			if (loggingEvent.hasCallerData()) {
				StackTraceElement st = loggingEvent.getCallerData()[0];
				loggingInfo.setCallerData(st.toString());
			}
			loggingInfo.setMdcProperty(loggingEvent.getMDCPropertyMap());
			loggingInfo.setCreateTime(millisecondToDateTime(loggingEvent.getTimeStamp()));
		}
		return loggingInfo;
	}
	
	public static String millisecondToDateTime(Long milli) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String date = df.format(new Date(milli));
		return date;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append(getCreateTime()).append(" - ")
        	.append('[').append(getLevel()).append("] ")
        	.append('[').append(getThreadName()).append("] ")
        	.append('(').append(getLoggerName()).append(')')
        	.append(" - ").append(getMessage());
        return sb.toString();
	}
	
}
