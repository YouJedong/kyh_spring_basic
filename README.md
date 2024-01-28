# kyh_spring_basic
김영한의 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술

## 0128
1. cmd에서 프로젝트 빌드 방법
    1. gradle 있는 경로까지 cd
    2. ./gradlew build
    3. cd build/libs 들어가서 jar파일명 복사
    4. java -jar {파일명}
2. mvc 기본 배우기
   - 섹션 2 > 스프링 웹 개발 기초 > API
   - Api에 @ResponseBody를 붙어주지 않으면 return할 때 viewResolver를 통해서 템플릿을 찾아 html을 던져줌 [JedongController - 19L]
   - @ResponseBody를 붙어주면 return할 때 HttpMessageConverter를 통해 String이면 StringConverter 객체면 jsonConverter를 통해 데이터를 그대로 응답 [JedongController - 26L]

test