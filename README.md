# Data-visualization

对 mysql 中的数据进行可视化，现为黑木耳项目的后端，主要是展示json数据，为前端提供数据访问接口。

**使用到的技术**：

Springboot+Mybatis+Echarts+Mysql+Thymeleaf

具体细节见博客：
https://www.cnblogs.com/jingpeng77/p/12997387.html

## 注意

中文乱码情况在右下角点击通过编码保存，选中修改编码格式为 `utf-8` 保存即可

## 启动

启动项目之前确保服务器上的 MySQL 服务已启动。

Windows 检查方法：Win+R，输入`services.msc`，找到名为 MYSQL57 的 Name，点击一下在左边 Services(Local)窗格内点击`start`开启 MySQL 服务即可。

使用 VSCode 打开本代码，自动识别为 Springboot 类型项目。

点击左边的 `[调试和运行]`三角形按钮之后点击该窗格内的绿色三角，即可运行起来。

Tomcat started on port(s): 8088 (http)，相关配置写在 application.properties 文件中。

前端端口 `8080`

## 部署 war 包到 Tomcat

### WAR 
WAR(Web Archive file)网络应用程序文件，是与平台无关的文件格式，它允许将很多文件组合成一个压缩文件。war专用在web方面，一个war包可以理解为一个web项目，里面是项目的所有东西。

一个标准的应用目录如下：

- *.html、*.jsp ：HTML和JSP页面，以及应用程序的客户端浏览器必须可见的其他文件（例如JavaScript，样式表文件和图像）。 在较大的应用程序中，您可以选择将这些文件划分为子目录层次结构，但是对于较小的应用程序，通常只为这些文件维护一个目录要简单得多
- /WEB-INF/web.xml：这是一个XML文件，描述了组成应用程序的servlet和其他组件，以及您希望服务器为您强制执行的所有初始化参数和容器管理的安全性约束。
- /WEB-INF/classes/：此目录包含应用程序所需的所有Java类文件（和相关资源），包括servlet和非servlet类，这些文件未合并到JAR文件中。 如果将类组织为Java包，则必须在 /WEB-INF/classes/下的目录层次结构中反映出来。 例如，一个名为com.mycompany.mypackage.MyServlet 的 Java 类将需要存储在一个名为 /WEB-INF/classes/com/mycompany/mypackage/MyServlet.class 的文件中。
- /WEB-INF/lib/：此目录包含 JAR 文件，这些文件包含您的应用程序所需的 Java 类文件（和相关资源），例如第三方类库或 JDBC 驱动程序。

使用 maven 可以把源代码打包输出如上文件。

### Tomcat

tomcat 安装包中各目录作用：

先来看一下，tomcat各目录的作用：

- `/bin`  存放启动和关闭tomcat的脚本文件

- `/conf` 存放Tomcat服务器的各种配置文件，其中包括server.xml（Tomcat的主要配置文件）、tomcat-user.xml和web.xml等配置文件

- `/lib`  存放tomcat与web应用的Jar包

- `/logs` 存放Tomcat的日志文件

- `/temp` 存放Tomcat运行时候产生的临时文件

- `/webapps`  当发布Web应用程序的时候，通常把Web应用程序的目录以及文件放到这个目录下

- `/work` Tomcat将JSP产生的Servlet源文件和字节码存放在这个文件目录下

参考：[如何在windows上部署war包到tomcat服务器](https://blog.51cto.com/u_14629396/5643098)

### 打包构建

本项目中增加的操作：

1. 修改 pom.xml 文件
```xml
<packaging>jar</packaging>
```
改为
```xml
<packaging>war</packaging>
```
若没有则手动添加即可（我这里便没有手动添加的）

2. 移除嵌入式 tomcat 插件

因为 spring-boot-starter-web 依赖中有Tomcat 的依赖，
所以移除，在 pom.xml 里找到 spring-boot-starter-web 依赖，为war需要发在我们服务器上的 tomcat 中，内置的 tomcat 不需要，可以如下在 maven 中配置屏蔽，在其中添加如下代码。这里通过scope作用域为provided来排除tomcat包，springboot框架中有包含tomcat包，scope默认的作用于是compile，编译，测试，运行；

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!--排除内部的Tomcat-->
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </exclusion>
    </exclusions>
</dependency>
```

3. 添加servlet-api的依赖

在 pom.xml 中新增以下依赖：
```xml
<!--打war包引入的下面依赖-->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
    <scope>provided</scope>
</dependency>
```
修改打包默认名称

配置 configuration 中的 finalName，就是打包后的默认名称。进行名称规范合理化。spring-boot-maven-plugin 插件中配置程序入口，如果上面我们屏蔽了 main入口，但是 tomcat 还是通过启动类作为入口，因此需要配置下启动 mainClass：
```xml
<build>
    <plugins>
        <plugin>
            <configuration>
                <finalName>bigData</finalName><!-- 打包后的war名称-->
            </configuration>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <!--war包-执行程序入口 -->
            <configuration>
                <mainClass>com.zjp.echartsdemo.EchartsdemoApplication</mainClass>
            </configuration>
        </plugin>
    </plugins>
</build>
```

参考：[springboot打war包，成功部署](https://blog.csdn.net/qq_40036754/article/details/127328473)


4. 修改项目默认启动类

我们需要类似于 web.xml 的配置方式来启动 Spring 上下文了，在 Application 类的同级添加一个(自定义的)SpringBootStartApplication类

启动类继承 SpringBootServletInitializer 类并重写 configure() 方法，或新建类继承 SpringBootServletInitializer 类重写 configure() 方法。

例如：

```java
package com.single.zxblog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
/**
 * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
 */
public class ZxblogApplication extends SpringBootServletInitializer {
    // 可以注释掉main入口，屏蔽
    public static void main(String[] args) {
        SpringApplication.run(ZxblogApplication.class, args);
    }
    
    // 添加这个
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(ZxblogApplication.class);
    }
}
```

5. 使用VsCode，在左边选中 资源管理器 tab，展开下面的 MAVEN 下拉菜单，点击 package 后面的三角，失败。原因可能是 vscode 未配置好 Maven 的路径。

#### 打包
在项目根目录下（即包含pom.xml的目录），在命令行里输入：

`mvn clean package -Dmaven.test.skip=true`

打包打印信息：

```c
[INFO] Replacing main artifact with repackaged archive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:35 min
[INFO] Finished at: 2023-03-26T22:53:44+08:00
[INFO] ------------------------------------------------------------------------
```

等待打包完成，出现 [INFO] BUILD SUCCESS 即为打包成功。

war 包里面的内容就是 META-INF 和 WEB-INF 部分

6. 把 target 目录下的生成的 `bigData.war` 包**手动复制**放在 `apache-tomcat-xxx/webapps/` 下就行了，启动 `tomcat`，即可自动解压部署，在浏览器访问的 `url`

`http://localhost:[端口号]/[打包项目名]/`

就是 `localhost:8080/bigData`

我写了一个api接口，运行成功后正常在浏览器输入

`localhost:8080/bigData/getData/`

当然还有其他的方法，配置 `apache-tomcat-xxx/conf/server.xml` 文件

用记事本打开上述文件，并找到 <Host> 节点添加下面的代码，如下：

```xml
<Context path="/bigdata" docBase="D:/Git/Data-visualization/target/bigData.war" reloadable="true"></Context>
```
- path：war 包解压后的项目名称，也就是访问路径（例：http://localhost:8080/bigdata/）
- docBase：war 包的路径（需要把 war 包的后缀名加上，如上图所示，避免报错）
- reloadable：自动加载

保存上述修改后，双击 apache-tomcat-xxx\bin\startup.bat 启动 Tomcat 即可。启动成功后在网页地址栏输入 http://localhost:8080/bigdata/ 即可成功访问。

还有一种方法，使用 GUI 界面，参考：[手把手教你快速开发部署一个 Java 后端 Hello world 应用](https://blog.csdn.net/m0_72885838/article/details/125959957)

细心的你会发现启动 Tomcat 后，产生的日志有乱码的情况出现，不要慌，这并不影响你项目的正常运行。这是由于编码不一致引起的，Tomcat 启动后默认编码 UTF-8，而Windows的默认编码是 GBK 。因此你想让其不乱码，只需配置 apache-tomcat-xxx\conf\logging.properties 的编码格式即可，如下：

logging.properties 文件内容：
```properties
...
java.util.logging.ConsoleHandler.encoding = GBK
```

参考：[Tomcat部署war包的方法（图文搭配讲解，亲测有效）](https://www.cnblogs.com/dongfangzhaoyue/p/16837497.html)

## jar 包部署

- 还原main入口(springboot模板生成的main，不做任何修改，如果屏蔽了，把注释打开)

- 去掉排除tomcat的配置(默认就不没有这个配置)。同样这一步也是针对打war添加了排除tomcat的配置，直接删除就行

- 使用maven-jar-plugin插件打包，指定程序入口和各种包含|排除项，这里通过jar插件配置一些项，对配置文件和main入口指定：

```xml
<!--打jar包-->
 <plugin>
 <groupId>org.apache.maven.plugins</groupId>
 <artifactId>maven-jar-plugin</artifactId>
 <version>.</version>
 <configuration>
  <archive>
  <manifest>
   <!--<addClasspath>true</addClasspath>-->
   <!--<classpathPrefix>lib/</classpathPrefix>-->
   <!--main入口-->
   <mainClass>com.platform.WebApplication</mainClass>
  </manifest>
  </archive>
  <!--包含的配置文件-->
  <!--<includes>-->
  <!--<include>*.yml</include>-->
  <!--<include>*.properties</include>-->
  <!--<include>templates/**</include>-->
  <!--<include>static/**</include>-->
  <!--<include>*.xml</include>-->
  <!--</includes>-->
 </configuration>
 </plugin>
```

- 最后，修改 pom.xml，把入口项目的 packaging 的 war 为 jar：
```xml
<packaging>jar</packaging>
```

之后还是执行 mvn clean package，就会在target下面自动生成 bigData.jar 包

在命令行运行: `java -jar bigData.jar`

即可，或者 

`java -jar bigData.jar --spring.profiles.active=default`

优化启动脚本，改为后台启动，尾部添上&，可不进入运行终端。并将日志输出到 springboot.log

`nohup java -jar SpringBootTest-0.0.1-SNAPSHOT.jar > springboot.log &
`

完善脚本，启动时，如果存在已经启动的服务，先关闭，再启动

创建脚本

`vim start.sh`

```sh
# 关闭程序
# fileName为jar包的名称
fileName=SpringBootTest-0.0.1-SNAPSHOT.jar
pid=$(ps -ef | grep $fileName| grep -v "grep" | awk '{print $2}')
kill -9 $pid

# 启动项目
nohup java -jar $fileName > springboot.log &

```
之后，启动项目就可以

`sh start.sh`

还需要压缩 jar 包，步骤参考 [1秒将本地SpringBoot项目jar包部署到Linux环境（超详细](https://blog.csdn.net/weixin_43811294/article/details/128810233?spm=1001.2101.3001.6650.1&utm_medium=distribute.wap_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7EPosition-1-128810233-blog-125078076.wap_relevant_featuresortv7&depth_1-utm_source=distribute.wap_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7EPosition-1-128810233-blog-125078076.wap_relevant_featuresortv7)

### 查看服务器 java 的端口

`ps aux|grep java`

删除某一进程。在-9后面空一格，填上查到的标识号。

`kill -9 [PID]`

### windows 平台查看端口号占用的 PID： 

`netstat -aon|findstr 8088`

### 查看是什么程序名称：

`tasklist|findstr '9800'`

### 杀死进程：

`taskkill /t /f /pid 9800`

来结束端口占用的进程

参考：https://blog.csdn.net/weixin_43518498/article/details/126612283

## 源仓库代码

https://github.com/masterzjp/Data-visualization

## 搭配的前端代码是

项目名为 `FrontEndofBlackFungus` 的文件夹

在本地的位置：`E:\Doctor1\Lab\实验室大课题\张老师大数据平台\FrontEndofBlackFungus`

GitHub 位置：https://github.com/JevenM/FrontEndofBlackFungus


