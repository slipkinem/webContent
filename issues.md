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
2. 