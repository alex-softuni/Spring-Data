package org.softuni.bg.service.impls;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.modelmapper.ModelMapper;
import org.softuni.bg.model.entities.Category;
import org.softuni.bg.model.entities.Product;
import org.softuni.bg.model.entities.User;
import org.softuni.bg.model.repositories.CategoryRepository;
import org.softuni.bg.model.repositories.ProductRepository;
import org.softuni.bg.model.repositories.UserRepository;
import org.softuni.bg.service.ProductService;
import org.softuni.bg.service.dtos.imports.ProductSeedRootDto;
import org.softuni.bg.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String XML_PATH = "src/main/resources/xml/products.xml";

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ProductSeedRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ProductSeedRootDto unmarshal = (ProductSeedRootDto) unmarshaller.unmarshal(new File(XML_PATH));

        List<Product> products = new ArrayList<>();

        unmarshal.getProducts().forEach(dto -> {
            if (!this.validatorUtil.isValid(dto)) {
                this.validatorUtil.getViolations(dto).forEach(System.out::println);
                return;
            }

            Product product = this.modelMapper.map(dto, Product.class);
            product.setCategories(getRandomCategories());
            product.setBuyer(getRandomUser());
            product.setSeller(getRandomUser());
            products.add(product);
        });

        this.productRepository.saveAll(products);
    }

    private User getRandomUser() {
        return this.userRepository.findById(ThreadLocalRandom.current().nextLong(1, this.categoryRepository.count() + 1)).orElse(null);
    }

    private Set<Category> getRandomCategories() {

        Set<Category> categories = new HashSet<>();
        long randomId = ThreadLocalRandom.current().nextLong(1, this.categoryRepository.count() + 1);
        long randomCount = ThreadLocalRandom.current().nextLong(1, 3);
        for (int i = 0; i < randomCount; i++) {
            categories.add(this.categoryRepository.findById(randomId).orElse(null));
        }
        return categories;
    }

    @Override
    public boolean isImported() {
        return this.productRepository.count() > 0;
    }
}
