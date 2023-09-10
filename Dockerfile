FROM openjdk:16-alpine
WORKDIR /
COPY calllogsService.jar calllogsService.jar
EXPOSE 8080
CMD ["java","-jar","calllogsService.jar"]