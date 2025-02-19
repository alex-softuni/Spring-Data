package org.softuni.bg.service.impls;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.softuni.bg.model.entities.Category;
import org.softuni.bg.model.repositories.CategoryRepository;
import org.softuni.bg.service.CategoryService;
import org.softuni.bg.service.dtos.imports.CategorySeedDto;
import org.softuni.bg.service.dtos.imports.CategorySeedRootDto;
import org.softuni.bg.util.ValidatorUtil;
import org.softuni.bg.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String XML_PATH = "src/main/resources/xml/categories.xml";

    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }


    @Override
    public boolean isImported() {
        return this.categoryRepository.count() > 0;
    }

    @Override
    public void seedCategories() throws JAXBException {

        CategorySeedRootDto parse = this.xmlParser.parse(CategorySeedRootDto.class, XML_PATH);

        for (CategorySeedDto category : parse.getCategories()) {
            if (!this.validatorUtil.isValid(category)) {
                this.validatorUtil.getViolations(category).forEach(System.out::println);
                continue;
            }
            this.categoryRepository.save(this.modelMapper.map(category, Category.class));

        }


    }
}
