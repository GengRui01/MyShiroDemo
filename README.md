# Shiro

## 什么是Shiro？

Apache Shiro是一个强大且易用的Java安全框架,执行身份验证、授权、密码和会话管理。使用Shiro的易于理解的API,您可以快速、轻松地获得任何应用程序,从最小的移动应用程序到最大的网络和企业应用程序

* Subject 当前操作用户

    Subject这一概念并不仅仅指人，也可以是第三方进程、后台帐户（Daemon Account）或其他类似事物。它仅仅意味着“当前跟软件交互的东西”

* SecurityManager 

    SecurityManager是Shiro框架的核心，典型的Facade模式，Shiro通过SecurityManager来管理内部组件实例，并通过它来提供安全管理的各种服务

* Realm

    Realm封装了数据源的连接细节，并在需要时将相关数据提供给Shiro。配置多个Realm是可以的，但是至少需要指定一个Realm，用于认证和（或）授权

综上所述，Subject代表了当前用户的安全操作，SecurityManager则管理所有用户的安全操作，Realm充当了Shiro与安全数据源间的“桥梁”或者“连接器”

##Shiro认证



