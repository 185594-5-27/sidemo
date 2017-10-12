# sidemo

## 一、开发前准备
    开发工具：Intellij（java web 开发工具）、Maven（jar包的管理工具）、navicate（mysql数据库可视化工具）；
    开发环境：MySQL数据库(5.6及以上版本)、JDK1.8（项目中使用到了lambda表达式因此必须要1.8以上的版本才可以支持）；
## 二、运行整个项目
    1、打开navicate执行src/main/resources/sql底下的hyll_springboot.sql脚本，成功创建数据库，并初始化数据。
    2、打开Intellij从github上导入该项目，并配置好JDK，maven，且成功导入jar包以后修改src/main/resources/application-dev.properties的数据库连接配置：
    spring.datasource.url=jdbc:mysql://10.6.71.236:3306/hyll_springboot?characterEncoding=utf-8（此段改为你的数据库连接的地址）
    spring.datasource.username=root（数据库账号）
    spring.datasource.password=haoyunll123（数据库密码）
