FROM amazoncorretto:17

CMD ["./gradlew", "clean", "build"]

VOLUME /tmp

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app.jar"]

#docker build --build-arg DEPENDENCY=build/dependency -t bongjaejeong/spring-boot-template --platform linux/amd64
#docker push bongjaejeong/spring-boot-template
#docker pull bongjaejeong/spring-boot-template
#docker run -d --name bj-spring-boot -p 8081:8081 bongjaejeong/spring-boot-template