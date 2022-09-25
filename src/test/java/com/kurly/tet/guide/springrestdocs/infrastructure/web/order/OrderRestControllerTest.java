package com.kurly.tet.guide.springrestdocs.infrastructure.web.order;

import com.kurly.tet.guide.springrestdocs.application.order.OrderFacade;
import com.kurly.tet.guide.springrestdocs.domain.exception.OrderNotFoundException;
import com.kurly.tet.guide.springrestdocs.domain.exception.ProductNotFoundException;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import com.kurly.tet.guide.springrestdocs.domain.product.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@DisplayName("주문API")
@WebMvcTest({OrderRestController.class})
class OrderRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderFacade orderFacade;

    @DisplayName("검색: 최대조회크기(size, 1000) 초과하는 경우 400 오류")
    @Test
    void testSearchWhenOverMax() throws Exception {
        this.mockMvc.perform(
                        get("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("size", "1001")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C400\",\"message\":\"잘못된 요청입니다. 요청내용을 확인하세요.\",\"data\":null}"));
    }

    @DisplayName("생성: 회원번호(memberNo)를 누락한 경우 400 오류")
    @Test
    void testCreateOrderWhenMissingMemberNo() throws Exception {
        var requestContent = """
                {
                    "memberNo":"",
                    "ids":[1]
                }
                """;
        this.mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestContent)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C400\",\"message\":\"잘못된 요청입니다. 요청내용을 확인하세요.\",\"data\":null}"));
    }

    @DisplayName("생성: 회원번호(memberNo)를 누락한 경우 400 오류")
    @Test
    void testCreateOrderWhenEmptyids() throws Exception {
        var requestContent = """
                {
                    "memberNo":"202209251508",
                    "ids":[]
                }
                """;
        this.mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestContent)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C400\",\"message\":\"잘못된 요청입니다. 요청내용을 확인하세요.\",\"data\":null}"));
    }

    @DisplayName("생성: 주문 정상생성")
    @Test
    void testCreate() throws Exception {
        var products = List.of(new ProductDto(1L, "Product1", "P0001"));
        var orderDto = new OrderDto(1L, "202209251508", "TX-00001", products);
        Mockito.when(orderFacade.create(Mockito.any()))
                .thenReturn(orderDto);

        var requestContent = """
                {
                    "memberNo":"202209251508",
                    "productIds":[1]
                }
                """;
        this.mockMvc.perform(
                        post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestContent)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("조회: 요청정보를 찾지 못하는 경우 404 오류")
    @Test
    void testGetOrderWhenNotFound() throws Exception {
        var orderNo = UUID.randomUUID().toString();
        Mockito.when(orderFacade.findByOrderNo(orderNo))
                .thenThrow(new ProductNotFoundException(Long.MAX_VALUE));

        this.mockMvc.perform(
                        get("/orders/{orderNo}", orderNo)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C404\",\"message\":\"상품(ID: 9223372036854775807)을 찾을 수 없습니다.\",\"data\":null}"));
    }

    @DisplayName("조회: 요청정보를 찾지 못하는 경우 404 오류")
    @Test
    void testFindByOrderNoWhenNotFound() throws Exception {
        var orderNo = UUID.randomUUID().toString();
        Mockito.when(orderFacade.findByOrderNo(orderNo))
                .thenThrow(new ProductNotFoundException(Long.MAX_VALUE));

        this.mockMvc.perform(
                        get("/orders/{orderNo}", orderNo)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C404\",\"message\":\"상품(ID: 9223372036854775807)을 찾을 수 없습니다.\",\"data\":null}"));
    }

    @DisplayName("결제: 결제금액이 누락된 경우")
    @Test
    void testPayment() throws Exception {
        var orderNo = UUID.randomUUID().toString();

        String modifyContent = """
                {
                    "paymentMoney":null // 상품명 누락
                }
                """;

        this.mockMvc.perform(
                        put("/orders/{orderNo}/payment", orderNo)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(modifyContent)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C400\",\"message\":\"잘못된 요청입니다. 요청내용을 확인하세요.\",\"data\":null}"));
    }

    @DisplayName("출하: 주문번호 누락된 경우")
    @Test
    void testShipping() throws Exception {
        var orderNo = "797a1eb9-d6b8-4875-9ac6-4cbeac65ba40";

        Mockito.when(orderFacade.shipping(orderNo))
                .thenThrow(new OrderNotFoundException(orderNo));

        this.mockMvc.perform(
                        put("/orders/{orderNo}/shipping", orderNo)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C404\",\"message\":\"주문(주문번호: 797a1eb9-d6b8-4875-9ac6-4cbeac65ba40)을 찾을 수 없습니다.\",\"data\":null}"));
    }
}