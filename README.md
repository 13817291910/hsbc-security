# HSBC 身份认证与管理系统MVP
一个实现了用户创建删除，登录退登，角色创建与绑定用户等功能的简易版身份认证与管理系统

# 项目技术栈
- java
- spring boot
- caffeine
- spring security crypto
- lombok
- aspectj

本项目使用**java**编码，**maven**管理jar包依赖，整体采用mvc分层架构，并用**springboot**作为主体开发框架,构建Rest API，与Bean管理及构建单元测试，使用**spring security crypto**进行密码随机加盐与hash,使用**caffeine**本地存储用户与角色等对象，使用**lombok**注解自动生成构造器,getter和setter方法, 使用**aspectJ**创建auth切面

# 作者
- 阚迪 [邮箱](kd13817291910@gmail.com) 
- 多年没用java写过业务代码了,本次assignment挑战一下，望批评指正
