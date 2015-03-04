# CFM-Server-Side
Car Fleet Manager - Server Side
System for managing car fleet in small business. System supports tracing car locations and provides managing car owners.

Modules:
- cfm-commons - common lib with supporting objects like business pojo objects and serialization objects to JSON by GSON library
- cfm-ear - deployment module contains jboss deployment descriptors, application exceptions and JEE beans definitions for cfm-ejb dao's.
- cfm-ejb - JEE ejb module containing dao, repository and service beans build on Hibernate JPA libs. Module is used just by cfm-web project. In future simple Hibernate JPA access should be replaced by Spring Data JPA database access.
- cfm-web - RESTfull service for managing cars and employees using http interface. Build on Spring with JPA access (without Spring DATA!) and RESTeasy libs. Contains own
- cfm-rsws - JAX RS web service project build on: Spring JAR WS supporting libs, 
- cfm-web-client - web application build for managing cars and employees. Contains dashboards 


All end projects contains other JPA implementations for testing purposes:
cfm-web - Hibernate JPA access injected in JEE EJB.
cfm-web-client - spring JPA access without Spring Data
cfm-rsws - Spring Data JPA and Spring REST MVC build on spring boot firstly (branch "SpringBoot") and on separated libs (branch "SeparatedSpring" merged to master finally)

Every project has to separate repositories from services like in boot data jpa: https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-jpa
