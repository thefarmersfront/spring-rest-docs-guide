Spring Rest Docs 사용 가이드
==================================
<!-- @see https://raw.githubusercontent.com/othneildrew/Best-README-Template/master/README.md -->
<a name="readme-top"></a>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>목차(Table of Contents)</summary>
  <ol>
    <li>
      <a href="#프로젝트-소개">프로젝트 소개</a>
    <li>
      <a href="#시작하기">시작하기</a>
      <ul>
        <li><a href="#준비사항">준비사항</a></li>
        <li><a href="#설치">설치</a></li>
      </ul>
    </li>
    <li><a href="#협업방법">협업방법</a></li>
    <li><a href="#부록">부록</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## 프로젝트 소개
곧 기술블로그를 통해 'Spring REST Docs 작성가이드 공유드리겠습니다.' 
<p style="text-align: right">(<a href="#readme-top">최상단 이동</a>)</p>

<!-- GETTING STARTED -->
## 시작하기

프로젝트를 로컬에서 시작하려면 다음 안내를 따라주시면 됩니다.

### 요구사항

### 설치

1. 리포지토리 복제(Clone the repo)
    ```sh
   git clone git@github.com:thefarmersfront/spring-rest-docs-guide.git
   cd spring-rest-docs-guide
    ```

2. 프로젝트 구성하기(Build project)
    ```sh
   ./gradlew clean build
    ```

3. API문서생성
    ```sh
   ./gradlew clean restDocsTest
    ```

4. 생성문서 확인
   1. Spring REST Docs: `build/docs/index.html`
   2. SwaggerUI: `api-spec/openapi3.yaml`

<p style="text-align: right">(<a href="#readme-top">최상단 이동</a>)</p>

<!-- CONTRIBUTION GUIDE -->
### 협업방법

* 프로젝트를 새롭게 시작했거나 프로젝트 문서가 없다면 확인자가 문서를 생성합니다.
* PUSH 전에 checkStyle, sonarlint 등을 이용해 코드를 정리해주세요.
* Pull Request 전에는 로컬 테스트를 반드시 수행해 주세요. <!-- 커밋 훅으로 강제하는 방법도 있겠으나, 핫픽스 등의 급한 처리건이 발생할 수 있으니... -->

<p style="text-align: right">(<a href="#readme-top">최상단 이동</a>)</p>

<!-- APPENDIX -->
## 부록

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [sdkman.io](https://sdkman.io/)
* [Markdown Guide](https://www.markdownguide.org/)
* [Best-README-Template](https://github.com/othneildrew/Best-README-Template)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[url-spring-boot]: https://spring.io/projects/spring-boot/
[url-spring-boot-ref-doc]: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
[url-spring-rest-docs-project]: https://spring.io/projects/spring-restdocs/
[url-spring-rest-docs-ref-doc]: https://docs.spring.io/spring-restdocs/docs/current/reference/html5/
[url-swagger-io]: https://swagger.io/
[url-swagger-ui]: https://swagger.io/tools/swagger-ui/