FROM java:8

MAINTAINER yudq yudq@xxx.com

VOLUME /tmp

ADD docker-0.0.1-SNAPSHOT.jar demo.jar

RUN bash -c 'touch /demo.jar'

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","demo.jar"]
