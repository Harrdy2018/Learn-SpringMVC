### lesson03
* ```EmployeeController.java```
```shell
# validator
# PathVariable注解&ModelAttribute注解
```
* ```Employee2Controller.java```
```shell
# RequestParam注解
# RequestBody注解
```
* ```StudentController.java```
```xml
    <!-- 使用注解方式验证java bean -->
    <!-- javax.validation.validation-api JSR-303批注装饰Java bean -->
    <!--  验证器的一个实现，例如Hibernate Validator -->
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-validator</artifactId>
      <version>5.4.1.Final</version>
    </dependency>
```
```json
 {
  "studentId": 1000,
  "sName": "",
  "grades": [
  ],
  "subjects": [
    "math",
    "chinese"
  ]
}
```
