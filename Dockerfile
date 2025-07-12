FROM tomcat:10.1-jdk21

# Удалим дефолтные примеры
RUN rm -rf /usr/local/tomcat/webapps/*

# Копируем наш war
COPY target/Servlets-1.0.war /usr/local/tomcat/webapps/ROOT.war
