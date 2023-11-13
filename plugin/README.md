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
