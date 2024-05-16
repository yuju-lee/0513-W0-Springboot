## 0 주차 StringBoot 키포인트 정리 (240513~240514)

- API란?
    
    - **API**(application programming interface)는 다른 소프트웨어 시스템과 통신하기 위해 따라야 하는 규칙을 정의합니다.

- RESTful API란?
    
    - Representational State Transfer(REST)는 API 작동 방식에 대한 조건을 부과하는 소프트웨어 아키텍처입니다.
    REST는 처음에 인터넷과 같은 복잡한 네트워크에서 통신을 관리하기 위한 지침으로 만들어졌습니다. 
    REST 아키텍처 스타일을 따르는 API를 REST API라고 합니다. 
    REST 아키텍처를 구현하는 웹 서비스를 RESTful 웹 서비스라고 합니다.

- MVC 디자인 패턴이란?
    
    - MVC란 Model-View-Controller의 약자로, 소프트웨어 디자인 패턴 중 하나입니다.
    - MVC 패턴은 소프트웨어를 구성하는 요소들을 Model, View, Controller로 구분하여 각각의 역할을 분리합니다.
    
    - Model
    
      - 데이터와 비즈니스 로직을 담당합니다.
      
      - 데이터베이스와 연동하여 데이터를 저장하고 불러오는 등의 작업을 수행합니다.
    
    - View
    
      - 사용자 인터페이스를 담당합니다.
      
      - 사용자가 보는 화면과 버튼, 폼 등을 디자인하고 구현합니다.
    
    - Controller
    
      - Model과 View 사이의 상호작용을 조정하고 제어합니다.
      
      - 사용자의 입력을 받아 Model에 전달하고, Model의 결과를 바탕으로 View를 업데이트합니다.
      
- Front Controller의 동작과정
  
  ![ctrllr](https://miro.medium.com/v2/resize:fit:720/format:webp/0*qmaU6vkJt3IKGPua.png)
  
  1. **Client(브라우저)**에서 **HTTP 요청**이 들어오면 **DispatcherServlet** 객체가 요청을 분석합니다.
  2. **DispatcherServlet** 객체는 분석한 데이터를 토대로 **Handler mapping**을 통해 **Controller**를 찾아 요청을 전달해 줍니다.
      
      - **Handler mapping** 에는 API path 와 Controller 메서드가 매칭되어 있습니다.
      
      ```java
      @RestController
      public class HelloController {
          @GetMapping("/api/hello")
          public String hello() {
              return "Hello World!";
          }
      }
      ```
      
      - API path 즉, URL을 Controller에 작성하는 방법은 @Controller 애너테이션이 달려있는 클래스를 생성한 뒤 @GetMapping 처럼 요청한 HTTP Method 와 일치하는 애너테이션을 추가한 메서드를 구현합니다.
          - URL은 `@GetMapping("/api/hello")` 이처럼 해당 애너테이션의 속성값으로 전달해주면 됩니다.
          - 해당 메서드명은 URL을 매핑하는데 영향을 미치지 않음으로 자유롭게 정해도 상관 없습니다.
      - 이제는 직접 Servlet을 구현하지 않아도 DispatcherServlet에 의해 간편하게 HTTP 요청을 처리할 수 있게 되었습니다.
  3. **Controller** → **DispathcerServlet**
      - 해당 Controller는 요청에 대한 처리를 완료 후 처리에 대한 결과 즉, 데이터('**Model**')와 '**View' 정보**를 전달합니다.
  4. **DispatcherServlet** → **Client**
      - ViewResolver 통해 View에 Model을 적용하여 View를 Client에게 응답으로 전달합니다.


- Jackson 라이브러리
  
    - Jackson은 `JSON` 데이터 구조를 처리해주는 라이브러리 입니다.
        - **Object**를  `JSON` 타입의 **String**으로 변환해줄 수 있습니다.
        - `JSON` 타입의 **String**을 **Object**로 변환해줄 수 있습니다.
    - Spring은 **3.0**버전 이후로  `Jacskon`과 관련된 **API**를 제공함으로써, 우리가 직접 소스 코드를 작성하여 `JSON` 데이터를 처리하지 않아도 자동으로 처리해주고 있습니다.
        - 따라서 SpringBoot의 `starter-web`에서는 default로 Jackson 관련 라이브러리들을 제공하고 있습니다.
        - 직접 `JSON` 데이터를 처리해야할 때는 Jackson 라이브러리의 ObjectMapper를 사용할 수 있습니다.
     
- DTO란 무엇일까?

  - 이름에서도 알 수 있듯이 DTO(Data Transfer Object)는 데이터 전송 및 이동을 위해 생성되는 객체를 의미합니다.
  - Client에서 보내오는 데이터를 객체로 처리할 때 사용됩니다.
  - 또한 서버의 계층간의 이동에도 사용됩니다.
      - 지금은 계층간의 이동이라는 용어에 대해  잘 이해가 되지 않겠지만 이후 강의를 통해 자연스럽게 이해가 되실겁니다.
  - 그리고 DB와의 소통을 담당하는 Java 클래스를 그대로 Client에 반환하는 것이 아니라 DTO로 한번 변환한 후 반환할 때도 사용됩니다.

- DBMS

  - DBMS 는 ‘***Database Management System***’ 의 약자로 Database를 관리하고 운영하는 소프트웨어를 의미합니다.
 
- RDBMS
  
  - RDBMS는 ‘***Relational DBMS***’의 약자로 관계형 데이터베이스라고 불립니다.
  - RDBMS는 테이블(table)이라는 최소 단위로 구성되며, 이 테이블은 열(column)과 행(row)으로 이루어져 있습니다.
  - 테이블간 FK(Foreign Key)를 통해 다른 데이터를 조합해서 함께 볼수 있다라는 장점이 있습니다.
 


## SQL 과제

> 수강생을 관리하는 MANAGER 테이블을 만들어보세요.

  > - 컬럼은 총 id, name, student_code 입니다.
  > - id는 bigint 타입이며 PK입니다.
  > - name은 최소 2자 이상, varchar 타입, not null 입니다.
  > - student_code는 STUDENT 테이블을 참조하는 FK이며 not null 입니다.
  > - FK는 CONSTRAINT 이름을 ‘manager_fk_student_code’ 로 지정해야합니다.

     CREATE TABLE IF NOT EXISTS MANAGER(
            id bigint primary key comment 'id',
            name varchar(2) not null, CONSTRAINT check_min_cha CHECK ( (LENGTH(name) > 1 )),
            student_code varchar(100) not null comment '학생 코드',
            foreign key(student_code) references STUDENT(student_code)
        );

> ALTER, MODIFY를 이용하여 MANAGER 테이블의 id 컬럼에 AUTO_INCREMENT 기능을 부여하세요.

```
ALTER TABLE MANAGER MODIFY id INT NOT NULL AUTO_INCREMENT;
```

> JOIN을 사용하여 managerA가 관리하는 수강생들의 이름과 시험 주차 별 성적을 가져오세요.
```
SELECT s.name, e.exam_seq, e.score
FROM MANAGER m
         JOIN STUDENT s ON m.student_code = s.student_code
         JOIN exam e ON e.student_code = m.student_code
where m.name = 'managerA' ;
```
> STUDENT 테이블에서 s1 수강생을 삭제했을 때 EXAM에 있는 s1수강생의 시험성적과 MANAGER의 managerA가 관리하는 수강생 목록에 자동으로 삭제될 수 있도록 하세요.

  > - ALTER, DROP, MODIFY, CASCADE 를 사용하여 EXAM, MANAGER 테이블을 수정합니다.
```
ALTER TABLE MANAGER DROP CONSTRAINT manager_ibfk_1;
ALTER TABLE EXAM DROP CONSTRAINT exam_fk_student_code;

# MANAGER 테이블에 새로운 외래 키 제약 조건 추가
ALTER TABLE MANAGER
    ADD CONSTRAINT manager_ibfk_1
        FOREIGN KEY (student_code)
            REFERENCES STUDENT(student_code)
            ON DELETE CASCADE;

# EXAM 테이블에 새로운 외래 키 제약 조건 추가
ALTER TABLE EXAM
    ADD CONSTRAINT exam_fk_student_code
        FOREIGN KEY (student_code)
            REFERENCES STUDENT(student_code)
            ON DELETE CASCADE;

# STUDENT 테이블에서 특정 학생 삭제
DELETE FROM STUDENT
WHERE student_code = 's1';
```




## Week 0 Summary of StringBoot key points (240513~240514) / (Machine Translated)

- What is API?
    
     - **API** (application programming interface) defines the rules that must be followed to communicate with other software systems.

- What is RESTful API?
    
     - Representational State Transfer (REST) is a software architecture that imposes conditions on how an API operates.
     REST was initially created as a guideline for managing communications in complex networks such as the Internet.
     An API that follows the REST architectural style is called a REST API.
     Web services that implement REST architecture are called RESTful web services.

- What is MVC design pattern?
    
     - MVC stands for Model-View-Controller and is one of the software design patterns.
     - The MVC pattern divides the elements that make up software into Model, View, and Controller and separates each role.
    
     -Model
    
       - Responsible for data and business logic.
      
       - Perform tasks such as saving and loading data in conjunction with the database.
    
     -View
    
       - Responsible for the user interface.
      
       - Design and implement the screens, buttons, and forms that users see.
    
     -Controller
    
       - Coordinate and control the interaction between Model and View.
      
       - Receives user input and passes it to the Model, and updates the View based on the Model’s results.
      
- Front Controller operation process
  
   ![ctrllr](https://miro.medium.com/v2/resize:fit:720/format:webp/0*qmaU6vkJt3IKGPua.png)
  
   1. When an **HTTP request** comes in from **Client (browser)**, the **DispatcherServlet** object analyzes the request.
   2. The **DispatcherServlet** object finds the **Controller** through **Handler mapping** based on the analyzed data and delivers the request.
      
       - **Handler mapping** matches the API path and Controller method.
      
       ```java
       @RestController
       public class HelloController {
           @GetMapping("/api/hello")
           public String hello() {
               return "Hello World!";
           }
       }
       ```
      
       - The method of writing an API path, or URL, in a Controller is to create a class with the @Controller annotation and then implement a method such as @GetMapping that adds an annotation that matches the requested HTTP Method.
           - The URL can be passed as a property value of the annotation like this: `@GetMapping("/api/hello")`.
           - The method name does not affect URL mapping, so you can freely decide it.
       - Now you can easily process HTTP requests through DispatcherServlet without having to implement a Servlet yourself.
   3. **Controller** → **DispathcerServlet**
       - After the controller completes the processing of the request, it delivers the processing results, that is, data ('**Model**') and '**View' information**.
   4. **DispatcherServlet** → **Client**
       - Apply the Model to the View through ViewResolver and deliver the View to the Client as a response.


- Jackson library
  
     - Jackson is a library that processes the `JSON` data structure.
         - You can convert **Object** to **String** of `JSON` type.
         - You can convert **String** of `JSON` type to **Object**.
     - Spring has provided **API** related to `Jacskon` since version **3.0**, automatically processing `JSON` data without us having to write source code ourselves.
         - Therefore, SpringBoot's `starter-web` provides Jackson-related libraries by default.
         - When you need to process `JSON` data directly, you can use the ObjectMapper of the Jackson library.
     
-What is DTO?

   - As the name suggests, DTO (Data Transfer Object) refers to an object created for data transfer and movement.
   - Used when processing data sent from the client as an object.
   - It is also used to move between server tiers.
       - You may not understand the term inter-class movement well now, but you will understand it naturally through later lectures.
   - It is also used to convert the Java class responsible for communication with the DB to a DTO and then return it, rather than returning it to the client as is.

-DBMS

   - DBMS stands for ‘***Database Management System***’ and refers to software that manages and operates a database.
 
- RDBMS
  
   - RDBMS is an abbreviation for ‘***Relational DBMS***’ and is called a relational database.
   - RDBMS is composed of the minimum unit called a table, and this table consists of columns and rows.
   - The advantage is that different data can be combined and viewed together through FK (Foreign Key) between tables.
