package kop_flowers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.kop_flowers.controller.CategoryController;
import kz.kop_flowers.model.dto.CategoryDto;
import kz.kop_flowers.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCategories() throws Exception {

        List<CategoryDto> mockList = List.of(
                CategoryDto.builder().id(1).name("Birthday").build(),
                CategoryDto.builder().id(2).name("Love").build()
        );

        Mockito.when(categoryService.getAllCategories()).thenReturn(mockList);

        mockMvc.perform(get("/api/category/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Birthday"))
                .andExpect(jsonPath("$[1].name").value("Love"));

        // verify() — вызываем ли метод сервисa
        verify(categoryService).getAllCategories();
    }


    @Test
    void testCreateCategory() throws Exception {

        CategoryDto requestDto = CategoryDto.builder()
                .name("Flowers")
                .build();

        CategoryDto responseDto = CategoryDto.builder()
                .id(100)
                .name("Flowers")
                .build();

        Mockito.when(categoryService.createCategory(Mockito.any(CategoryDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(
                        post("/api/category")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.name").value("Flowers"));

        // --- verify that service method was called ---
        ArgumentCaptor<CategoryDto> captor = ArgumentCaptor.forClass(CategoryDto.class);

        verify(categoryService).createCategory(captor.capture());

        CategoryDto capturedArgument = captor.getValue();

        // Проверяем что контроллер передал ДТО в сервис корректно
        assertThat(capturedArgument.getName()).isEqualTo("Flowers");
    }
}
