package kz.kop_flowers.service;

import jakarta.transaction.Transactional;
import kz.kop_flowers.model.FlowerMapper;
import kz.kop_flowers.model.dto.CategoryDto;
import kz.kop_flowers.model.dto.FlowerDto;
import kz.kop_flowers.model.entity.Category;
import kz.kop_flowers.model.entity.Flower;
import kz.kop_flowers.model.exception.CategoryNotFoundException;
import kz.kop_flowers.model.exception.FlowerNotFoundException;
import kz.kop_flowers.repository.CategoryRepository;
import kz.kop_flowers.repository.FlowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlowerServiceImpl implements FlowerService {

    private final FlowerRepository flowerRepository;
    private final CategoryRepository categoryRepository;
    private final FlowerMapper mapper;
    private final CategoryService categoryService;

    @Override
    public List<FlowerDto> getAllFlowers() {
        List<Flower> flowers = flowerRepository.findAll();
        return flowers.stream()
                .map(mapper::fromEntityToDto)
                .toList();
    }

    @Override
    public FlowerDto getFlowerById(Integer id) {
        Flower flower = flowerRepository.findById(id).orElseThrow(() -> new FlowerNotFoundException("Flower is not exists"));
        return mapper.fromEntityToDto(flower);
    }

    @Override
    public FlowerDto createFlower(FlowerDto flowerDto) {
        Flower flower = Flower.builder()
                .name(flowerDto.getName())
                .price(flowerDto.getPrice())
                .size(flowerDto.getSize())
                .category(categoryService.getCategoryById(flowerDto.getCategory().getId()))
                .build();
        flower = flowerRepository.save(flower);
        return mapper.fromEntityToDto(flower);
    }

    @Override
    public void deleteFlowerById(Integer id) {
        Flower flower = flowerRepository.findById(id)
                .orElseThrow(() -> new FlowerNotFoundException("Flower with id " + id + " not found"));

        flowerRepository.delete(flower);
    }

    @Override
    public List<FlowerDto> getFlowersByCategoryId(Integer categoryId) {
        List<Flower> flowers = flowerRepository.findByCategoryId(categoryId);

        if (flowers.isEmpty()) {
            throw new FlowerNotFoundException("No flowers found for category id " + categoryId);
        }

        return flowers.stream()
                .map(flower -> FlowerDto.builder()
                        .id(flower.getId())
                        .name(flower.getName())
                        .price(flower.getPrice())
                        .size(flower.getSize())
                        .category(
                                CategoryDto.builder()
                                        .id(flower.getCategory().getId())
                                        .name(flower.getCategory().getName())
                                        .build()
                        )
                        .build())
                .toList();

    }




    @Override
    @Transactional
    public Flower updateFlower(Integer id, FlowerDto flowerDto) {

        Flower existingFlower = flowerRepository.findById(id)
                .orElseThrow(() -> new FlowerNotFoundException("Flower with id " + id + " not found"));

        existingFlower.setName(flowerDto.getName());
        existingFlower.setPrice(flowerDto.getPrice());
        existingFlower.setSize(flowerDto.getSize());

        Category category = categoryRepository.findById(flowerDto.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        existingFlower.setCategory(category);

        return flowerRepository.save(existingFlower);
    }



}
