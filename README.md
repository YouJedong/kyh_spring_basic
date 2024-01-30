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

test