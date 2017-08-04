package net.saisimon.logging;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import net.saisimon.orm.LoggingInfo;

/**
 * Mongo Appender
 * 
 * @author Saisimon
 *
 */
public class MongoAppender extends AppenderBase<LoggingEvent> {
	
	private MongoClient client;
	private MongoTemplate mongoTemplate;
	
	private String database;
	private String username;
	private String password;
	private String host;
	private int port;
	
	@Override
	public void start() {
		connectMongoDB();
		buildMongoTemplate();
		super.start();
	}

	@Override
	public void stop() {
		disconnectMongoDB();
		super.stop();
	}

	@Override
	protected void append(LoggingEvent loggingEvent) {
		LoggingInfo loggingInfo = LoggingInfo.build(loggingEvent);
		String collectionName = getCollectionName(LoggingInfo.class);
		if (null == loggingInfo.getId()) {
			loggingInfo.setId(Long.valueOf(getNextId(collectionName)));
		}
		mongoTemplate.save(loggingInfo, collectionName);
	}
	
	private String getCollectionName(Class<?> entityClass) {
		String className = entityClass.getName();
		className = className.substring(className.lastIndexOf(".") + 1);
		return StringUtils.uncapitalize(className);
	}
	
	private String getNextId(String collectionName) {
		DBCollection seq = mongoTemplate.getCollection("seq");
		DBObject query = new BasicDBObject();
		query.put("_id", collectionName);
		DBObject change = new BasicDBObject("seq", Integer.valueOf(1));
		DBObject update = new BasicDBObject("$inc", change);
		DBObject res = seq.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
		return res.get("seq").toString();
	}
	
	private void connectMongoDB() {
		if (null == client) {
			ServerAddress serverAddress = new ServerAddress(host, port);
			MongoClientOptions options = MongoClientOptions.builder()
					.connectionsPerHost(5).threadsAllowedToBlockForConnectionMultiplier(100)
					.connectTimeout(1000).maxWaitTime(1500).build();
			MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
			List<MongoCredential> credentials = new ArrayList<>();
			credentials.add(credential);
			client = new MongoClient(serverAddress, credentials, options);
		}
	}
	
	private void disconnectMongoDB() {
		if (null != client) {
			client.close();
			client = null;
			mongoTemplate = null;
		}
	}
	
	private void buildMongoTemplate() {
		if (null == mongoTemplate) {
			MongoDbFactory factory = new SimpleMongoDbFactory(client, database);
			mongoTemplate = new MongoTemplate(factory);
		}
	}
	
	public void setClient(MongoClient client) {
		this.client = client;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}