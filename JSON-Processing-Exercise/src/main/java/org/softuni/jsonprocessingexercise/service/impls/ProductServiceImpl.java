package org.softuni.jsonprocessingexercise.service.impls;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.softuni.jsonprocessingexercise.model.dtos.ProductSeedDTO;
import org.softuni.jsonprocessingexercise.model.entities.Product;
import org.softuni.jsonprocessingexercise.model.repositories.ProductRepository;
import org.softuni.jsonprocessingexercise.service.ProductService;
import org.softuni.jsonprocessingexercise.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String FILE_PATH = "src/main/resources/json/products.json";

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        if (this.productRepository.count() == 0) {
            ProductSeedDTO[] productSeedDTOS = this.gson.fromJson(new FileReader(FILE_PATH), ProductSeedDTO[].class);
            for (ProductSeedDTO productSeedDTO : productSeedDTOS) {
                if (!this.validationUtil.isValid(productSeedDTO)) {
                    this.validationUtil.getViolations(productSeedDTO)
                            .forEach(v -> System.out.println(v.getMessage()));
                    continue;
                }
                Product product = this.modelMapper.map(productSeedDTO, Product.class);
            }
        }
    }
}
