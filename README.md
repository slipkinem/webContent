---
title: java SSM和vue整合
categories: 课外学习
tags:
  - java
  - ssm
  - javascript
---

# SSM整合流程
## spring spring-mvc mybatis maven
## web前端项目的源码[vue-admin](https://github.com/slipkinem/vue-admin)
## 数据库的sql文件[vue_admin.sql](mind/vue_admin.sql)

### IDE idea
1. File => new Project => maven => create from archetype => **maven-archetype-webapp**  
![界面预览](https://github.com/slipkinem/webContent/blob/master/mind/1.png)
2. 一直点击next直到finished，maven会自动创建一个项目目录  
![2](https://github.com/slipkinem/webContent/blob/master/mind/2.png)
  * 最终项目目录结构图  
<!-- more -->
```$xslt
│  .gitignore
│  pom.xml
│  README.md
│  tree.txt
│  
├─src
│  └─main
│      ├─java
│      │  └─cn
│      │      └─lvsen
│      │          └─test
│      │              ├─controller
│      │              │      TableController.java
│      │              │      UserController.java
│      │              │      
│      │              ├─dao
│      │              │      TableDataMapper.java
│      │              │      UserMapper.java
│      │              │      
│      │              ├─interceptor
│      │              │      AuthInterceptor.java
│      │              │      
│      │              ├─mapping
│      │              │      TableDataMapper.xml
│      │              │      UserMapper.xml
│      │              │      
│      │              ├─model
│      │              │      TableData.java
│      │              │      TableDataExample.java
│      │              │      User.java
│      │              │      UserExample.java
│      │              │      
│      │              └─service
│      │                  │  TableService.java
│      │                  │  UserService.java
│      │                  │  
│      │                  └─impl
│      │                          TableServiceImpl.java
│      │                          UserServiceImpl.java
│      │                          
│      ├─resources
│      │  │  generatorConfig.xml
│      │  │  jdbc.properties
│      │  │  logback.xml.bak
│      │  │  
│      │  └─spring
│      │          spring-mvc.xml
│      │          spring-mybatis.xml
│      │          
│      └─webapp
│          │  index.html
│          │  index.jsp
│          │  
│          ├─static
│          │  ├─css
│          │  │      app.f3c8e005c2be0a746136766675898059.css
│          │  │      app.f3c8e005c2be0a746136766675898059.css.gz
│          │  │      
│          │  ├─fonts
│          │  │      element-icons.b02bdc1.ttf
│          │  │      
│          │  └─js
│          │          0.105cd4667842b0092158.js
│          │          1.3f78774435db7aa5de2a.js
│          │          app.3ec6fa4bebfb1bb58a2b.js
│          │          app.3ec6fa4bebfb1bb58a2b.js.gz
│          │          manifest.b0bb778337a7d07c6313.js
│          │          vendor.bbf93d5282d3216eb30e.js
│          │          vendor.bbf93d5282d3216eb30e.js.gz
│          │          
│          └─WEB-INF
│                  web.xml
│                  
└─test
        TestHello.java
```
3. 在创建的POM里面添加依赖  
    pom.xml
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>article</groupId>
  <artifactId>article</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>article Maven Webapp</name>
  <url>http://maven.apache.org</url>

  <properties>
    <spring.version>4.2.5.RELEASE</spring.version>
  </properties>

  <dependencies>
    <!--测试-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
    <!--spring-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- jdbc连接池 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- 事务管理 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!--spring测试用-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- 注解扫描 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- MVC -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- 切面，将日志记录，事务等分离开来 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- mybatis和spring结合用 -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.2.4</version>
    </dependency>
    <!-- mysql连接驱动 -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.38</version>
    </dependency>
    <!-- 连接数据库 -->
    <dependency>
      <groupId>commons-dbcp</groupId>
      <artifactId>commons-dbcp</artifactId>
      <version>1.4</version>
    </dependency>
  <!-- mybatis -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.3.1</version>
    </dependency>
    <!-- 转换json用 -->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>1.2.7</version>
    </dependency>
    <!-- jackson转换json用 @ResponseBody默认调用jackson，将java对象转为json -->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.8.7</version>
    </dependency>

    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-annotations</artifactId>
      <version>2.8.0</version>
    </dependency>

    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.8.7</version>
    </dependency>
    <!-- 分页插件 -->
    <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper</artifactId>
      <version>5.0.0</version>
    </dependency>
    <!-- javaHttp基础包 -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
    </dependency>
    <!-- 日志 -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.24</version>
    </dependency>
    <!-- slf4j要配合其他日志包运行，slf4j起个管理的作用 -->
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.1.11</version>
    </dependency>

  </dependencies>


  <build>
    <finalName>article</finalName>
    <!-- 防止package打包不到XML-->
    <resources>
      <resource>
        <directory>src/main/java</directory>
        <includes>
          <include>**/*.xml</include>
        </includes>
        <filtering>true</filtering>
      </resource>
    </resources>

    <plugins>
      <!-- mybatis生成代码插件 -->
      <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.5</version>
        <configuration>
          <!-- 生成文件的配置 -->
          <configurationFile>src/main/resources/generatorConfig.xml</configurationFile>
          <verbose>true</verbose>
          <overwrite>true</overwrite>
        </configuration>
      </plugin>
      <plugin>
        <!-- maven插件 -->
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>1.6</source>
          <target>1.6</target>
        </configuration>
      </plugin>
    </plugins>

  </build>
</project>
```
4. 配置mybatisGeneratorConfig，需要连接数据库，
    将准备的sql文件导入数据库中[vue_admin.sql](mind/vue_admin.sql)  
generatorConfig.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
    PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
    "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>

  <!--<properties resource="jdbc.properties"/>-->

  <!--连接mysql的jar包-->
  <classPathEntry
      location="C:/Users/**/.m2/repository/mysql/mysql-connector-java/5.1.29/mysql-connector-java-5.1.29.jar" />

  <context id="default" targetRuntime="MyBatis3">
    <!-- jdbc连接 -->
    <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                    connectionURL="jdbc:mysql://localhost:3306/vue_admin"
                    userId="**"
                    password="**"/>
    <!-- 生成model =》 数据层 -->
    <javaModelGenerator targetPackage="cn.lvsen.test.model" targetProject="src/main/java">
    </javaModelGenerator>
    <!-- 生成mapper 存有mybatis的数据库操作xml -->
    <sqlMapGenerator targetPackage="cn.lvsen.test.mapping" targetProject="src/main/java">
    </sqlMapGenerator>
    <!-- 存有mappper的接口类，mybatis会自动生成实体类，将mapper和dao连接起来 -->
    <javaClientGenerator type="XMLMAPPER" targetPackage="cn.lvsen.test.dao" targetProject="src/main/java">
    </javaClientGenerator>
    <!-- 表的名字 -->
    <table tableName="t_table" domainObjectName="TableData"/>
    <table tableName="t_user" domainObjectName="User" />
  </context>
</generatorConfiguration>
```
5. 配置resources/spring/spring-mvc.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:p="http://www.springframework.org/schema/p"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

  <!--扫描包，查注解-->
  <context:component-scan base-package="cn.lvsen.test"/>

  <!--映射器与适配器，去掉@ResponseBody就会出问题-->
  <mvc:annotation-driven/>
  <!-- 静态资源放置地 -->
  <mvc:resources mapping="/**" location="static" />
  <!--<mvc:resources mapping="/css/**" location="static" />-->
  <mvc:default-servlet-handler />
  <!-- 拦截器 -->
  <mvc:interceptors>
    <mvc:interceptor>
      <mvc:mapping path="/api/**"/>
      <bean class="cn.lvsen.test.interceptor.AuthInterceptor"/>
    </mvc:interceptor>
  </mvc:interceptors>
  <!-- 视图 -->
  <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver"
        p:prefix="/" p:suffix=".html"/>
</beans>
```
6. 配置resources/spring/spring-mybatis.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  <!--引入jdbc的配置文件-->
  <bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <property name="location" value="classpath:jdbc.properties" />
  </bean>
  <!--jdbc连接数据库基本配置-->
  <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="${jdbc.driver}" />
    <property name="url" value="${jdbc.url}" />
    <property name="username" value="${jdbc.username}" />
    <property name="password" value="${jdbc.password}" />
  </bean>
  <!--spring和mybatis结合的配置-->
  <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="mapperLocations" value="classpath:cn/lvsen/test/mapping/*Mapper.xml" />
    <property name="plugins">
      <array>
        <bean class="com.github.pagehelper.PageInterceptor">
          <!--少了这句话会导致空指针异常-->
          <property name="properties">
            <value></value>
          </property>
        </bean>
      </array>
    </property>
  </bean>

  <!--dao接口，sping会自动查找其中的类 dao ——> 数据访问层 dao映射的mapper，去掉启动报错-->
  <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="cn.lvsen.test.dao"/>
    <!--对应上面id="sqlSessionFactory"-->
    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
  </bean>
</beans>
```

7. spring-mybatis.xml配置时用了**jdbc.properties**这是为了方便管理连接  
    resources/jdbc.properties的配置
```
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/vue_admin?useUnicode=true&characterEncoding=UTF-8
jdbc.username=**
jdbc.password=**
```
8. web.xml配置，web.xml是这个web项目的入口  
```
<?xml version="1.0" encoding="utf-8" ?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">
  <display-name>Archetype Created Web Application</display-name>
  <!--spring和mybatis的配置文件-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring/spring-mybatis.xml</param-value>
  </context-param>

  <!--spring监听器-->
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!--解决乱码-->
  <filter>
    <filter-name>SpringEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <!--映射的路径-->
  <filter-mapping>
    <filter-name>SpringEncodingFilter</filter-name>
    <url-pattern>/</url-pattern>
  </filter-mapping>
  <!-- 声明静态资源类型，加入spring扫描 -->
  <servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>*.html</url-pattern>
  </servlet-mapping>

  <!--spring MVC 核心分发器-->
  <servlet>
    <servlet-name>SpringMvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring/spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>

  <servlet-mapping>
    <servlet-name>SpringMvc</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
  <!-- 首页，可以多个，根据顺来显示-->
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
  </welcome-file-list>
</web-app>
```

9. SSM基本配置完了，其他功能需要的时候再加，现在新建文件夹  
  * java/cn.xx.xx/controller  // controller层
  * java/cn.xx.xx/service // service层
  * java/cn.xx.xx/service/impl // service层接口的实现类
  * webapp/index.html

10. 在controller里面新建一个UserController类，  
进行restAPI的书写,api使用url: **api/user/hello**
```$xslt
@Controller
@RequestMapping("api/user")
public class UserController {

    @RequestMapping(value = "hello", method = RequestMethod.GET) 
    @ResponseBody // 要返回json数据
    public Map login(HttpSession httpSession) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        try {
          map.put("errorCode", 0);
          map.put("message", "hello");
        } catch (Exception e) {
            map.put("errorCode", 1);
            map.put("errorMessage", "未知错误");
        }
        return map;
    }
```

11. 一个接口写好，然后配置服务器启动项目  
  1.![3](https://github.com/slipkinem/webContent/blob/master/mind/3.png)  
  2.![3](https://github.com/slipkinem/webContent/blob/master/mind/4.png)  
  3.![3](https://github.com/slipkinem/webContent/blob/master/mind/5.png)  
  然后点击**apply** => **OK**  
项目启动，打开地址栏，追加输入**api/user/hello**，拿到数据OK，后台走通

### 前端部分
12. 后台暂时放置，开始写前端**[前端部分代码](https://github.com/slipkinem/vue-admin)**
#### 前端框架用的VUE，UI框架用的饿了么开源的element_ui
  * 总概况：vue+vue-router+vue-resource+vuex+element_ui
13. **用npm下载vue-cli**
  * 用vue-cli生成webpack项目骨架
  * npm install 下载所有依赖
14. 