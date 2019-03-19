package lt.bit.eshop.service;


import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.form.CategoryModel;
import lt.bit.eshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(CategoryModel categoryModel) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(categoryModel.getName());

        String slug = categoryModel
                .getName()
                .trim()
                .toLowerCase()
                .replace(" ", "-");

        entity.setSlug(slug);



        this.categoryRepository.save(entity);
    }

    public List<CategoryModel> getCategories() {

        List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepository.findAll();

        return categories.stream().map(CategoryModel::new).collect(Collectors.toList());
    }

    public CategoryEntity findCategory(String categorySlug) {

        return categoryRepository.findBySlug(categorySlug);

    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
