# BlogProject
세션을 이용한 로그인, CRUD 및 댓글, 검색을 구현한 블로그 프로젝트이다.

## 1. 사용 기술
- 백엔드 : Spring Boot, Spring Data JPA
- 프론트엔드 : HTML, CSS, JavaScript, Bootstrap, Thymeleaf
- 데이터베이스 : H2Databases

## 2. ERD
![데이터베이스 ERD](https://user-images.githubusercontent.com/46291115/210496885-4a24a1a7-58d4-4ca4-912b-05b7331f9346.png)

## 3. 디렉토리 구조
- java
  - common
    - SessionCommon.java
  - controller
    - api
      - AccountApiController.java
      - PostApiController.java
      - ReplyApiController.java
    - MainController.java
  - domain
    - Account.java
    - AccountRepository.java
    - Post.java
    - PostRepository.java
    - Reply
    - ReplyRepository.java
    - TimeEntity.java
  - dto
    - AccountRequestDto.java
    - PostRequestDto.java
    - ReplyRequestDto.java
  - service
    - AccountService.java
    - PostService.java
    - ReplyService.java
  - BlogApplication.java
- resources
  - static.js
    - main.js
  - templates
    - create.html
    - detail.html
    - login.html
    - main.html
    - myAccount.html
    - newAccount.html
    - replyUpdate.html
    - update.html
    - updateAccount.html
    
## 4. 실행 화면
1. 로그인 화면

![로그인 화면](https://user-images.githubusercontent.com/46291115/210496905-4f9d8e5d-af07-4c4f-9811-e4956a912dc8.png)

2. 회원가입 화면

![회원가입 화면](https://user-images.githubusercontent.com/46291115/210496947-18192876-0cc0-4c7c-bac2-1abc6fdddc28.png)

3. 메인 화면

![메인 화면](https://user-images.githubusercontent.com/46291115/210496926-6c304e22-7f32-4f94-9538-e98d827037db.png)

4. 글 추가 화면

![글 추가 화면](https://user-images.githubusercontent.com/46291115/210496817-52b9b4fa-3043-4067-92e3-271914c0fbdf.png)

5. 글 세부 내용 및 댓글 수정 창 화면

![글 세부 내용 and 댓글 수정 창 화면](https://user-images.githubusercontent.com/46291115/210496861-3debabb5-948d-4a60-a04b-019ed980d98d.png)

6. 글 수정 화면

![글 수정 화면](https://user-images.githubusercontent.com/46291115/210496837-ac69244a-55ed-4cab-9cfe-4a44b98e2e84.png)

7. 내 정보 화면

![내 정보 화면](https://user-images.githubusercontent.com/46291115/210496774-c32cf256-59b8-464d-9176-0e051356e5dd.png)
