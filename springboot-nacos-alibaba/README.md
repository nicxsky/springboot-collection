# Nacos单机模式安装启动（CentOS7） & Springboot集成Nacos   

Nacos server最新下载地址   
https://github.com/alibaba/nacos/releases   

安装（提前安装好JDK环境）   
cd /opt   
wget https://github.com/alibaba/nacos/releases/download/2.0.0-bugfix/nacos-server-2.0.0.tar.gz   
tar -zxvf nacos-server-2.0.0.tar.gz   

启动   
cd /opt/nacos/bin   
./startup.sh -m standalone   

访问控制台   
http://192.168.164.128:8848/nacos/#/login   
默认账号密码：nacos/nacos   

#   
Springboot集成Nacos   

1、实现配置的动态变更   
2、实现服务的注册与发现   

注：配置数据默认存在本地数据库   
NACOS_PATH/data，会发现里边有个derby-data目录，Derby是Java编写的数据库，属于Apache的一个开源项目，我们的配置数据现在就存储在这个库中。   
修改数据源：   
将NACOS_PATH/conf/nacos-mysql.sql中的表结构导入到mysql数据中，   
修改NACOS_PATH/conf/application.properties文件，增加支持mysql数据源配置（目前只支持mysql），添加mysql数据源的url、用户名和密码   

spring.datasource.platform=mysql   

db.num=1   
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_db?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true   
db.user=   
db.password=   
