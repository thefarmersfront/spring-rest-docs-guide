package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import com.kurly.tet.guide.springrestdocs.application.product.ProductFacade;
import com.kurly.tet.guide.springrestdocs.domain.exception.ProductNotFoundException;
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

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@DisplayName("상품API")
@WebMvcTest({ProductRestController.class})
class ProductRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductFacade productFacade;

    @DisplayName("검색: 최대조회크기(size, 1000) 초과하는 경우 400 오류")
    @Test
    void testSearchWhenOverMax() throws Exception {
        this.mockMvc.perform(
                        get("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("size", "1001")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C400\",\"message\":\"잘못된 요청입니다. 요청내용을 확인하세요.\",\"data\":null}"));
    }

    @DisplayName("생성: 상품명을 누락한 경우 400 오류")
    @Test
    void testCreateProduct() throws Exception {
        var requestContent = """
                {
                    "productName":"",
                    "productNo":"1234"
                }
                """;
        this.mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestContent)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C400\",\"message\":\"잘못된 요청입니다. 요청내용을 확인하세요.\",\"data\":null}"));
    }

    @DisplayName("조회: 요청정보를 찾지 못하는 경우 404 오류")
    @Test
    void testGetProductWhenNotFound() throws Exception {
        Mockito.when(productFacade.getProduct(Long.MAX_VALUE))
                .thenThrow(new ProductNotFoundException(Long.MAX_VALUE));

        this.mockMvc.perform(
                        get("/products/{productId}", Long.MAX_VALUE)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C404\",\"message\":\"상품(ID: 9223372036854775807)을 찾을 수 없습니다.\",\"data\":null}"));
    }

    @DisplayName("변경: 요청정보를 찾지 못하는 경우 404 오류")
    @Test
    void testModifyProductWhenNotFound() throws Exception {
        Mockito.when(productFacade.modify(Mockito.anyLong(), Mockito.any()))
                .thenThrow(new ProductNotFoundException(Long.MAX_VALUE));

        String modifyContent = """
                {
                    "productName":"1234",
                    "productNo":"1234",
                    "productStatus":"ACTIVATED"
                }
                """;
        this.mockMvc.perform(
                        put("/products/{productId}", Long.MAX_VALUE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(modifyContent)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C404\",\"message\":\"상품(ID: 9223372036854775807)을 찾을 수 없습니다.\",\"data\":null}"));
    }

    @DisplayName("변경: 상품명을 누락한 경우 404 오류")
    @Test
    void testModify02() throws Exception {
        String modifyContent = """
                {
                    "productName":"", // 상품명 누락
                    "productNo":"1234",
                    "productStatus":"ACTIVATED"
                }
                """;

        this.mockMvc.perform(
                        put("/products/{productId}", Long.MAX_VALUE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(modifyContent)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":\"C400\",\"message\":\"잘못된 요청입니다. 요청내용을 확인하세요.\",\"data\":null}"));
    }

    @DisplayName("삭제: 별도 처리 없음. NOT_CONTENT 반환")
    @Test
    void testDeleteWhenNotFound() throws Exception {
        this.mockMvc.perform(
                        delete("/products/{productId}", Long.MAX_VALUE)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}