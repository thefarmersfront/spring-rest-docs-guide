package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kurly.tet.guide.springrestdocs.annotations.RestDocsTest;
import com.kurly.tet.guide.springrestdocs.application.product.ProductFacade;
import com.kurly.tet.guide.springrestdocs.common.util.JsonUtils;
import com.kurly.tet.guide.springrestdocs.documenation.MockMvcFactory;
import com.kurly.tet.guide.springrestdocs.domain.product.ProductDto;
import com.kurly.tet.guide.springrestdocs.domain.product.ProductStatus;
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
import static com.kurly.tet.guide.springrestdocs.config.Constant.HOST_LOCAL;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentFormatGenerator.customFormat;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentFormatGenerator.generateEnumAttrs;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentUtils.getDocumentRequest;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

@DisplayName("??????API ?????????")
@RestDocsTest
class ProductRestControllerDocsTest {
    @Mock
    private ProductFacade productFacade;
    @InjectMocks
    private ProductRestController controller;

    @DisplayName("??????: ????????????")
    @Test
    void testSearchProduct(RestDocumentationContextProvider contextProvider) throws Exception {

        var responseSource = """
                {
                    "content": [
                      {
                        "id": 1,
                        "productName": "TEST2",
                        "productNo": "00003",
                        "productStatus": "CREATED",
                        "productStatusDescription": "??????",
                        "created": "2022-09-22T18:41:52",
                        "modified": "2022-09-22T18:41:52"
                      },
                      {
                        "id": 4,
                        "productName": "TEST5",
                        "productNo": "00006",
                        "productStatus": "CREATED",
                        "productStatusDescription": "??????",
                        "created": "2022-09-22T18:41:52",
                        "modified": "2022-09-22T18:41:52"
                      },
                      {
                        "id": 7,
                        "productName": "TEST8",
                        "productNo": "00009",
                        "productStatus": "CREATED",
                        "productStatusDescription": "??????",
                        "created": "2022-09-22T18:41:52",
                        "modified": "2022-09-22T18:41:52"
                      },
                      {
                        "id": 10,
                        "productName": "TEST11",
                        "productNo": "00012",
                        "productStatus": "ACTIVATED",
                        "productStatusDescription": "????????????",
                        "created": "2022-09-22T18:41:52",
                        "modified": "2022-09-22T18:41:52"
                      },
                      {
                        "id": 13,
                        "productName": "TEST14",
                        "productNo": "00015",
                        "productStatus": "ARCHIVED",
                        "productStatusDescription": "???????????????",
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

        var parameterDescriptors = new ParameterDescriptor[]{
                parameterWithName("page").description("???????????????"),
                parameterWithName("size").description("???????????? ??????"),
                parameterWithName("productName").description("?????????"),
                parameterWithName("productNo").description("????????????")
        };

        var fieldDescriptors = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                fieldWithPath("data.content[].id").type(NUMBER).description("??????????????????"),
                fieldWithPath("data.content[].productName").type(STRING).description("?????????"),
                fieldWithPath("data.content[].productNo").type(STRING).description("????????????"),
                fieldWithPath("data.content[].productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????"),
                fieldWithPath("data.content[].productStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.content[].created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.content[].modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.pageRequest.page").type(NUMBER).description("???????????????"),
                fieldWithPath("data.pageRequest.size").type(NUMBER).description("???????????????"),
                fieldWithPath("data.total").type(NUMBER).description("????????? ????????????"),
                fieldWithPath("data.totalPages").type(NUMBER).description("?????? ???????????????"),
                fieldWithPath("data.isFirst").type(BOOLEAN).description("??????????????????????"),
                fieldWithPath("data.isLast").type(BOOLEAN).description("?????????????????????????"),
                fieldWithPath("data.hasPrevious").type(BOOLEAN).description("?????????????????????????"),
                fieldWithPath("data.hasNext").type(BOOLEAN).description("????????????????????????????"),
                fieldWithPath("data.hasContent").type(BOOLEAN).description("??????????????????????"),
        };


        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_LOCAL, controller)
                .perform(RestDocumentationRequestBuilders.get("/products")
                        .param("page", "0")
                        .param("size", "20")
                        .param("productName", "???????????????")
                        .param("productNo", "PRODUCT01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //REST Docs ???
                .andDo(MockMvcRestDocumentation.document("get-v1-get-products",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterDescriptors
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
                                        parameterDescriptors
                                )
                                .responseFields(
                                        fieldDescriptors
                                )
                                .build())));
    }

    @DisplayName("??????: ??????")
    @Test
    void testCreateProduct(RestDocumentationContextProvider contextProvider) throws Exception {
        var createCommandJson = """
                {
                    "productName": "???????????????",
                    "productNo": "TEST-1111"
                }
                """;
        var createdProductJson = """
                {
                    "id": 1,
                    "productName": "???????????????",
                    "productNo": "TEST01",
                    "productStatus": "CREATED",
                    "created": "2022-09-23T05:46:10",
                    "modified": "2022-09-23T05:46:10"
                }""";

        Mockito.when(productFacade.create(Mockito.any()))
                .thenReturn(JsonUtils.fromJson(createdProductJson, ProductDto.class));

        var requestFieldDescription = new FieldDescriptor[]{
                fieldWithPath("productName").type(STRING).description("?????????"),
                fieldWithPath("productNo").type(STRING).description("????????????")
        };

        var responseFieldDescription = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                fieldWithPath("data.id").type(NUMBER).description("??????????????????"),
                fieldWithPath("data.productName").type(STRING).description("?????????"),
                fieldWithPath("data.productNo").type(STRING).description("????????????"),
                fieldWithPath("data.productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????"),
                fieldWithPath("data.productStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????")
        };

        var expectedContent = "{\"code\":\"0000\",\"message\":\"??????\",\"data\":{\"id\":1,\"productName\":\"???????????????\",\"productNo\":\"TEST01\",\"productStatus\":\"CREATED\",\"created\":\"2022-09-23T05:46:10\",\"modified\":\"2022-09-23T05:46:10\",\"productStatusDescription\":\"??????\"}}";
        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_LOCAL, controller)
                .perform(RestDocumentationRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCommandJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent))
                //REST Docs ???
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

    @DisplayName("??????: ????????????")
    @Test
    void testGetProduct(RestDocumentationContextProvider contextProvider) throws Exception {
        var sourceJson = """
                {
                    "id": 1,
                    "productName": "???????????????",
                    "productNo": "TEST01",
                    "productStatus": "CREATED",
                    "created": "2022-09-23T05:46:10",
                    "modified": "2022-09-23T05:46:10"
                }""";

        Mockito.when(productFacade.getProduct(Mockito.anyLong()))
                .thenReturn(JsonUtils.fromJson(sourceJson, ProductDto.class));

        String expectedContent = "{\"code\":\"0000\",\"message\":\"??????\",\"data\":{\"id\":1,\"productName\":\"???????????????\",\"productNo\":\"TEST01\",\"productStatus\":\"CREATED\",\"created\":\"2022-09-23T05:46:10\",\"modified\":\"2022-09-23T05:46:10\",\"productStatusDescription\":\"??????\"}}";

        var parameterDescriptor = parameterWithName("id").description("????????????");

        var fieldDescriptors = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                fieldWithPath("data.id").type(NUMBER).description("??????????????????"),
                fieldWithPath("data.productName").type(STRING).description("?????????"),
                fieldWithPath("data.productNo").type(STRING).description("????????????"),
                fieldWithPath("data.productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????"),
                fieldWithPath("data.productStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????")
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_LOCAL, controller)
                .perform(RestDocumentationRequestBuilders.get("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent))
                //REST Docs ???
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

    @DisplayName("??????: ??????")
    @Test
    void testModifyProduct(RestDocumentationContextProvider contextProvider) throws Exception {
        var modifyCommandJson = """
                {
                    "productName": "???????????????",
                    "productNo": "TEST-1111",
                    "productStatus": "ACTIVATED"
                }
                """;

        var modifiedProductJson = """
                {
                    "id": 1,
                    "productName": "???????????????",
                    "productNo": "TEST01",
                    "productStatus": "ACTIVATED",
                    "created": "2022-09-23T05:46:10",
                    "modified": "2022-09-23T05:46:10"
                }""";

        var expectedContent = "{\"code\":\"0000\",\"message\":\"??????\",\"data\":{\"id\":1,\"productName\":\"???????????????\",\"productNo\":\"TEST01\",\"productStatus\":\"ACTIVATED\",\"created\":\"2022-09-23T05:46:10\",\"modified\":\"2022-09-23T05:46:10\",\"productStatusDescription\":\"?????????\"}}";

        Mockito.when(productFacade.modify(Mockito.anyLong(), Mockito.any()))
                .thenReturn(JsonUtils.fromJson(modifiedProductJson, ProductDto.class));

        var parameterDescriptor = parameterWithName("id").description("????????????");

        var requestFieldDescription = new FieldDescriptor[]{
                fieldWithPath("productName").type(STRING).description("?????????"),
                fieldWithPath("productNo").type(STRING).description("????????????"),
                fieldWithPath("productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????")
        };

        var responseFieldDescription = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                fieldWithPath("data.id").type(NUMBER).description("??????????????????"),
                fieldWithPath("data.productName").type(STRING).description("?????????"),
                fieldWithPath("data.productNo").type(STRING).description("????????????"),
                fieldWithPath("data.productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????"),
                fieldWithPath("data.productStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????")
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_LOCAL, controller)
                .perform(RestDocumentationRequestBuilders.put("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyCommandJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent))
                //REST Docs ???
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

    @DisplayName("??????: ??????")
    @Test
    void testDeleteProduct(RestDocumentationContextProvider contextProvider) throws Exception {
        var expectedContent = "";

        var parameterDescriptor = parameterWithName("id").description("????????????");

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_LOCAL, controller)
                .perform(RestDocumentationRequestBuilders.delete("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent))
                //REST Docs ???
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