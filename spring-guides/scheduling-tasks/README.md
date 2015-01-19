RUN AND TEST
============

    mvn clean package
    java -jar target/*.jar

or

    mvn clean spring-boot:run

Note
================

This example is a bit different than the original spring guide. In the original spring guide, @EnableScheduling is added
at ScheduledTasks.java, However, @EnableScheduling is moved to Application.class in order to keep all the configurations in one class.