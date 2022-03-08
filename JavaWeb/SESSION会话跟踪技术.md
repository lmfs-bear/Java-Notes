# SESSION会话跟踪技术

Http是无状态的

- 含义：是指协议对于交互性场景没有记忆能力，服务器无法判断两次请求是由同一个客户端还是两个客户端发过来的。

### 会话跟踪技术

- 客户端第一次发请求给服务器，服务器获取session，获取不到则创建新的，然后响应给客户端。
- 下次客户端给服务器发请求时，会把sessionID发给服务器...

![1646739885069](C:\Users\McGrady2002\AppData\Roaming\Typora\typora-user-images\1646739885069.png)

### session保存作用域

![1646740511272](C:\Users\McGrady2002\AppData\Roaming\Typora\typora-user-images\1646740511272.png)





