# SpringBootGraphQL
Back End GraphQL server for read RSS based on SpringBoot 

## Technical Info

The software was built using the following tools/platforms:
- [IBM cloud](http://75.126.254.59:8080/accounts) - with Linux Ubuntu Operational System
- [Java 8](https://www.java.com/pt_BR/) - **THE PROGRAMMING LANGUAGE**
- [Spring Boot 2.1.0](http://spring.io/projects/spring-boot) - The framework I use every day for building nice applications
- [H2 Database](http://www.h2database.com/html/main.html) - Database that can be used as a library http://75.126.254.59:8080/h2
- [Lombok](https://projectlombok.org/) - Framework used to reduce boilerplate code
- [SpringFox](http://springfox.github.io/springfox/) - Library used to generate Swagger Documentation and UI
- [Maven](https://maven.apache.org/) - Just Maven!
- [JUnit](https://junit.org/) - Used for tests
- [AssertJ](http://joel-costigliola.github.io/assertj/) - assertThat() like a *PRO*
- [Mockito](https://site.mockito.org/) - Mocking dependencies
- [Jacoco](https://www.eclemma.org/jacoco/trunk/doc/maven.html) - Test Coverage Library

## Usage

First, you have to generate the `jar` with `mvn`:

```sh
$ ./gradlew clean 
```
or 

```sh
$ ./gradlew build
```

You are ready to run with the command:

```sh
$ ./gradlew bootRun
```
or

```sh
$ cd target/ && java -jar SpringBootGraphQL-1.0.jar
```

The program will start embedded tomcat at port 8080, so you can now navigate to [Graph i QL](http://localhost:8080/graphiql)

Documentation could be reached [HERE](http://localhost:8080/graphiql).

```sh
{
findAllAuthors {
id
firstName
lastName
}
countAuthors
countBooks
findAllBooks {
id
title
pageCount
}
}
```

H2 database console http://75.126.254.59:8080/h2-console/



## Test Coverage

I tried to focus on the application *CORE BUSINESS*, so not all the classes were not tested on purpose.

```sh
./gradlew test
```

1. Test Coverage is 80% of Tested Classes
2. The Coverage is measured by Jacoco library that gives a HTML report that can be reached at `target/site/jacoco/index.html`

## Non-Tested Classes
1. `Exceptions` were not tested.
2. ` Application.java` was nos tested
3. `JpaConfiguration` and `SwaggerConfig` were not tested 
3. `model` package was not tested, but the `Account` class was, because of the business logic methods
4. `DTOs` inside of `request` and `response` packages were not tested
5. `@NotSameAccount` annotation was not tested
