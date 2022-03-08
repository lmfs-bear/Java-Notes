# Thymeleaf - 视图模板技术

### 快速入门

1 添加thymeleaf的jar包

2 新建一个servlet类viewBaseServlet

3 在web.xml文件中添加配置

```xml
<context-param>
    <param-name>view-prefix</param-name>
    <param-value>/</param-value>
</context-param>
<context-param>
    <param-name>view-suffix</param-name>
    <param-value>.html</param-value>
</context-param>
```

- 配置前缀view-prefix

- 配置后缀view-suffix

4 使得我们的servlet继承viewBaseservlet

![1646743332009](D:\Study\3.2\Java\Notes\JavaWeb\pictures\%5CUsers%5CMcGrady2002%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1646743332009.png)