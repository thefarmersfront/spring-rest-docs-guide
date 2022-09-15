Spring REST Docs 사용자정의 스니펫 적용시 주의사항
==============================================

* Spring REST Docs 스니펫이 적용되지 않은 이유: 스니펫 폴더이름이 잘못 되었음
    * AS-IS: `src/test/resources/org.springframework.restdocs.templates.asciidoctor`
    * TO-BE: `src/test/resources/org/springframework/restdocs/templates/asciidoctor`
* 인텔리제이에서는 동일하게 보이지만, 실제로 스니펫 폴더 경로는 달랐음
