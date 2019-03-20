package lt.bit.eshop.form;

import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.entity.Product;
import org.hibernate.validator.constraints.Range;

import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductModel {

    private Long id;
    @NotBlank(message="Name is required!")
    private String name;
    @NotBlank(message="Description is required!")
    private String description;
    @NotNull(message="Price is required!")
    @DecimalMin(value = "0.1")
    private double price;
    @NotNull(message="Category is required!")
    private Long categoryId;
    private String categoryName;


    public ProductModel(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
        this.setCategoryName(product.getCategory().getName());

    }

    public ProductModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
