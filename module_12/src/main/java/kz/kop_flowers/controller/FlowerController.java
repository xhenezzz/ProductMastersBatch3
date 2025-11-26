package kz.kop_flowers.controller;

import kz.kop_flowers.model.dto.FlowerDto;
import kz.kop_flowers.model.entity.Flower;
import kz.kop_flowers.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flowers")
@RequiredArgsConstructor
public class FlowerController {

    private final FlowerService flowerService;

    @GetMapping()
    public List<FlowerDto> getFlowers() {
        return flowerService.getAllFlowers();
    }

    @GetMapping("/{id}")
    public FlowerDto getFlowerById(
            @PathVariable Integer id
    ) {
        return flowerService.getFlowerById(id);
    }

    @PostMapping("/create")
    public FlowerDto createFlower(
            @RequestBody FlowerDto flower
    ) {
        return flowerService.createFlower(flower);
    }

    @DeleteMapping("/{id}")
    public String deleteFlower(@PathVariable Integer id) {
        flowerService.deleteFlowerById(id);
        return "Flower with id " + id + " deleted";
    }

    @GetMapping("/category/{categoryId}")
    public List<FlowerDto> getFlowersByCategory(@PathVariable Integer categoryId) {
        return flowerService.getFlowersByCategoryId(categoryId);
    }

    @PutMapping("/{id}")
    public Flower updateFlower(
            @PathVariable Integer id,
            @RequestBody FlowerDto flowerDto
    ) {
        return flowerService.updateFlower(id, flowerDto);
    }

}
