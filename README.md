# kyh_spring_basic
김영한의 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술

## 01/28
1. cmd에서 프로젝트 빌드 방법
    1. gradle 있는 경로까지 cd
    2. ./gradlew build
    3. cd build/libs 들어가서 jar파일명 복사
    4. java -jar {파일명}
2. mvc 기본 배우기
   - 섹션 2 > 스프링 웹 개발 기초 > API
   - Api에 @ResponseBody를 붙어주지 않으면 return할 때 viewResolver를 통해서 템플릿을 찾아 html을 던져줌 [JedongController - 19L]
   - @ResponseBody를 붙어주면 return할 때 HttpMessageConverter를 통해 String이면 StringConverter 객체면 jsonConverter를 통해 데이터를 그대로 응답 [JedongController - 26L]

## 01/29
### JPA와 비슷한 형식으로 insert,select 만들기

### 테스트 케이스 작성

- 테스트 케이스 작성의 장점
  1. app을 수정 → 실행하는 복잡한 절차를 거치지 않고 쉽게 테스트할 수 있음
  2. 작성한 테스트 케이스를 한번에 class 레벨이나 패키지 레벨에서 테스트할 수 있다.
- 테스트 케이스 작성 시 주의사항
   - class 레벨에서 테스트를 할 시 테스트 케이스의 순서를 지정할 수 없다. 그래서 같은 필드를 쓴다면 서로 영향을 받아서 정상적인 테스트가 어려울 수 있다.
     그래서 한 테스트 케이스가 끝난 후 clear하는 작업이 필요하다.
   - 해결법 : 한 테스트가 끝난 후 실행되는 메서드에 @AfterEach을 붙어서 이 메서드 안에서 저장소를 clear하는 로직을 만든다. [MemoryMemberRepositoryTest.Java - 17L]
- 테스트 주도 개발(TDD)
   - 실제 기능을 위한 코드를 먼저 만드는 것이 아닌 이렇게 테스트 케이스를 먼저 만들어 논 후 이 테스트를 통과할 수 있는 실제 코드를 작성하는 것을 TDD라고 함

*인텔리j 단축키
- static import → option + enter
- 변수명 한번에 리팩토링 → shift + F6

## 0129

### Section3

- 회원 서비스 개발
- 회원 서비스 테스트
    - 테스트 케이스의 메서드명은 한글로 해놓으면 편함 [MemberServiceTest]
    - 테스트 케이스를 작성할때는 given → when → then 형식으로 작성하면 좋다
    - 로직의 예외가 잘 발생하는지 테스트하는것도 중요하다.
        - try-catch로 exception을 잡을 수도 있지만 assertThrows를 이용해서 테스트를 할 수도 있다. [MemberServiceTest - 중복_회원_예외 로직 중 ]
    - DI(의존성 주입) 사용
        - 테스트 시 계속 다른 repository를 사용하지 않고 같은 repository를 사용할 수 있게 테스트 케이스 실행 전에 service에 의존성주입으로 테스트를 실행한다. [MemberServiceTest - @BeforeEach 로직 중]

### Section4

- 컴포넌트 스캔과 자동 의존관계 설정
    - @Controller, @Service, @Repository 어노테이션을 붙어서 자동으로 스트링 빈 컨테이너에 등록하게 하는 것
    - @Component 어노테이션을 붙이면 spring에서 스캔을 해서 등록시키는데 위의 3개 어노테이션 안에 @Conponent 어노테이션이 붙어있다.

*intelliJ 단축키

- 리턴 값을 자동생성 → commend + option + v
- 팩토링관련된 메뉴 → control + t
    - 함수 추출
- 테스트 케이스 자동 생성 → commend + shift + T
- 테스트 케이스 실행 → commend + shift + R (테스트 하고싶은 블럭에서 단축키)
- 생성자 자동생성 → commend + N

## 0212

### Section4

- 자바 코드로 직접 스프링 빈 등록하기
    - @Service, @Repository를 쓰지 않고 직접 빈에 등록하는 방법
    - SpringConfig.java를 생성 후 @Bean을 통해 직접 등록을 한다.


*DI(Dependency Injection)의 3가지 방법

- 필드 주입, setter 주입, 생성자 주입
- 의존관계가 실행중에는 동적으로 변할 일이 없으므로 생성자 주입을 권장한다.

### Section5

- 회원관리예제  - 웹 MVC개발
    1. 회원 등록
    2. 회원 조회

### Section6

- 스프링 DB접근 기술
    1. H2를 이용한 DB접근 기술
        - 웹 콘솔로 접속

            ```java
            // mac
            // 권한 변경
            chmod 755 h2.sh
            
            // 실행
            ./h2.sh
            
            데이터베이스 파일 생성 방법
            `jdbc:h2:~/test` (최초 한번)
            `~/test.mv.db` 파일 생성 확인
            이후부터는 `jdbc:h2:tcp://localhost/~/test` 이렇게 접속
            ```

        - 프로젝트와 연결
            1. build.gradle에 dependency 추가

                ```java
                implementation 'org.springframework.boot:spring-boot-starter-jdbc'
                runtimeOnly 'com.h2database:h2'
                ```

            2. properties에 url, driver-class-name, user-name추가 (h2는 암호 필요 없음)

                ```java
                spring.datasource.url=jdbc:h2:tcp://localhost/~/test
                spring.datasource.driver-class-name=org.h2.Driver
                spring.datasource.username=sa
                ```

    *SpringConfig.java로 DB를 관리하면 나중에 DB를 바꿀때 쉽게 교체할 수 있다.
    
    ```java
    @Bean
    public MemberRepository memberRepository() {
    	  // return new MemoryMemberRepository();  
    		return  new JdbcMemberRepository(dataSource);
    }
    ```
    
    - 스프링의 DI(Dependencies Injection)을 사용하면 **기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경**할 수 있다.
    - 이것을 **개방-폐쇄의 법칙(OCP - Open-Closed Principle)**이라고 한다 *DB를 확장하는데에는 개방적이고 수정하는 것에는 폐쇄적이다.

## 0213

- 스프링 통합 테스트
    - DB와 연결 후 아래 어노테이션을 테스트 class에 붙어서 쉽게 테스트 가능하다(MemberIntegrationServiceTest.java)
        - @SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행
        - @Transactional : 테스트 케이스에 이 어노테이션이 있으면 테스트 시작전에 트랜젝션을 시작하고 완료 후에 항상 롤백 하기 때문에 다음 테스트에 영향을 주지 않음(테스트 케이스에서만 유효한 기능)
- 스프링 JdbcTemplate
- JPA

## 0214
- 스프링 데이터 JPA
    - JpaRepository를 상속받은 interface를 만들면 자동적으로 Spring bean에 구현체를 만들어준다.

## 0216
- AOP(Aspect Oriented Programming)
    - 공통 관심사항과 핵심 관심 사항을 분리
- AOP 적용중 오류 발생.. 해결할것

## 0216
- AOP(Aspect Oriented Programming)
    - 공통 관심사항과 핵심 관심 사항을 분리
- AOP 적용중 오류 발생.. 해결할것
    - 강의 보는 중 Bean에 등록했다가 지우고 Aop 클래스에 Component 어노테이션에 등록했는데 둘다 넣어놔서 오류가 났었음
- AOP 이해
    - 어떤 API가 실행 되든 간에 공통적으로 처리해야할 사항이 있을때 AOP를 설정해놓는 것이다.