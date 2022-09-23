package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kurly.tet.guide.springrestdocs.annotations.RestDocsTest;
import com.kurly.tet.guide.springrestdocs.common.util.JsonUtils;
import com.kurly.tet.guide.springrestdocs.documenation.MockMvcFactory;
import com.kurly.tet.guide.springrestdocs.domain.ProductDto;
import com.kurly.tet.guide.springrestdocs.domain.ProductFacade;
import com.kurly.tet.guide.springrestdocs.domain.ProductStatus;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.kurly.tet.guide.springrestdocs.config.Constant.FORMAT_LOCAL_DATE_TIME;
import static com.kurly.tet.guide.springrestdocs.config.Constant.HOST_DEV;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentFormatGenerator.customFormat;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentFormatGenerator.generateEnumAttrs;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentUtils.getDocumentRequest;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

@DisplayName("상품API 문서화")
@RestDocsTest
class ProductRestControllerDocsTest {
    @Mock
    private ProductFacade productFacade;
    @InjectMocks
    private ProductRestController controller;

    @DisplayName("검색: 상품정보")
    @Test
    void testSearchProduct(RestDocumentationContextProvider contextProvider) throws Exception {

        String responseSource = """
                {
                    "content": [
                      {
                        "productId": 1,
                        "productName": "TEST2",
                        "productNo": "00003",
                        "productStatus": "CREATED",
                        "productStatusDescription": "생성",
                        "created": "2022-09-22T18:41:52",
                        "modified": "2022-09-22T18:41:52"
                      },
                      {
                        "productId": 4,
                        "productName": "TEST5",
                        "productNo": "00006",
                        "productStatus": "CREATED",
                        "productStatusDescription": "생성",
                        "created": "2022-09-22T18:41:52",
                        "modified": "2022-09-22T18:41:52"
                      },
                      {
                        "productId": 7,
                        "productName": "TEST8",
                        "productNo": "00009",
                        "productStatus": "CREATED",
                        "productStatusDescription": "생성",
                        "created": "2022-09-22T18:41:52",
                        "modified": "2022-09-22T18:41:52"
                      },
                      {
                        "productId": 10,
                        "productName": "TEST11",
                        "productNo": "00012",
                        "productStatus": "ACTIVATED",
                        "productStatusDescription": "활성화됨",
                        "created": "2022-09-22T18:41:52",
                        "modified": "2022-09-22T18:41:52"
                      },
                      {
                        "productId": 13,
                        "productName": "TEST14",
                        "productNo": "00015",
                        "productStatus": "ARCHIVED",
                        "productStatusDescription": "보관처리됨",
                        "created": "2022-09-22T18:41:52",
                        "modified": "2022-09-22T18:41:52"
                      }
                    ],
                    "pageRequest": {
                      "page": 0,
                      "size": 1000
                    },
                    "total": 5,
                    "totalPages": 1,
                    "hasNext": false,
                    "hasPrevious": false,
                    "hasContent": true,
                    "isFirst": true,
                    "isLast": true
                  }""";

        Mockito.when(productFacade.search(Mockito.any(), Mockito.any()))
                .thenReturn(JsonUtils.fromJson(responseSource, PageResponse.class));

        FieldDescriptor[] fieldDescriptors = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("응답코드(정상: 0000)"),
                fieldWithPath("message").type(STRING).description("응답메시지(정상: OK)"),
                fieldWithPath("data.content[].productId").type(NUMBER).description("상품일련번호"),
                fieldWithPath("data.content[].productName").type(STRING).description("상품명"),
                fieldWithPath("data.content[].productNo").type(STRING).description("상품번호"),
                fieldWithPath("data.content[].productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("상품상태"),
                fieldWithPath("data.content[].productStatusDescription").type(STRING).description("상품상태설명"),
                fieldWithPath("data.content[].created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("생성일시"),
                fieldWithPath("data.content[].modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("변경일시"),
                fieldWithPath("data.pageRequest.page").type(NUMBER).description("페이지번호"),
                fieldWithPath("data.pageRequest.size").type(NUMBER).description("페이지크기"),
                fieldWithPath("data.total").type(NUMBER).description("데이터 전체갯수"),
                fieldWithPath("data.totalPages").type(NUMBER).description("전체 페이지갯수"),
                fieldWithPath("data.isFirst").type(BOOLEAN).description("시작페이지인가?"),
                fieldWithPath("data.isLast").type(BOOLEAN).description("마지막페이지인가?"),
                fieldWithPath("data.hasPrevious").type(BOOLEAN).description("앞페이지가있는가?"),
                fieldWithPath("data.hasNext").type(BOOLEAN).description("다음페이지가있는가?"),
                fieldWithPath("data.hasContent").type(BOOLEAN).description("컨텐츠가있는가?"),
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_DEV, controller)
                .perform(RestDocumentationRequestBuilders.get("/products")
                        .param("page", "0")
                        .param("size", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //REST Docs 용
                .andDo(MockMvcRestDocumentation.document("get-v1-get-products",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("조회페이지"),
                                parameterWithName("size").description("페이지크기")
                        ),
                        responseFields(
                                fieldDescriptors
                        )
                ))
                //OAS 3.0 - Swagger
                .andDo(MockMvcRestDocumentationWrapper.document("get-v1-get-products",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(ResourceSnippetParameters.builder()
                                .requestParameters(
                                        parameterWithName("page").description("조회페이지"),
                                        parameterWithName("size").description("페이지크기")
                                )
                                .responseFields(
                                        fieldDescriptors
                                )
                                .build())));
    }

    @DisplayName("생성: 상품")
    @Test
    void testCreateProduct(RestDocumentationContextProvider contextProvider) throws Exception {
        String createCommandJson = """
                {
                    "productName": "테스트상품",
                    "productNo": "TEST-1111"
                }
                """;
        String createdProductJson = """
                {
                    "productId": 1,
                    "productName": "테스트상품",
                    "productNo": "TEST01",
                    "productStatus": "CREATED",
                    "created": "2022-09-23T05:46:10",
                    "modified": "2022-09-23T05:46:10"
                }""";

        Mockito.when(productFacade.create(Mockito.any()))
                .thenReturn(JsonUtils.fromJson(createdProductJson, ProductDto.class));

        FieldDescriptor[] requestFieldDescription = new FieldDescriptor[] {
                fieldWithPath("productName").type(STRING).description("상품명"),
                fieldWithPath("productNo").type(STRING).description("상품번호")
        };

        FieldDescriptor[] responseFieldDescription = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("응답코드(정상: 0000)"),
                fieldWithPath("message").type(STRING).description("응답메시지(정상: OK)"),
                fieldWithPath("data.productId").type(NUMBER).description("상품일련번호"),
                fieldWithPath("data.productName").type(STRING).description("상품명"),
                fieldWithPath("data.productNo").type(STRING).description("상품번호"),
                fieldWithPath("data.productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("상품상태"),
                fieldWithPath("data.productStatusDescription").type(STRING).description("상품상태설명"),
                fieldWithPath("data.created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("생성일시"),
                fieldWithPath("data.modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("변경일시")
        };

        String expectedContent = "{\"code\":\"0000\",\"message\":\"정상\",\"data\":{\"productId\":1,\"productName\":\"테스트상품\",\"productNo\":\"TEST01\",\"productStatus\":\"CREATED\",\"created\":\"2022-09-23T05:46:10\",\"modified\":\"2022-09-23T05:46:10\",\"productStatusDescription\":\"생성\"}}";
        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_DEV, controller)
                .perform(RestDocumentationRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCommandJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent))
                //REST Docs 용
                .andDo(MockMvcRestDocumentation.document("post-v1-create-product",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                requestFieldDescription
                        ),
                        responseFields(
                                responseFieldDescription
                        )
                ))
                //OAS 3.0 - Swagger
                .andDo(MockMvcRestDocumentationWrapper.document("post-v1-create-product",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(ResourceSnippetParameters.builder()
                                .requestFields(
                                        requestFieldDescription
                                )
                                .responseFields(
                                        responseFieldDescription
                                )
                                .build())));
    }

    @DisplayName("조회: 상품상세")
    @Test
    void testGetProduct(RestDocumentationContextProvider contextProvider) throws Exception {
        String sourceJson = """
                {
                    "productId": 1,
                    "productName": "테스트상품",
                    "productNo": "TEST01",
                    "productStatus": "CREATED",
                    "created": "2022-09-23T05:46:10",
                    "modified": "2022-09-23T05:46:10"
                }""";

        Mockito.when(productFacade.getProduct(Mockito.anyLong()))
                .thenReturn(JsonUtils.fromJson(sourceJson, ProductDto.class));

        String expectedContent = "{\"code\":\"0000\",\"message\":\"정상\",\"data\":{\"productId\":1,\"productName\":\"테스트상품\",\"productNo\":\"TEST01\",\"productStatus\":\"CREATED\",\"created\":\"2022-09-23T05:46:10\",\"modified\":\"2022-09-23T05:46:10\",\"productStatusDescription\":\"생성\"}}";

        ParameterDescriptor parameterDescriptor = parameterWithName("productId").description("상품번호");

        FieldDescriptor[] fieldDescriptors = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("응답코드(정상: 0000)"),
                fieldWithPath("message").type(STRING).description("응답메시지(정상: OK)"),
                fieldWithPath("data.productId").type(NUMBER).description("상품일련번호"),
                fieldWithPath("data.productName").type(STRING).description("상품명"),
                fieldWithPath("data.productNo").type(STRING).description("상품번호"),
                fieldWithPath("data.productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("상품상태"),
                fieldWithPath("data.productStatusDescription").type(STRING).description("상품상태설명"),
                fieldWithPath("data.created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("생성일시"),
                fieldWithPath("data.modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("변경일시")
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_DEV, controller)
                .perform(RestDocumentationRequestBuilders.get("/products/{productId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent))
                //REST Docs 용
                .andDo(MockMvcRestDocumentation.document("get-v1-get-product",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterDescriptor
                        ),
                        responseFields(
                                fieldDescriptors
                        )
                ))
                //OAS 3.0 - Swagger
                .andDo(MockMvcRestDocumentationWrapper.document("get-v1-get-product",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(ResourceSnippetParameters.builder()
                                .pathParameters(
                                        parameterDescriptor
                                )
                                .responseFields(
                                        fieldDescriptors
                                )
                                .build())));
    }

    @DisplayName("변경: 상품")
    @Test
    void testModifyProduct(RestDocumentationContextProvider contextProvider) throws Exception {
        var modifyCommandJson = """
                {
                    "productName": "테스트상품",
                    "productNo": "TEST-1111",
                    "productStatus": "ACTIVATED"
                }
                """;

        var modifiedProductJson = """
                {
                    "productId": 1,
                    "productName": "테스트상품",
                    "productNo": "TEST01",
                    "productStatus": "ACTIVATED",
                    "created": "2022-09-23T05:46:10",
                    "modified": "2022-09-23T05:46:10"
                }""";

        var expectedContent = "{\"code\":\"0000\",\"message\":\"정상\",\"data\":{\"productId\":1,\"productName\":\"테스트상품\",\"productNo\":\"TEST01\",\"productStatus\":\"ACTIVATED\",\"created\":\"2022-09-23T05:46:10\",\"modified\":\"2022-09-23T05:46:10\",\"productStatusDescription\":\"활성화\"}}";

        Mockito.when(productFacade.modify(Mockito.anyLong(), Mockito.any()))
                .thenReturn(JsonUtils.fromJson(modifiedProductJson, ProductDto.class));

        ParameterDescriptor parameterDescriptor = parameterWithName("productId").description("상품번호");

        FieldDescriptor[] requestFieldDescription = new FieldDescriptor[] {
                fieldWithPath("productName").type(STRING).description("상품명"),
                fieldWithPath("productNo").type(STRING).description("상품번호"),
                fieldWithPath("productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("상품상태")
        };

        FieldDescriptor[] responseFieldDescription = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("응답코드(정상: 0000)"),
                fieldWithPath("message").type(STRING).description("응답메시지(정상: OK)"),
                fieldWithPath("data.productId").type(NUMBER).description("상품일련번호"),
                fieldWithPath("data.productName").type(STRING).description("상품명"),
                fieldWithPath("data.productNo").type(STRING).description("상품번호"),
                fieldWithPath("data.productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("상품상태"),
                fieldWithPath("data.productStatusDescription").type(STRING).description("상품상태설명"),
                fieldWithPath("data.created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("생성일시"),
                fieldWithPath("data.modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("변경일시")
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_DEV, controller)
                .perform(RestDocumentationRequestBuilders.put("/products/{productId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyCommandJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent))
                //REST Docs 용
                .andDo(MockMvcRestDocumentation.document("put-v1-modify-product",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterDescriptor
                        ),
                        requestFields(
                                requestFieldDescription
                        ),
                        responseFields(
                                responseFieldDescription
                        )
                ))
                //OAS 3.0 - Swagger
                .andDo(MockMvcRestDocumentationWrapper.document("put-v1-modify-product",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(ResourceSnippetParameters.builder()
                                .pathParameters(
                                        parameterDescriptor
                                )
                                .requestFields(
                                        requestFieldDescription
                                )
                                .responseFields(
                                        responseFieldDescription
                                )
                                .build())));
    }

    @DisplayName("삭제: 상품")
    @Test
    void testDeleteProduct(RestDocumentationContextProvider contextProvider) throws Exception {
        var expectedContent = "";

        ParameterDescriptor parameterDescriptor = parameterWithName("productId").description("상품번호");

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_DEV, controller)
                .perform(RestDocumentationRequestBuilders.delete("/products/{productId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent))
                //REST Docs 용
                .andDo(MockMvcRestDocumentation.document("delete-v1-delete-product",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterDescriptor
                        )
                ))
                //OAS 3.0 - Swagger
                .andDo(MockMvcRestDocumentationWrapper.document("delete-v1-delete-product",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(ResourceSnippetParameters.builder()
                                .pathParameters(
                                        parameterDescriptor
                                )
                                .build())));
    }
}