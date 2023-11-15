## 学习mvn插件
* 内置```pom-4.0.0.xml```
```xml
<project>
  <build>
    <directory>${project.basedir}/target</directory>
    <outputDirectory>${project.build.directory}/classes</outputDirectory>
    <finalName>${project.artifactId}-${project.version}</finalName>
    <testOutputDirectory>${project.build.directory}/test-classes</testOutputDirectory>
    <sourceDirectory>${project.basedir}/src/main/java</sourceDirectory>
    <scriptSourceDirectory>${project.basedir}/src/main/scripts</scriptSourceDirectory>
    <testSourceDirectory>${project.basedir}/src/test/java</testSourceDirectory>
    <resources>
      <resource>
        <directory>${project.basedir}/src/main/resources</directory>
      </resource>
    </resources>
    <testResources>
      <testResource>
        <directory>${project.basedir}/src/test/resources</directory>
      </testResource>
    </testResources>
  </build>
</project>
```
* mvn内置变量
```shell
项目的根目录,即包含 pom.xml 文件的目录	${project.basedir}
项目的 groupId	                    ${project.groupId}
项目的 artifactId	                ${project.artifactId}
项目版本 version                      ${project.version}
项目打包输出文件的名称	                ${project.build.finalName}           默认为 ${project.artifactId}-${project.version}
项目的主源码目录	                    ${project.build.sourceDirectory}     默认为 /src/main/java/	
项目的测试源码目录	                    ${project.build.testSourceDirectory} 默认为 /src/test/java/	
项目构建输出目录	                    ${project.build.directory}           默认为 /target/	
项目主代码编译输出目录	                ${project.outputDirectory}           默认为 /target/classes/	
项目测试代码编译输出目录	            ${project.testOutputDirectory}       默认为 /target/testclasses/
```
* 待学习
```shell
MAVEN - 使用maven-dependency-plugin的应用场景是什么?
# https://blog.csdn.net/goodjava2007/article/details/126519696
```
#### ```dependency```命令行的使用方法
```shell
## 显示该项目的依赖关系树
mvn -gs D:/apache-maven-3.8.3/conf/settings.xml dependency:tree

## 分析项目依赖，确定哪些依赖是已使用已声明的，已使用未声明的，未使用已声明的
mvn -gs D:/apache-maven-3.8.3/conf/settings.xml dependency:analyze

mvn dependency:copy，将配置在插件中的JAR包复制到指定位置
copy的配置：https://maven.apache.org/plugins/maven-dependency-plugin/copy-mojo.html
mvn dependency:copy-dependencies，将项目POM文件中定义的所有依赖及其传递依赖复制到指定位置；
copy-dependencies的配置：https://maven.apache.org/plugins/maven-dependency-plugin/copy-dependencies-mojo.html
mvn dependency:unpack，将配置在插件中的JAR包复制到指定位置，并且将JAR包进行解压缩；
mvn dependency:unpack-dependencies，将项目POM文件中定义的所有依赖及其传递依赖复制到指定位置，并且将JAR包进行解压缩；

## 应用场景
某个特殊的JAR包，无法直接通过MAVEN依赖获取或者说MAVEN仓库内也不存在，那么如何将我们所需要的JAR包打入我们的生产JAR包中呢？使用：mvn dependency:copy 可以解决该问题
某个特殊的JAR包内包含我们需要的文件，或者说我们如何将所需的文件从JAR包中提取出来并放入指定的位置？使用：mvn dependency:unpack 可以解决该问题
```
