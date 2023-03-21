### lesson01
#### 利用注解单独进行异常处理
* 加注解
```shell
# @ControllerAdvice(annotations = {CustomizedHandleException.class})
# 增加扫描路径 <context:component-scan base-package="com.sohu.handle"/>
```
* 异常处理
```txt
@ExceptionHandler(Exception.class)
@ResponseBody
public BaseRespBean<Object> handleException(Exception e) {
    LOGGER.info("current class Name={}.", this.getClass().getName());
    LOGGER.error("exception : {}", e.getClass().getName());
    return new BaseRespBean<>(RetCode.SYSTEM_ERROR.getCode(), e.getMessage());
}
```
