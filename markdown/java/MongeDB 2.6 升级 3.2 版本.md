# MongeDB 2.6 升级 3.2 版本

标签（空格分隔）： MongoDB Java Spring-Data-MongoDB

---
[TOC]
### 修改 pom.xml 文件
根据[下表](#spring-data-mongodb-版本与-spring-版本兼容性)，修改 `Spring`、`Spring Data MongoDB`、`Mongodb Java Driver` 版本以支持 `MongoDB 3.0`，避免兼容性的问题。
> [Spring Data MongoDB](http://docs.spring.io/spring-data/data-mongodb/docs/1.7.2.RELEASE/reference/html/#new-features.1-7-0) 从 1.7 开始支持 `MongoDB Java Driver 3.0`

> **Requirement :**
> 1. `JDK 1.6+`
> 2. `Spring 4.0.x+`
> 3. `Spring Data MongoDB 1.7+`
>
    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-mongodb</artifactId>
        <version>1.7.2.RELEASE</version>
    </dependency>
> 4. `MongoDB Java Driver 3.0+`
>
    <dependency>
 		<groupId>org.mongodb</groupId>
 		<artifactId>mongo-java-driver</artifactId>
        <version>3.2.2</version>
 	</dependency>

#### Spring Data MongoDB 版本与 Spring 版本兼容性
| Spring Data MongoDB | Spring    | MongoDB Java Driver |
| -----------------   | --------: | -----------------:  |
| 1.6                 | 3.2+      | 2.4+                |
| 1.7、1.8            | 4.0+      | 2.6+                |
| 1.9+                | 4.2.5+    | 2.6+                |
| 1.9.2+              | 4.2.6+    | 2.6+                |
| 1.9.3+              | 4.2.8+    | 2.6+                |
| 1.9.7+              | 4.2.9+    | 2.6+                |
| 2.0.0.M1            | 5.0.0.M3+ | 2.6+                |
 
### 修改 Spring 配置文件 （mongo.xml）
1. 删除 xsi:schemaLocation 中链接的版本号，使用[最新的 xsd 文件](http://www.springframework.org/schema/data/mongo/spring-mongo.xsd)
2. 修改原来 `mongo` 连接的配置，`Spring Data MongoDB` 中的配置与原来有较大差异

```xml
    <!-- 连接 mongodb 的客户端 -->
    <mongo:mongo-client id="mongo3" 
        host="${mongo3.host}" 
        port="${mongo3.port}"
        <!-- MongoDB 3 改变了证书验证方式 -->
        credentials="${mongo3.username}:${mongo3.password}@${mongo3.db}" >
        <!-- mongodb 客户端的可配置选项参数 -->
     	<mongo:client-options 
     	    connections-per-host="10"     
     	    threads-allowed-to-block-for-connection-multiplier="100" 
     	    connect-timeout="1000" 
     	    max-wait-time="1500" />
    </mongo:mongo-client>
     
     <!-- 连接 mongodb 的 MongoDbFactory -->
    <mongo:db-factory dbname="${mongo3.db}" mongo-ref="mongo3" />
    
     <!-- 通过 MongoDbFactory 构造 MongoTemplate -->
    <bean id="mongoTemplate" class="org.springframework.data.mongodb.core.MongoTemplate">
     	<constructor-arg name="mongoDbFactory" ref="mongoDbFactory" />
    </bean>
```
更多配置请查看 [Spring Data MongoDB 1.7 文档](http://docs.spring.io/spring-data/data-mongodb/docs/1.7.2.RELEASE/reference/html/#mongo.mongo-3) 或 [spring-mongo.xsd 文件](http://www.springframework.org/schema/data/mongo/spring-mongo.xsd)

### 参考资料
* [Spring Data MongoDB 文档列表](http://docs.spring.io/spring-data/data-mongodb/docs/ )
* [Spring Data MongoDB 官方网站](http://projects.spring.io/spring-data-mongodb/)