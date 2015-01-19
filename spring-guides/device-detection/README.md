RUN AND TEST
============

    mvn clean package
    java -jar target/*.jar

or

    mvn clean spring-boot:run


Note
======

By including the Spring Mobile dependency, Spring Boot configures a DeviceResolverHandlerInterceptor and DeviceHandlerMethodArgumentResolver automatically.