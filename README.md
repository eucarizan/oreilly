# [Spring Boot](https://github.com/eucarizan/oreilly-private/blob/main/springboot-rest/README.md)

- [Spring Boot](#spring-boot)
  - [Spring Boot Create](#spring-boot-create)
    - [Create new project](#create-new-project)
    - [Create a REST Controller](#create-a-rest-controller)
      - [Create Books Application](#create-books-application)
      - [Create a REST Controller](#create-a-rest-controller-1)
      - [Books Database](#books-database)
      - [GET Mapping](#get-mapping)
      - [POST Mapping](#post-mapping)
      - [PUT Mapping](#put-mapping)
      - [DELETE Mapping](#delete-mapping)
    - [Handling JSON](#handling-json)
      - [Add the json dependency](#add-the-json-dependency)
      - [Book POJO](#book-pojo)
      - [Update book controller](#update-book-controller)
      - [Updating List Endpoint](#updating-list-endpoint)
      - [Creating a New Book](#creating-a-new-book)
    - [Dependency Injection](#dependency-injection)
      - [Move the logic of `BookController` to a service, Create Book Service](#move-the-logic-of-bookcontroller-to-a-service-create-book-service)
      - [Dependency Injection](#dependency-injection-1)
      - [Refactor the Controller](#refactor-the-controller)
      - [Run and Test the Application](#run-and-test-the-application)

## Spring Boot Create
### Create new project
1. Create a basic application using the Spring Initializr using curl:
    ```
    curl https://start.spring.io/starter.zip -d dependencies=web -d type=gradle-project -d bootVersion=3.0.4 -o demo.zip
    ```

2. Unpack the archive file:
    ```
    tar -xf demo.zip
    ```

3. Run the application using Gradle:
    ```
    .\gradlew bootRun
    ```

4. View the Application
    ```
    curl http://localhost:8080
    ```
    >output:
    ```
    {"timestamp":"2023-03-18T00:14:54.510+00:00","status":404,"error":"Not Found","path":"/"}
    ```

### Create a REST Controller
#### Create Books Application
1. Run the command to create an application using Spring Initializr:
    ```
    curl https://start.spring.io/starter.zip -d groupId=com.oreilly -d artifactId=books -d name=books -d package=com.oreilly.books -d dependencies=web -d javaVersion=17 -d type=gradle-project -d bootVersion=3.0.4 -o books.zip
    ```

2. Unpack the archive file:
    ```
    tar -xf demo.zip && rm books.zip
    ```

3. Run the application:
    ```
    .\gradlew bootRun
    ```

4. View the application:
    ```
    curl http://localhost:8080
    ```
    >output:
    ```
    {"timestamp":"2023-03-18T00:35:25.917+00:00","status":404,"error":"Not Found","path":"/"}
    ```

#### Create a REST Controller
1. Create a new class in the package `com.oreilly.books` called `BookController.java`
2. Add the `@RestController` annotation to the class
3. Add the `@RequestMapping("/books")` annotation to the class

#### Books Database
1. Declare a new variable called books that will hold a `List<String>`. In the constructor for this class, create three new books

#### GET Mapping
1. Create a method to return a list of all the books
2. Add the `@GetMapping` annotation to the list() method
3. Test the Endpoint
    ```
    curl http://localhost:8080/books
    ```

#### POST Mapping
1. Create a method to create a new book and add it to the list of the books.
2. Add the `@PostMapping` annotation to the method
3. Add the `@RequestBody` annotation to the param
4. Test the Endpoint
   >POST: create new book
   ```
   curl -X POST http://localhost:8080/books -d "{\"title\": \"TEST\"}" -H "Content-Type: application/json"
   ```
   >GET: verify if new book is added
   ```
   curl http://localhost:8080/books
   ```
   >output
   ```
   ["Hacking with Spring Boot 2.3","97 Things Every Java Programmer Should Know","Spring Boot: Up and Running","TEST"]
   ```

#### PUT Mapping
1. Create a method to update a book
2. Add the `@PutMapping` annotation to the method
3. Add the `@RequestBody` annotation to the param
4. Test the Endpoint
   >PUT: update a book
   ```
   curl -X PUT http://localhost:8080/books -d "{\"oldtitle\": \"Spring Boot: Up and Running\", \"newtitle\": \"MY NEW TITLE\"}" -H "Content-Type: application/json"
   ```
   >GET: verify if book was updated
   ```
   http://localhost:8080/books
   ```
   >output
   ```
   ["Hacking with Spring Boot 2.3","97 Things Every Java Programmer Should Know","MY NEW TITLE"]
   ```

#### DELETE Mapping
1. Create a method to delete a book
2. Add the `@DeleteMapping` annotation to the method
3. Add the `@RequestParam` annotation to the param
4. Test the Endpoint
   >DELETE: delete a book
   ```
   curl -X DELETE http://localhost:8080/books?title=Spring%20Boot%3A%20Up%20and%20Running
   ```
   >GET: verify if book was deleted
   ```
   http://localhost:8080/books
   ```
   >output
   ```
   ["Hacking with Spring Boot 2.3","97 Things Every Java Programmer Should Know"]
   ```

### Handling JSON
#### Add the json dependency
1. Add `org.springframework.boot:spring-boot-starter-json` in build.gradle

#### Book POJO
1. Create a `Book.java` class in `com.oreilly.books`

#### Update book controller
1. Update the books type from `String` to `Book`
2. Create new book instance

#### Updating List Endpoint
1. Update the return type to `Book`
2. Test the Endpoint
   >GET
   ```
   http://localhost:8080/books
   ```
   >output
   ```
   [
    {
        "title": "Hacking with Spring Boot 2.3",
        "author": "Greg L. Turnquist",
    },
    {
        "title": "97 Things Every Java Programmer Should Know",
        "author": "Kevlin Henney and Trisha Gee",
    },
    {
        "title": "Spring Boot: Up and Running",
        "author": "Greg L. Turnquist ",
    }
   ]
   ```

#### Creating a New Book
1. Update the create method using the `Book` object
2. Test the Endpoint
   >POST: create a new book
   ```
   curl -X POST http://localhost:8080/books -d "{\"title\": \"My Title\", \"author\": \"My Author\"}" -H "Content-Type: application/json"
   ```
   >GET: verify if book was added
   ```
   curl http://localhost:8080/books
   ```
   >output
   ```
   [{"title":"Hacking with Spring Boot 2.3","author":"Greg L. Turnquist"},
    {"title":"97 Things Every Java Programmer Should Know","author":"Kevlin Henney and Trisha Gee"},
    {"title":"Spring Boot: Up and Running","author":"Greg L. Turnquist"},
    {"title":"My Title","author":"My Author"}]
   ```

### Dependency Injection
#### Move the logic of `BookController` to a service, Create Book Service
1. Create a `BookService.java` class in `com.oreilly.books`
2. Move the code from controller to the service
3. Add `@Service` annotation to the class

#### Dependency Injection
1. Add the `BookService` to the `BookController` constructor
>You might come across code where the constructor is annotated with @Autowired. This used to be required, but if there is only a single constructor you don't need to do this any longer.

#### Refactor the Controller
1. Use the `BookService` to update each method

#### Run and Test the Application
1. Run the application
   ```
   .\gradlew bootRun
   ```
   output: notice the order in which constructors were called
   ```
   BookService() called...
   BookController() called...
   ```

2. List
   ```
   curl http://localhost:8080/books
   ```

3. Create
   ```
   curl -X POST http://localhost:8080/books -H "content-type: application/json" -d "{\"title\": \"TEST\",\"author\": \"TEST\"}"
   ```
   >GET: verify if the book was added
   ```
   curl http://localhost:8080/books
   ```
   >output
   ```
   [{"id":0,"title":"Hacking with Spring Boot 2.3","author":"Greg L. Turnquist"},
    {"id":0,"title":"97 Things Every Java Programmer Should Know","author":"Kevlin Henney and Trisha Gee"},
    {"id":0,"title":"Spring Boot: Up and Running","author":"Greg L. Turnquist "},
    {"id":4,"title":"TEST","author":"TEST"}]
   ```

4. Read
   >GET{id}
   ```
   curl http://localhost:8080/books/1
   ```
   >output
   ```
   {"id":1,"title":"Hacking with Spring Boot 2.3","author":"Greg L. Turnquist"}
   ```

5. Update
   >UPDATE
   ```
   curl -X PUT http://localhost:8080/books/4 -H "content-type: application/json" -d "{\"id\": 4,\"title\": \"NEW TEST\",\"author\": \"NEW TEST\"}"
   ```
   >GET: verify if book was updated
   ```
   curl http://localhost:8080/books/4
   ```
   >output
   ```
   {"id":4,"title":"NEW TEST","author":"NEW TEST"}
   ```

6. Delete
   >DELETE
   ```
   curl -X DELETE http://localhost:8080/books/1
   ```
   >GET: verify if book was deleted
   ```
   curl http://localhost:8080/books
   ```
   >output
   ```
   [{"id":2,"title":"97 Things Every Java Programmer Should Know","author":"Kevlin Henney and Trisha Gee"},
    {"id":3,"title":"Spring Boot: Up and Running","author":"Greg L. Turnquist "},
    {"id":4,"title":"NEW TEST","author":"NEW TEST"}]
   ```