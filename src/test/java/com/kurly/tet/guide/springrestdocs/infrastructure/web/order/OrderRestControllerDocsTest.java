package com.kurly.tet.guide.springrestdocs.infrastructure.web.order;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.kurly.tet.guide.springrestdocs.annotations.RestDocsTest;
import com.kurly.tet.guide.springrestdocs.application.order.OrderFacade;
import com.kurly.tet.guide.springrestdocs.common.util.JsonUtils;
import com.kurly.tet.guide.springrestdocs.documenation.MockMvcFactory;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderStatus;
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

import java.util.UUID;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.kurly.tet.guide.springrestdocs.config.Constant.*;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentFormatGenerator.customFormat;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentFormatGenerator.generateEnumAttrs;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentUtils.getDocumentRequest;
import static com.kurly.tet.guide.springrestdocs.documenation.DocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

@DisplayName("??????API ?????????")
@RestDocsTest
class OrderRestControllerDocsTest {
    @Mock
    private OrderFacade orderFacade;
    @InjectMocks
    private OrderRestController controller;

    @DisplayName("??????: ???????????? ??????")
    @Test
    void test01(RestDocumentationContextProvider contextProvider) throws Exception {
        var responseSource = """
                {
                   "content":[
                     {
                       "id":1,
                       "memberNo":"1111",
                       "orderNo":"202209251659441",
                       "orderStatus":"COMPLETED",
                       "orderStatusDescription":"????????????",
                       "products":[
                         {
                           "id":1,
                           "productName":"TEST2",
                           "productNo":"00003",
                           "productStatus":"CREATED",
                           "created":"2022-09-25T16:59:23",
                           "modified":"2022-09-25T16:59:23",
                           "productStatusDescription":"??????"
                         },
                         {
                           "id":4,
                           "productName":"TEST5",
                           "productNo":"00006",
                           "productStatus":"CREATED",
                           "created":"2022-09-25T16:59:23",
                           "modified":"2022-09-25T16:59:23",
                           "productStatusDescription":"??????"
                         }
                       ],
                       "orderDateTime":"2022-09-25T16:59:44",
                       "paymentMoney":1000,
                       "paymentDateTime":"2022-09-25T17:00:46",
                       "shipmentDateTime":"2022-09-25T17:00:59",
                       "completedDateTime":"2022-09-25T17:01:17"
                     },
                     {
                       "id":2,
                       "memberNo":"1111",
                       "orderNo":"202209251659492",
                       "orderStatus":"SHIPPED",
                       "orderStatusDescription":"???????????? ",
                       "products":[
                         {
                           "id":4,
                           "productName":"TEST5",
                           "productNo":"00006",
                           "productStatus":"CREATED",
                           "created":"2022-09-25T16:59:23",
                           "modified":"2022-09-25T16:59:23",
                           "productStatusDescription":"??????"
                         },
                         {
                           "id":7,
                           "productName":"TEST8",
                           "productNo":"00009",
                           "productStatus":"CREATED",
                           "created":"2022-09-25T16:59:23",
                           "modified":"2022-09-25T16:59:23",
                           "productStatusDescription":"??????"
                         }
                       ],
                       "orderDateTime":"2022-09-25T16:59:49",
                       "paymentMoney":10001,
                       "paymentDateTime":"2022-09-25T17:01:37",
                       "shipmentDateTime":"2022-09-25T17:01:44",
                       "completedDateTime":null
                     },
                     {
                       "id":3,
                       "memberNo":"1111",
                       "orderNo":"202209251659563",
                       "orderStatus":"PAID",
                       "orderStatusDescription":"????????????",
                       "products":[
                         {
                           "id":7,
                           "productName":"TEST8",
                           "productNo":"00009",
                           "productStatus":"CREATED",
                           "created":"2022-09-25T16:59:23",
                           "modified":"2022-09-25T16:59:23",
                           "productStatusDescription":"??????"
                         },
                         {
                           "id":10,
                           "productName":"TEST11",
                           "productNo":"00012",
                           "productStatus":"CREATED",
                           "created":"2022-09-25T16:59:23",
                           "modified":"2022-09-25T16:59:23",
                           "productStatusDescription":"??????"
                         }
                       ],
                       "orderDateTime":"2022-09-25T16:59:56",
                       "paymentMoney":1003,
                       "paymentDateTime":"2022-09-25T17:02:20",
                       "shipmentDateTime":null,
                       "completedDateTime":null
                     },
                     {
                       "id":4,
                       "memberNo":"1111",
                       "orderNo":"202209251700034",
                       "orderStatus":"ORDERED",
                       "orderStatusDescription":"????????????",
                       "products":[
                         {
                           "id":4,
                           "productName":"TEST5",
                           "productNo":"00006",
                           "productStatus":"CREATED",
                           "created":"2022-09-25T16:59:23",
                           "modified":"2022-09-25T16:59:23",
                           "productStatusDescription":"??????"
                         },
                         {
                           "id":7,
                           "productName":"TEST8",
                           "productNo":"00009",
                           "productStatus":"CREATED",
                           "created":"2022-09-25T16:59:23",
                           "modified":"2022-09-25T16:59:23",
                           "productStatusDescription":"??????"
                         },
                         {
                           "id":10,
                           "productName":"TEST11",
                           "productNo":"00012",
                           "productStatus":"CREATED",
                           "created":"2022-09-25T16:59:23",
                           "modified":"2022-09-25T16:59:23",
                           "productStatusDescription":"??????"
                         }
                       ],
                       "orderDateTime":"2022-09-25T17:00:03",
                       "paymentMoney":null,
                       "paymentDateTime":null,
                       "shipmentDateTime":null,
                       "completedDateTime":null
                     }
                   ],
                   "pageRequest":{
                     "page":0,
                     "size":1000
                   },
                   "total":4,
                   "totalPages":1,
                   "hasNext":false,
                   "hasPrevious":false,
                   "isFirst":true,
                   "isLast":true,
                   "hasContent":true
                }""";

        Mockito.when(orderFacade.search(Mockito.any(), Mockito.any()))
                .thenReturn(JsonUtils.fromJson(responseSource, PageResponse.class));

        var parameterDescriptors = new ParameterDescriptor[]{
                parameterWithName("page").description("???????????????"),
                parameterWithName("size").description("????????? ??????"),
                parameterWithName("memberNo").description("????????????"),
                parameterWithName("orderNo").description("????????????")
        };

        var responseFieldDescriptors = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                fieldWithPath("data.content[].id").type(NUMBER).description("????????????"),
                fieldWithPath("data.content[].memberNo").type(STRING).description("????????????"),
                fieldWithPath("data.content[].orderNo").type(STRING).description("????????????"),
                fieldWithPath("data.content[].orderStatus").type(STRING).attributes(generateEnumAttrs(OrderStatus.class, OrderStatus::getDescription)).description("????????????"),
                fieldWithPath("data.content[].orderStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.content[].products[].id").type(NUMBER).description("??????????????????"),
                fieldWithPath("data.content[].products[].productName").type(STRING).description("?????????"),
                fieldWithPath("data.content[].products[].productNo").type(STRING).description("????????????"),
                fieldWithPath("data.content[].products[].productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????"),
                fieldWithPath("data.content[].products[].productStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.content[].products[].created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.content[].products[].modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.content[].orderDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????"),
                fieldWithPath("data.content[].paymentMoney").type(NUMBER).description("????????????").optional(),
                fieldWithPath("data.content[].paymentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.content[].shipmentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.content[].completedDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
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
                .perform(RestDocumentationRequestBuilders.get("/orders")
                        .param("page", "0")
                        .param("size", "20")
                        .param("memberNo", "20220925163201")
                        .param("orderNo", UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //REST Docs ???
                .andDo(MockMvcRestDocumentation.document("get-v1-get-orders",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterDescriptors
                        ),
                        responseFields(
                                responseFieldDescriptors
                        )
                ))
                //OAS 3.0 - Swagger
                .andDo(MockMvcRestDocumentationWrapper.document("get-v1-get-orders",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(ResourceSnippetParameters.builder()
                                .requestParameters(
                                        parameterDescriptors
                                )
                                .responseFields(
                                        responseFieldDescriptors
                                )
                                .build())));
    }

    @DisplayName("????????????")
    @Test
    void test02(RestDocumentationContextProvider contextProvider) throws Exception {
        var createCommandJson = """
                {
                    "memberNo":"1111",
                    "productIds":[1,4]
                }
                """;

        var requestFieldDescription = new FieldDescriptor[]{
                fieldWithPath("memberNo").type(STRING).description("????????????"),
                fieldWithPath("productIds").type(ARRAY).description("??????????????????")
        };

        var createdOrderJson = """
                {
                  "id":1,
                  "memberNo":"1111",
                  "orderNo":"202209251659441",
                  "orderStatus":"ORDERED",
                  "orderStatusDescription":"????????????",
                  "products":[
                    {
                      "id":1,
                      "productName":"TEST2",
                      "productNo":"00003",
                      "productStatus":"CREATED",
                      "created":"2022-09-25T16:59:23",
                      "modified":"2022-09-25T16:59:23",
                      "productStatusDescription":"??????"
                    },
                    {
                      "id":4,
                      "productName":"TEST5",
                      "productNo":"00006",
                      "productStatus":"CREATED",
                      "created":"2022-09-25T16:59:23",
                      "modified":"2022-09-25T16:59:23",
                      "productStatusDescription":"??????"
                    }
                  ],
                  "orderDateTime":"2022-09-25T16:59:44",
                  "paymentMoney":null,
                  "paymentDateTime":null,
                  "shipmentDateTime":null,
                  "completedDateTime":null
                }""";

        Mockito.when(orderFacade.create(Mockito.any()))
                .thenReturn(JsonUtils.fromJson(createdOrderJson, OrderDto.class));

        var responseFieldDescription = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                fieldWithPath("data.id").type(NUMBER).description("????????????"),
                fieldWithPath("data.memberNo").type(STRING).description("????????????"),
                fieldWithPath("data.orderNo").type(STRING).description("????????????"),
                fieldWithPath("data.orderStatus").type(STRING).attributes(generateEnumAttrs(OrderStatus.class, OrderStatus::getDescription)).description("????????????"),
                fieldWithPath("data.orderStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.products[].id").type(NUMBER).description("??????????????????"),
                fieldWithPath("data.products[].productName").type(STRING).description("?????????"),
                fieldWithPath("data.products[].productNo").type(STRING).description("????????????"),
                fieldWithPath("data.products[].productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????"),
                fieldWithPath("data.products[].productStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.products[].created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.products[].modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.orderDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????"),
                fieldWithPath("data.paymentMoney").type(NUMBER).description("????????????").optional(),
                fieldWithPath("data.paymentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.shipmentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.completedDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_LOCAL, controller)
                .perform(RestDocumentationRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCommandJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                //REST Docs ???
                .andDo(MockMvcRestDocumentation.document("post-v1-create-order",
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
                .andDo(MockMvcRestDocumentationWrapper.document("post-v1-create-order",
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

    @DisplayName("????????????")
    @Test
    void test03(RestDocumentationContextProvider contextProvider) throws Exception {
        var orderNo = "797a1eb9-d6b8-4875-9ac6-4cbeac65ba40";
        var commandJson = """
                {
                    "paymentMoney": 1000
                }
                """;
        var expectedContent = "{\"code\":\"0000\",\"message\":\"??????\",\"data\":{\"id\":1,\"memberNo\":\"1111\",\"orderNo\":\"202209251659441\",\"orderStatus\":\"PAID\",\"products\":[{\"id\":1,\"productName\":\"TEST2\",\"productNo\":\"00003\",\"productStatus\":\"CREATED\",\"created\":\"2022-09-25T16:59:23\",\"modified\":\"2022-09-25T16:59:23\",\"productStatusDescription\":\"??????\"},{\"id\":4,\"productName\":\"TEST5\",\"productNo\":\"00006\",\"productStatus\":\"CREATED\",\"created\":\"2022-09-25T16:59:23\",\"modified\":\"2022-09-25T16:59:23\",\"productStatusDescription\":\"??????\"}],\"orderDateTime\":\"2022-09-25T16:59:44\",\"paymentMoney\":1000,\"paymentDateTime\":\"2022-09-25T17:00:46\",\"shipmentDateTime\":null,\"completedDateTime\":null,\"orderStatusDescription\":\"????????????\"}}";
        var parameterDescriptor = parameterWithName("orderNo").description("????????????");
        var requestFieldDescription = new FieldDescriptor[]{
                fieldWithPath("paymentMoney").type(NUMBER).description("????????????")
        };

        var orderJson = """
                {
                  "id":1,
                  "memberNo":"1111",
                  "orderNo":"202209251659441",
                  "orderStatus":"PAID",
                  "orderStatusDescription":"????????????",
                  "products":[
                    {
                      "id":1,
                      "productName":"TEST2",
                      "productNo":"00003",
                      "productStatus":"CREATED",
                      "created":"2022-09-25T16:59:23",
                      "modified":"2022-09-25T16:59:23",
                      "productStatusDescription":"??????"
                    },
                    {
                      "id":4,
                      "productName":"TEST5",
                      "productNo":"00006",
                      "productStatus":"CREATED",
                      "created":"2022-09-25T16:59:23",
                      "modified":"2022-09-25T16:59:23",
                      "productStatusDescription":"??????"
                    }
                  ],
                  "orderDateTime":"2022-09-25T16:59:44",
                  "paymentMoney":1000,
                  "paymentDateTime":"2022-09-25T17:00:46",
                  "shipmentDateTime":null,
                  "completedDateTime":null
                }""";

        Mockito.when(orderFacade.payment(Mockito.any(), Mockito.any()))
                .thenReturn(JsonUtils.fromJson(orderJson, OrderDto.class));

        var responseFieldDescription = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                fieldWithPath("data.id").type(NUMBER).description("????????????"),
                fieldWithPath("data.memberNo").type(STRING).description("????????????"),
                fieldWithPath("data.orderNo").type(STRING).description("????????????"),
                fieldWithPath("data.orderStatus").type(STRING).attributes(generateEnumAttrs(OrderStatus.class, OrderStatus::getDescription)).description("????????????"),
                fieldWithPath("data.orderStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.products[].id").type(NUMBER).description("??????????????????"),
                fieldWithPath("data.products[].productName").type(STRING).description("?????????"),
                fieldWithPath("data.products[].productNo").type(STRING).description("????????????"),
                fieldWithPath("data.products[].productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????"),
                fieldWithPath("data.products[].productStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.products[].created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.products[].modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.orderDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????"),
                fieldWithPath("data.paymentMoney").type(NUMBER).description("????????????").optional(),
                fieldWithPath("data.paymentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.shipmentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.completedDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_LOCAL, controller)
                .perform(RestDocumentationRequestBuilders.put("/orders/{orderNo}/payment", orderNo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commandJson)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent))
                //REST Docs ???
                .andDo(MockMvcRestDocumentation.document("put-v1-payment-order",
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
                .andDo(MockMvcRestDocumentationWrapper.document("put-v1-payment-order",
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

    @DisplayName("??????????????????")
    @Test
    void test06(RestDocumentationContextProvider contextProvider) throws Exception {
        var orderNo = "797a1eb9-d6b8-4875-9ac6-4cbeac65ba40";
        var expectedContent = "{\"code\":\"0000\",\"message\":\"??????\",\"data\":{\"id\":1,\"memberNo\":\"1111\",\"orderNo\":\"202209251659441\",\"orderStatus\":\"PAID\",\"products\":[{\"id\":1,\"productName\":\"TEST2\",\"productNo\":\"00003\",\"productStatus\":\"CREATED\",\"created\":\"2022-09-25T16:59:23\",\"modified\":\"2022-09-25T16:59:23\",\"productStatusDescription\":\"??????\"},{\"id\":4,\"productName\":\"TEST5\",\"productNo\":\"00006\",\"productStatus\":\"CREATED\",\"created\":\"2022-09-25T16:59:23\",\"modified\":\"2022-09-25T16:59:23\",\"productStatusDescription\":\"??????\"}],\"orderDateTime\":\"2022-09-25T16:59:44\",\"paymentMoney\":1000,\"paymentDateTime\":\"2022-09-25T17:00:46\",\"shipmentDateTime\":null,\"completedDateTime\":null,\"orderStatusDescription\":\"????????????\"}}";
        var parameterDescriptor = parameterWithName("orderNo").description("????????????");

        var orderJson = """
                {
                  "id":1,
                  "memberNo":"1111",
                  "orderNo":"202209251659441",
                  "orderStatus":"PAID",
                  "orderStatusDescription":"????????????",
                  "products":[
                    {
                      "id":1,
                      "productName":"TEST2",
                      "productNo":"00003",
                      "productStatus":"CREATED",
                      "created":"2022-09-25T16:59:23",
                      "modified":"2022-09-25T16:59:23",
                      "productStatusDescription":"??????"
                    },
                    {
                      "id":4,
                      "productName":"TEST5",
                      "productNo":"00006",
                      "productStatus":"CREATED",
                      "created":"2022-09-25T16:59:23",
                      "modified":"2022-09-25T16:59:23",
                      "productStatusDescription":"??????"
                    }
                  ],
                  "orderDateTime":"2022-09-25T16:59:44",
                  "paymentMoney":1000,
                  "paymentDateTime":"2022-09-25T17:00:46",
                  "shipmentDateTime":null,
                  "completedDateTime":null
                }""";

        Mockito.when(orderFacade.findByOrderNo(Mockito.any()))
                .thenReturn(JsonUtils.fromJson(orderJson, OrderDto.class));

        var responseFieldDescription = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                fieldWithPath("data.id").type(NUMBER).description("????????????"),
                fieldWithPath("data.memberNo").type(STRING).description("????????????"),
                fieldWithPath("data.orderNo").type(STRING).description("????????????"),
                fieldWithPath("data.orderStatus").type(STRING).attributes(generateEnumAttrs(OrderStatus.class, OrderStatus::getDescription)).description("????????????"),
                fieldWithPath("data.orderStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.products[].id").type(NUMBER).description("??????????????????"),
                fieldWithPath("data.products[].productName").type(STRING).description("?????????"),
                fieldWithPath("data.products[].productNo").type(STRING).description("????????????"),
                fieldWithPath("data.products[].productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????"),
                fieldWithPath("data.products[].productStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.products[].created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.products[].modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.orderDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????"),
                fieldWithPath("data.paymentMoney").type(NUMBER).description("????????????").optional(),
                fieldWithPath("data.paymentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.shipmentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.completedDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_LOCAL, controller)
                .perform(RestDocumentationRequestBuilders.get("/orders/{orderNo}", orderNo)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent))
                //REST Docs ???
                .andDo(MockMvcRestDocumentation.document("get-v1-get-order",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterDescriptor
                        ),
                        responseFields(
                                responseFieldDescription
                        )
                ))
                //OAS 3.0 - Swagger
                .andDo(MockMvcRestDocumentationWrapper.document("get-v1-get-order",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(ResourceSnippetParameters.builder()
                                .pathParameters(
                                        parameterDescriptor
                                )
                                .responseFields(
                                        responseFieldDescription
                                )
                                .build())));
    }

    @DisplayName("????????????")
    @Test
    void test04(RestDocumentationContextProvider contextProvider) throws Exception {
        var orderNo = "797a1eb9-d6b8-4875-9ac6-4cbeac65ba40";

        var orderJson = """
                {
                  "id":1,
                  "memberNo":"1111",
                  "orderNo":"202209251659441",
                  "orderStatus":"SHIPPED",
                  "orderStatusDescription":"????????????",
                  "products":[
                    {
                      "id":1,
                      "productName":"TEST2",
                      "productNo":"00003",
                      "productStatus":"CREATED",
                      "created":"2022-09-25T16:59:23",
                      "modified":"2022-09-25T16:59:23",
                      "productStatusDescription":"??????"
                    },
                    {
                      "id":4,
                      "productName":"TEST5",
                      "productNo":"00006",
                      "productStatus":"CREATED",
                      "created":"2022-09-25T16:59:23",
                      "modified":"2022-09-25T16:59:23",
                      "productStatusDescription":"??????"
                    }
                  ],
                  "orderDateTime":"2022-09-25T16:59:44",
                  "paymentMoney":1000,
                  "paymentDateTime":"2022-09-25T17:00:46",
                  "shipmentDateTime":"2022-09-25T17:00:50",
                  "completedDateTime":null
                }""";

        Mockito.when(orderFacade.shipping(Mockito.any()))
                .thenReturn(JsonUtils.fromJson(orderJson, OrderDto.class));

        var parameterDescriptor = parameterWithName("orderNo").description("????????????");

        var responseFieldDescription = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                fieldWithPath("data.id").type(NUMBER).description("????????????"),
                fieldWithPath("data.memberNo").type(STRING).description("????????????"),
                fieldWithPath("data.orderNo").type(STRING).description("????????????"),
                fieldWithPath("data.orderStatus").type(STRING).attributes(generateEnumAttrs(OrderStatus.class, OrderStatus::getDescription)).description("????????????"),
                fieldWithPath("data.orderStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.products[].id").type(NUMBER).description("??????????????????"),
                fieldWithPath("data.products[].productName").type(STRING).description("?????????"),
                fieldWithPath("data.products[].productNo").type(STRING).description("????????????"),
                fieldWithPath("data.products[].productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????"),
                fieldWithPath("data.products[].productStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.products[].created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.products[].modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.orderDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????"),
                fieldWithPath("data.paymentMoney").type(NUMBER).description("????????????").optional(),
                fieldWithPath("data.paymentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.shipmentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.completedDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_LOCAL, controller)
                .perform(RestDocumentationRequestBuilders.put("/orders/{orderNo}/shipping", orderNo)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //REST Docs ???
                .andDo(MockMvcRestDocumentation.document("put-v1-shipping-order",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterDescriptor
                        ),
                        responseFields(
                                responseFieldDescription
                        )
                ))
                //OAS 3.0 - Swagger
                .andDo(MockMvcRestDocumentationWrapper.document("put-v1-shipping-order",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(ResourceSnippetParameters.builder()
                                .pathParameters(
                                        parameterDescriptor
                                )
                                .responseFields(
                                        responseFieldDescription
                                )
                                .build())));
    }

    @DisplayName("????????????")
    @Test
    void test05(RestDocumentationContextProvider contextProvider) throws Exception {
        var orderNo = "797a1eb9-d6b8-4875-9ac6-4cbeac65ba40";

        var orderJson = """
                {
                  "id":1,
                  "memberNo":"1111",
                  "orderNo":"202209251659441",
                  "orderStatus":"COMPLETED",
                  "orderStatusDescription":"????????????",
                  "products":[
                    {
                      "id":1,
                      "productName":"TEST2",
                      "productNo":"00003",
                      "productStatus":"CREATED",
                      "created":"2022-09-25T16:59:23",
                      "modified":"2022-09-25T16:59:23",
                      "productStatusDescription":"??????"
                    },
                    {
                      "id":4,
                      "productName":"TEST5",
                      "productNo":"00006",
                      "productStatus":"CREATED",
                      "created":"2022-09-25T16:59:23",
                      "modified":"2022-09-25T16:59:23",
                      "productStatusDescription":"??????"
                    }
                  ],
                  "orderDateTime":"2022-09-25T16:59:44",
                  "paymentMoney":1000,
                  "paymentDateTime":"2022-09-25T17:00:46",
                  "shipmentDateTime":"2022-09-25T17:00:50",
                  "completedDateTime":"2022-09-26T17:00:50"
                }""";

        Mockito.when(orderFacade.complete(Mockito.any()))
                .thenReturn(JsonUtils.fromJson(orderJson, OrderDto.class));

        var parameterDescriptor = parameterWithName("orderNo").description("????????????");

        var responseFieldDescription = new FieldDescriptor[]{
                fieldWithPath("code").type(STRING).description("????????????(??????: 0000)"),
                fieldWithPath("message").type(STRING).description("???????????????(??????: OK)"),
                fieldWithPath("data.id").type(NUMBER).description("????????????"),
                fieldWithPath("data.memberNo").type(STRING).description("????????????"),
                fieldWithPath("data.orderNo").type(STRING).description("????????????"),
                fieldWithPath("data.orderStatus").type(STRING).attributes(generateEnumAttrs(OrderStatus.class, OrderStatus::getDescription)).description("????????????"),
                fieldWithPath("data.orderStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.products[].id").type(NUMBER).description("??????????????????"),
                fieldWithPath("data.products[].productName").type(STRING).description("?????????"),
                fieldWithPath("data.products[].productNo").type(STRING).description("????????????"),
                fieldWithPath("data.products[].productStatus").type(STRING).attributes(generateEnumAttrs(ProductStatus.class, ProductStatus::getDescription)).description("????????????"),
                fieldWithPath("data.products[].productStatusDescription").type(STRING).description("??????????????????"),
                fieldWithPath("data.products[].created").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.products[].modified").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("????????????"),
                fieldWithPath("data.orderDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????"),
                fieldWithPath("data.paymentMoney").type(NUMBER).description("????????????").optional(),
                fieldWithPath("data.paymentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.shipmentDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
                fieldWithPath("data.completedDateTime").type(STRING).attributes(customFormat(FORMAT_LOCAL_DATE_TIME)).description("??????????????????").optional(),
        };

        MockMvcFactory.getRestDocsMockMvc(contextProvider, HOST_LOCAL, controller)
                .perform(RestDocumentationRequestBuilders.put("/orders/{orderNo}/complete", orderNo)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //REST Docs ???
                .andDo(MockMvcRestDocumentation.document("put-v1-complete-order",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterDescriptor
                        ),
                        responseFields(
                                responseFieldDescription
                        )
                ))
                //OAS 3.0 - Swagger
                .andDo(MockMvcRestDocumentationWrapper.document("put-v1-complete-order",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(ResourceSnippetParameters.builder()
                                .pathParameters(
                                        parameterDescriptor
                                )
                                .responseFields(
                                        responseFieldDescription
                                )
                                .build())));
    }
}
