# Servlet-保存作用域

### 1．保存作用域

原始情况下，保存作用域我们可以认为有四个: page(页面级别，现在几乎不用)，request(一次请求响应范围），lsession , application 。

- request：每一次请求都是一个新的 request 对象，如果在 web 组件之间需要共享同一个请求中的数据，只能使用请求转发。
- session：每一次会话都是一个新的 session 对象，如果如果需要在一次会话中的多个请求之间需要共享数据，只能使用session。
- application：应用对象，Tomcat启动到关闭，表示一个应用，在一个应用中有且只有一个 application 对象，作用于整个 Web 应用，可以实现多次会话之间的数据共享。



使用
```
设置作用域中的共享数据（保存数据）
作用域对象.setAttribute(String name,Object value);
获取作用域中的共享数据（获取数据）
Object value=作用域对象.getAttribute(String name);
删除作用域中的指定的共享数据（删除数据）
作用域对象.removeAttribute(String name);
```





