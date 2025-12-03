package kop_flowers;

import kz.kop_flowers.model.FlowerMapper;
import kz.kop_flowers.model.dto.CategoryDto;
import kz.kop_flowers.model.entity.Category;
import kz.kop_flowers.model.exception.CategoryNotFoundException;
import kz.kop_flowers.repository.CategoryRepository;
import kz.kop_flowers.service.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private FlowerMapper mapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;


    @Test
    void testGetCategoryById_success() {
        Category category = Category.builder().id(1).name("Flowers").build();

        Mockito.when(categoryRepository.findById(1))
                .thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(1);

        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("Flowers", result.getName());

        verify(categoryRepository).findById(1);
    }

    @Test
    void testGetCategoryById_isEmpty() {
        Mockito.when(categoryRepository.findById(99))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                CategoryNotFoundException.class,
                () -> categoryService.getCategoryById(99)
        );

        verify(categoryRepository).findById(99);
    }

    @Test
    void testGetCategoryDtoById_success() {
        Category category = Category.builder().id(1).name("8 марта").build();
        CategoryDto categoryDto = CategoryDto.builder().id(1).name("8 марта").build();

        Mockito.when(categoryRepository.findById(1))
                .thenReturn(Optional.of(category));
        Mockito.when(mapper.fromEntityToDto(category))
                .thenReturn(categoryDto);

        CategoryDto result = categoryService.getCategoryDtoById(1);

        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("8 марта", result.getName());

        verify(categoryRepository).findById(1);
        verify(mapper).fromEntityToDto(category);
    }

    @Test
    void testGetAllCategories_success() {
        List<Category> categories = List.of(
                Category.builder().id(1).name("A").build(),
                Category.builder().id(2).name("B").build()
        );

        List<CategoryDto> dtos = List.of(
                CategoryDto.builder().id(1).name("A").build(),
                CategoryDto.builder().id(2).name("B").build()
        );

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        Mockito.when(mapper.fromEntityToDto(categories.get(0))).thenReturn(dtos.get(0));
        Mockito.when(mapper.fromEntityToDto(categories.get(1))).thenReturn(dtos.get(1));

        List<CategoryDto> result = categoryService.getAllCategories();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("A", result.get(0).getName());

        verify(categoryRepository).findAll();
        verify(mapper, times(2)).fromEntityToDto(any(Category.class));
    }

    @Test
    void testGetAllCategories_empty() {
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of());

        List<CategoryDto> result = categoryService.getAllCategories();

        Assertions.assertTrue(result.isEmpty());

        verify(categoryRepository).findAll();
    }

    @Test
    void testCreateCategory_success() {
        CategoryDto inputDto = CategoryDto.builder().name("Test").build();

        Category savedEntity = Category.builder().id(10).name("Test").build();
        CategoryDto outputDto = CategoryDto.builder().id(10).name("Test").build();

        Mockito.when(categoryRepository.save(any(Category.class)))
                .thenReturn(savedEntity);
        Mockito.when(mapper.fromEntityToDto(savedEntity))
                .thenReturn(outputDto);

        CategoryDto result = categoryService.createCategory(inputDto);

        Assertions.assertEquals(10, result.getId());
        Assertions.assertEquals("Test", result.getName());

        verify(categoryRepository).save(any(Category.class));
        verify(mapper).fromEntityToDto(savedEntity);
    }
}
