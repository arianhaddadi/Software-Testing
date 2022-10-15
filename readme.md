# Software Testing
This Project is an Implementation of Multiple Software Testing Concepts on a Spring Application as Described Below:
1. branch ca1: Unit Testing Using JUnit Framework with *Theories* & *Parametrized* Methods.
2. branch ca2: Unit Testing Using *Dependency Injection* and Using *Test Doubles* (with Mockito Library).
3. branch ca3: Unit Testing Using *Graph Based Coverage Criteria* (e.g., Node Coverage, Prime Path Coverage).
4. branch ca4: Unit Testing Using *Logical Coverage Criteria* (e.g., CC, CACC, CUTPNFP).
5. branch ca5: Unit Testing Using *Input Space Partitioning* & *API Testing*.
6. branch ca6: Unit Testing Using *Mutation Testing* (With Pitest Library) & *UI Testing*.
7. branch ca7: Unit testing Using *Behavior Driven Testing* (With Cucumber Library) & *Performance Testing*.

[//]: # (Spring PetClinic Sample Application [![Build Status]&#40;https://travis-ci.org/spring-projects/spring-petclinic.png?branch=main&#41;]&#40;https://travis-ci.org/spring-projects/spring-petclinic/&#41;)

[//]: # ()
[//]: # (Pet Clinic is a famous and up-to-date sample web application makes it a reasonable choince to practice testing methods on.)

[//]: # ()
[//]: # (## Understanding the Spring Petclinic application with a few diagrams)

[//]: # (<a href="https://speakerdeck.com/michaelisvy/spring-petclinic-sample-application">See the presentation here</a>)

[//]: # ()
[//]: # (## Running petclinic locally)

[//]: # (Petclinic is a [Spring Boot]&#40;https://spring.io/guides/gs/spring-boot&#41; application built using [Maven]&#40;https://spring.io/guides/gs/maven/&#41;. You can build a jar file and run it from the command line:)

[//]: # ()
[//]: # ()
[//]: # (```)

[//]: # (git clone https://github.com/spring-projects/spring-petclinic.git)

[//]: # (cd spring-petclinic)

[//]: # (./mvnw package)

[//]: # (java -jar target/*.jar)

[//]: # (```)

[//]: # ()
[//]: # (You can then access petclinic here: http://localhost:8080/)

[//]: # ()
[//]: # (<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">)

[//]: # ()
[//]: # (Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately &#40;changes to Java source files require a compile as well - most people use an IDE for this&#41;:)

[//]: # ()
[//]: # (```)

[//]: # (./mvnw spring-boot:run)

[//]: # (```)

[//]: # ()
[//]: # (## In case you find a bug/suggested improvement for Spring Petclinic)

[//]: # (Our issue tracker is available here: https://github.com/spring-projects/spring-petclinic/issues)

[//]: # ()
[//]: # ()
[//]: # (## Database configuration)

[//]: # ()
[//]: # (In its default configuration, Petclinic uses an in-memory database &#40;H2&#41; which)

[//]: # (gets populated at startup with data. The h2 console is automatically exposed at `http://localhost:8080/h2-console`)

[//]: # (and it is possible to inspect the content of the database using the `jdbc:h2:mem:testdb` url.)

[//]: # ( )
[//]: # (A similar setup is provided for MySql in case a persistent database configuration is needed. Note that whenever the database type is changed, the app needs to be run with a different profile: `spring.profiles.active=mysql` for MySql.)

[//]: # ()
[//]: # (You could start MySql locally with whatever installer works for your OS, or with docker:)

[//]: # ()
[//]: # (```)

[//]: # (docker run -e MYSQL_USER=petclinic -e MYSQL_PASSWORD=petclinic -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=petclinic -p 3306:3306 mysql:5.7.8)

[//]: # (```)

[//]: # ()
[//]: # (Further documentation is provided [here]&#40;https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources/db/mysql/petclinic_db_setup_mysql.txt&#41;.)

[//]: # ()
[//]: # (## Working with Petclinic in your IDE)

[//]: # ()
[//]: # (### Prerequisites)

[//]: # (The following items should be installed in your system:)

[//]: # (* Java 8 or newer.)

[//]: # (* git command line tool &#40;https://help.github.com/articles/set-up-git&#41;)

[//]: # (* Your preferred IDE )

[//]: # (  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is)

[//]: # (  not there, just follow the install process here: https://www.eclipse.org/m2e/)

[//]: # (  * [Spring Tools Suite]&#40;https://spring.io/tools&#41; &#40;STS&#41;)

[//]: # (  * IntelliJ IDEA)

[//]: # (  * [VS Code]&#40;https://code.visualstudio.com&#41;)

[//]: # ()
[//]: # (### Steps:)

[//]: # ()
[//]: # (1&#41; On the command line)

[//]: # (    ```)

[//]: # (    git clone https://github.com/spring-projects/spring-petclinic.git)

[//]: # (    ```)

[//]: # (2&#41; Inside Eclipse or STS)

[//]: # (    ```)

[//]: # (    File -> Import -> Maven -> Existing Maven project)

[//]: # (    ```)

[//]: # ()
[//]: # (    Then either build on the command line `./mvnw generate-resources` or using the Eclipse launcher &#40;right click on project and `Run As -> Maven install`&#41; to generate the css. Run the application main method by right clicking on it and choosing `Run As -> Java Application`.)

[//]: # ()
[//]: # (3&#41; Inside IntelliJ IDEA)

[//]: # (    In the main menu, choose `File -> Open` and select the Petclinic [pom.xml]&#40;pom.xml&#41;. Click on the `Open` button.)

[//]: # ()
[//]: # (    CSS files are generated from the Maven build. You can either build them on the command line `./mvnw generate-resources` or right click on the `spring-petclinic` project then `Maven -> Generates sources and Update Folders`.)

[//]: # ()
[//]: # (    A run configuration named `PetClinicApplication` should have been created for you if you're using a recent Ultimate version. Otherwise, run the application by right clicking on the `PetClinicApplication` main class and choosing `Run 'PetClinicApplication'`.)

[//]: # ()
[//]: # (4&#41; Navigate to Petclinic)

[//]: # ()
[//]: # (    Visit [http://localhost:8080]&#40;http://localhost:8080&#41; in your browser.)

[//]: # ()
[//]: # ()
[//]: # (## Looking for something in particular?)

[//]: # ()
[//]: # (|Spring Boot Configuration | Class or Java property files  |)

[//]: # (|--------------------------|---|)

[//]: # (|The Main Class | [PetClinicApplication]&#40;https://github.com/spring-projects/spring-petclinic/blob/main/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java&#41; |)

[//]: # (|Properties Files | [application.properties]&#40;https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources&#41; |)

[//]: # (|Caching | [CacheConfiguration]&#40;https://github.com/spring-projects/spring-petclinic/blob/main/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java&#41; |)

[//]: # ()
[//]: # (## Interesting Spring Petclinic branches and forks)

[//]: # ()
[//]: # (The Spring Petclinic "main" branch in the [spring-projects]&#40;https://github.com/spring-projects/spring-petclinic&#41;)

[//]: # (GitHub org is the "canonical" implementation, currently based on Spring Boot and Thymeleaf. There are)

[//]: # ([quite a few forks]&#40;https://spring-petclinic.github.io/docs/forks.html&#41; in a special GitHub org)

[//]: # ([spring-petclinic]&#40;https://github.com/spring-petclinic&#41;. If you have a special interest in a different technology stack)

[//]: # (that could be used to implement the Pet Clinic then please join the community there.)

[//]: # ()
[//]: # ()
[//]: # (## Interaction with other open source projects)

[//]: # ()
[//]: # (One of the best parts about working on the Spring Petclinic application is that we have the opportunity to work in direct contact with many Open Source projects. We found some bugs/suggested improvements on various topics such as Spring, Spring Data, Bean Validation and even Eclipse! In many cases, they've been fixed/implemented in just a few days.)

[//]: # (Here is a list of them:)

[//]: # ()
[//]: # (| Name | Issue |)

[//]: # (|------|-------|)

[//]: # (| Spring JDBC: simplify usage of NamedParameterJdbcTemplate | [SPR-10256]&#40;https://jira.springsource.org/browse/SPR-10256&#41; and [SPR-10257]&#40;https://jira.springsource.org/browse/SPR-10257&#41; |)

[//]: # (| Bean Validation / Hibernate Validator: simplify Maven dependencies and backward compatibility |[HV-790]&#40;https://hibernate.atlassian.net/browse/HV-790&#41; and [HV-792]&#40;https://hibernate.atlassian.net/browse/HV-792&#41; |)

[//]: # (| Spring Data: provide more flexibility when working with JPQL queries | [DATAJPA-292]&#40;https://jira.springsource.org/browse/DATAJPA-292&#41; |)

[//]: # ()
[//]: # ()
[//]: # (# Contributing)

[//]: # ()
[//]: # (The [issue tracker]&#40;https://github.com/spring-projects/spring-petclinic/issues&#41; is the preferred channel for bug reports, features requests and submitting pull requests.)

[//]: # ()
[//]: # (For pull requests, editor preferences are available in the [editor config]&#40;.editorconfig&#41; for easy use in common text editors. Read more and download plugins at <https://editorconfig.org>. If you have not previously done so, please fill out and submit the [Contributor License Agreement]&#40;https://cla.pivotal.io/sign/spring&#41;.)

[//]: # ()
[//]: # (# License)

[//]: # ()
[//]: # (The Spring PetClinic sample application is released under version 2.0 of the [Apache License]&#40;https://www.apache.org/licenses/LICENSE-2.0&#41;.)

[//]: # ()
[//]: # ([spring-petclinic]: https://github.com/spring-projects/spring-petclinic)

[//]: # ([spring-framework-petclinic]: https://github.com/spring-petclinic/spring-framework-petclinic)

[//]: # ([spring-petclinic-angularjs]: https://github.com/spring-petclinic/spring-petclinic-angularjs )

[//]: # ([javaconfig branch]: https://github.com/spring-petclinic/spring-framework-petclinic/tree/javaconfig)

[//]: # ([spring-petclinic-angular]: https://github.com/spring-petclinic/spring-petclinic-angular)

[//]: # ([spring-petclinic-microservices]: https://github.com/spring-petclinic/spring-petclinic-microservices)

[//]: # ([spring-petclinic-reactjs]: https://github.com/spring-petclinic/spring-petclinic-reactjs)

[//]: # ([spring-petclinic-graphql]: https://github.com/spring-petclinic/spring-petclinic-graphql)

[//]: # ([spring-petclinic-kotlin]: https://github.com/spring-petclinic/spring-petclinic-kotlin)

[//]: # ([spring-petclinic-rest]: https://github.com/spring-petclinic/spring-petclinic-rest)
