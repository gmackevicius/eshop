package lt.bit.eshop.form;

import lt.bit.eshop.entity.CategoryEntity;

import javax.validation.constraints.NotBlank;

public class CategoryModel {

    private Long id;

    @NotBlank(message="Name is required!")
    private String name;

    private String slug;


    public CategoryModel(CategoryEntity entity) {
        this.setName(entity.getName());
        this.setId(entity.getId());
        this.setSlug((entity.getSlug()));
    }

    public CategoryModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
