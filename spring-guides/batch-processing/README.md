RUN AND TEST
============

    mvn clean package
    java -jar target/*.jar

or

    mvn clean spring-boot:run


Note
======
JMXAutoConfiguration has to be excluded in order to run successfully. Although I am not quite clear about the reason at this moment, I
suspect that Spring-Batch might contain some services which cannot be exported by the MBeanExporter cleanly with tweaks.
