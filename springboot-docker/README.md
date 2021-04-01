# springboot-docker   
Springboot集成Docker   

欢迎学习交流   

##Dockerfile解释：   
1、FROM:指明当前镜像继承的基镜像,编译当前镜像时候会自动下载基镜像   
2、MAINTAINER:当前镜像的作者和邮箱,使用空格隔开   
3、VOLUME:挂载目录   
4、ADD:从当前工作目录复制文件到镜像目录中并重新命名   
5、RUN:在当前镜像上执行Linux命令,这里我执行了2个run指令,第二个run指令是为了解决容器和宿主机时间不一致的问题   
6、EXPOSE:监听的端口号   
7、ENTRYPOINT:让容器像一个可执行程序一样运行  

**注意
VOLUME 指定了临时文件目录为/tmp。其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp。该步骤是可选的，如果涉及到文件系统的应用就很有必要了。/tmp目录用来持久化到 Docker 数据文件夹，因为 Spring Boot 使用的内嵌 Tomcat 容器默认使用/tmp作为工作目录
项目的 jar 文件作为 “app.jar” 添加到容器的
ENTRYPOINT 执行项目 app.jar。为了缩短 Tomcat 启动时间，添加一个系统属性指向 “/dev/./urandom” 作为 Entropy Source

如果是第一次打包，它会自动下载java 8的镜像作为基础镜像，以后再制作镜像的时候就不会再下载了。

##编译、运行：
1、编译   
mvn clean install -DskipTests docker:build  
或者  
点击idea的maven插件中的docker:build  

2、编译成功   
查看/src/resources/static/img/docker_build_succ.jpg   

3、查看镜像   
docker images   

4、运行   
docker run -p 8080:8080 -v /logs/demo:/logs/demo -d demo:1.0.0 --name first-demo   
docker run --name=first-demo -p 80:80 -itd demo:1.0.0   

5、相关命令
查看容器   
docker ps -l   
docker ps -a   

启动容器   
docker start {CONTAINER ID}   

停止、删除容器（-f强制）  
docker stop {CONTAINER ID}   
docker rm {CONTAINER ID}   
docker rm -f {CONTAINER ID}   
删除全部容器   
docker rm $(docker ps -a -q)   

查看镜像   
docker images   
删除镜像   
docker rmi {image id}   
删除全部镜像   
docker rmi $( docker images -q)   

6、接口调用   
查看/src/resources/static/img/api_succ.jpg   


##问题&解决：
1、docker远程连接失败,提示我们连接被拒绝(failed: Connection refused: connect)   
docker 开启2375端口，提供外部访问docker   
vim /usr/lib/systemd/system/docker.service   

ExecStart修改为   
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock   

重新加载配置   
systemctl daemon-reload   
重启docker   
systemctl restart docker   
查看状态   
systemctl status docker   
netstat -anp|grep 2375   

2、build报错   
[ERROR] Failed to execute goal com.spotify:docker-maven-plugin:1.0.0:build (default-cli) on project docker: Exception caught: com.spotify.docker.client.shaded.com.fasterxml.jackson.databind.JsonMappingException: Can not construct instance of com.spotify.docker.client.messages.RegistryAuth: no String-argument constructor/factory method to deserialize from String value ('desktop')   
[ERROR]  at [Source: N/A; line: -1, column: -1] (through reference chain: java.util.LinkedHashMap["credStore"])   
[ERROR] -> [Help 1]   
[ERROR]    
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.   
[ERROR] Re-run Maven using the -X switch to enable full debug logging.   
[ERROR]    
[ERROR] For more information about the errors and possible solutions, please read the following articles:   
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException   

检查docker服务是否启动，telnet {ip} 2375   
检查com.spotify的版本配置   
https://mvnrepository.com/artifact/com.spotify/docker-maven-plugin   

3、IPv4 forwarding is disabled. Networking will not work   
vim /etc/sysctl.conf   
`# 配置转发`   
net.ipv4.ip_forward=1   
`# 重启服务，让配置生效`   
systemctl restart network   
`# 查看是否成功，如果返回为net.ipv4.ip_forward = 1则表示成功`   
sysctl net.ipv4.ip_forward   
`# 重启docker服务`    
service docker restart   
`# 启动xxx容器`    
docker start xxx   
`# 进入xxx容器`   
docker attach xxx   
`# 获取百度信息`   
curl baidu.com   

4、docker attach卡住   
[root@ydq ~]# docker exec -it ea2cb59ca578 /bin/bash   
root@ea2cb59ca578:/# curl baidu.com   
`<html>`   
`<meta http-equiv="refresh" content="0;url=http://www.baidu.com/">`   
`</html>`   

https://hanquan.blog.csdn.net/article/details/104105950   

5、肉鸡问题   
https://blog.csdn.net/qq_42982923/article/details/108165578   

6、docker run 和 start 的区别   
https://www.jianshu.com/p/73099cb02cad   


