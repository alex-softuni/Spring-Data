package org.softuni.bg.service.impls;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.softuni.bg.model.entities.Category;
import org.softuni.bg.model.entities.Product;
import org.softuni.bg.model.entities.User;
import org.softuni.bg.model.repositories.CategoryRepository;
import org.softuni.bg.model.repositories.ProductRepository;
import org.softuni.bg.model.repositories.UserRepository;
import org.softuni.bg.service.ProductService;
import org.softuni.bg.service.dtos.exports.ProductsInPriceRangeDto;
import org.softuni.bg.service.dtos.exports.ProductsInPriceRangeRootDto;
import org.softuni.bg.service.dtos.imports.ProductSeedRootDto;
import org.softuni.bg.util.ValidatorUtil;
import org.softuni.bg.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
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
    private final XmlParser xmlParser;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedProducts() throws JAXBException {
        ProductSeedRootDto parse = this.xmlParser.parse(ProductSeedRootDto.class, XML_PATH);

        List<Product> products = new ArrayList<>();

        parse.getProductSeedDto().forEach(dto -> {
            if (!this.validatorUtil.isValid(dto)) {
                this.validatorUtil.getViolations(dto).forEach(System.out::println);
                return;
            }

            Product product = this.modelMapper.map(dto, Product.class);
            product.setCategories(getRandomCategories());
            product.setBuyer(getRandomUser(true));
            product.setSeller(getRandomUser(false));
            products.add(product);
        });

        this.productRepository.saveAll(products);
    }

    @Override
    public void exportProductsInPriceRangeOrderedByPrice(BigDecimal lower, BigDecimal upper) throws JAXBException {
        List<ProductsInPriceRangeDto> dto = this.productRepository.findProductsInPriceRangeOrderedByPrice(lower, upper)
                .stream()
                .map(p -> this.modelMapper.map(p, ProductsInPriceRangeDto.class))
                .toList();

        ProductsInPriceRangeRootDto rootDto = new ProductsInPriceRangeRootDto();
        rootDto.setProducts(dto);
        this.xmlParser.exportToFile(rootDto, XML_PATH);

    }

    private User getRandomUser(boolean isBuyer) {
        List<Long> userIds = this.userRepository.findAllIds();  // Fetch all IDs
        if (userIds.isEmpty()) return null;

        long randomId = userIds.get(ThreadLocalRandom.current().nextInt(userIds.size()));

        return isBuyer && randomId % 4 == 0 ? null : this.userRepository.getReferenceById(randomId);
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
