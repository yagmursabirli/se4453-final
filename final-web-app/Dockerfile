# Build aşaması
FROM maven:3.9.6-amazoncorretto-8-debian-bookworm AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Uygulama imajı
FROM tomcat:10-jre8-openjdk-buster

# SSH yükleniyor
RUN apt-get update && apt-get install -y openssh-server \
    && mkdir /var/run/sshd

# Root için parola belirleniyor (sabit veya arg ile)
RUN echo 'root:docker' | chpasswd

# SSH bağlantısı için gerekli port
EXPOSE 22

# Tomcat portu
EXPOSE 8080

# WAR kopyalanıyor
COPY --from=build /build/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# SSH başlatılıyor
CMD service ssh start && catalina.sh run
