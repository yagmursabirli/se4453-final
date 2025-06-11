# 1. Maven ile build et
FROM maven:3.9.6-amazoncorretto-8-debian-bookworm AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# 2. Tomcat + SSH servisli final image
FROM tomcat:10-jre8-openjdk-buster

# SSH kurulumu
RUN apt-get update && \
    apt-get install -y openssh-server && \
    mkdir /var/run/sshd && \
    echo 'root:docker' | chpasswd

# SSH bağlantıları için root login'e izin ver
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

# SSH ve Tomcat portlarını aç
EXPOSE 22
EXPOSE 8080

# WAR dosyasını kopyala
COPY --from=build /build/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Startup scripti kopyala
COPY startup.sh /usr/local/bin/startup.sh
RUN chmod +x /usr/local/bin/startup.sh

# SSH ve Tomcat’i başlat
CMD ["/usr/local/bin/startup.sh"]
