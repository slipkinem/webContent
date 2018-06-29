## issues
1. junit测试报XML映射找不到，查看target里面XML没有打包，  
    原因是默认不打包XML，给POM.XML添加以下配置
    ```
    <build>
        <finalName>article</finalName>
    
    
        <resources>
          <resource>
            <directory>src/main/java</directory>
            <includes>
              <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
          </resource>
        </resources>
      </build>
    ```
2.  包命名及内容   
  * po: mybatis生成java对象 Persistent Object
  * dao: Data Access Object 自定义数据访问接口
  * mapper: mybatis生成的数据访问接口
  * model: 自定义java对象 model层
  * vo Value Object用new关键字创建，由GC回收，业务上的数据传递