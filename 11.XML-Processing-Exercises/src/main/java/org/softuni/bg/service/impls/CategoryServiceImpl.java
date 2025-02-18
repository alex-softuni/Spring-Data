package org.softuni.bg.service.impls;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.modelmapper.ModelMapper;
import org.softuni.bg.model.entities.Category;
import org.softuni.bg.model.repositories.CategoryRepository;
import org.softuni.bg.service.CategoryService;
import org.softuni.bg.service.dtos.imports.CategorySeedDto;
import org.softuni.bg.service.dtos.imports.CategorySeedRootDto;
import org.softuni.bg.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String XML_PATH = "src/main/resources/xml/categories.xml";

    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean isImported() {
        return this.categoryRepository.count() > 0;
    }

    @Override
    public void seedCategories() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CategorySeedRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        CategorySeedRootDto unmarshal = (CategorySeedRootDto) unmarshaller.unmarshal(new File(XML_PATH));

        for (CategorySeedDto category : unmarshal.getCategories()) {
            if (!this.validatorUtil.isValid(category)) {
                this.validatorUtil.getViolations(category).forEach(System.out::println);
                continue;
            }
            this.categoryRepository.save(this.modelMapper.map(category, Category.class));
        }


    }
}
