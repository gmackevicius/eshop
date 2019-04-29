package lt.bit.eshop.service;


import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.entity.Product;
import lt.bit.eshop.form.CategoryModel;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.repository.CategoryRepository;
import lt.bit.eshop.repository.ProductRepository;
import lt.bit.eshop.validation.exceptions.ProductNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    public Product createProduct(ProductModel productModel){

        Product productEntity = new Product();

        productEntity.setName(productModel.getName());
        productEntity.setDescription(productModel.getDescription());
        productEntity.setPrice(productModel.getPrice());
        productEntity.setQuantity(productModel.getQuantity());

        Optional<CategoryEntity> categoryEnt = categoryRepository.findById(productModel.getCategoryId());

        CategoryEntity categoryEntity = categoryEnt.get();


        productEntity.setCategory(categoryEntity);

       return productRepository.save(productEntity);
    }

    public String constructImageName(Long id) {
        StringBuilder builder = new StringBuilder();

        builder.append("product_");
        builder.append(id.toString());

        return builder.toString();
    }

    public void attachImage(Long productId, String imageName) throws ProductNotFound {
        Optional<Product> optional = this.productRepository.findById(productId);

        if (!optional.isPresent()) {
            throw new ProductNotFound("Product " + productId + " not found");
        }

        Product product = optional.get();

        product.setImageName(imageName);

        this.productRepository.save(product);
    }

    public Product getById(Long id) {

        Product productEntity = null;
        Optional<Product> product =  productRepository.findById(id);
        if(product.isPresent()) {
           productEntity = product.get();
        }

        return productEntity;
    }

    public List<ProductModel> getByName(String name) {
        List<ProductModel> productList;
        List<Product> products =  productRepository.findByNameContaining(name);


       return  productList = products.stream().map(ProductModel::new).collect(Collectors.toList());
    }

    public static void modelToEntityAndSave(ProductModel model) {

    }

    public void editProduct(ProductModel model, Long id) {
        Product productEntity = null;
        Optional<Product> product =  productRepository.findById(id);
        if(product.isPresent()) {
            productEntity = product.get();
        }
        productEntity.setName(model.getName());
        productEntity.setDescription(model.getDescription());
        productEntity.setPrice(model.getPrice());
        productEntity.setQuantity(model.getQuantity());

        Optional<CategoryEntity> categoryEnt = categoryRepository.findById(model.getCategoryId());

        CategoryEntity categoryEntity = categoryEnt.get();
        productEntity.setCategory(categoryEntity);


        productRepository.save(productEntity);
    }

    public List<ProductModel> getProducts(String sortBy) {
        return getProducts(sortBy, null);
    }

    public List<ProductModel> getProducts(String sortBy, String name) {




        List<ProductModel> productList;
        List<Product> products ;

        if(name != null) {
            products = this.productRepository.findByNameContaining(name);
        } else {
            products = this.productRepository.findAll(sortBy(sortBy));
        }




//           productList = products.stream().map(p -> {
//                ProductModel product = new ProductModel();
//
//                product.setId(p.getId());
//                product.setName(p.getName());
//                product.setDescription(p.getDescription());
//                product.setPrice(p.getPrice());
//                return product;
//            }).collect(Collectors.toList());

        productList = products.stream().map(ProductModel::new).collect(Collectors.toList());


        return productList;
    }

    public List<ProductModel> getProductsByCategory(CategoryEntity category, String sortBy) {
        return getProductsByCategory(category, sortBy, null);
    }

    public List<ProductModel> getProductsByCategory(CategoryEntity category, String sortBy, String name) {

        List<ProductModel> productList;

        List<Product> products;

        if(name != null) {
           products = productRepository.findByCategoryAndNameContaining(category, sortBy(sortBy), name);
        } else {
           products = productRepository.findByCategory(category, sortBy(sortBy));
        }

        return  productList = products.stream().map(ProductModel::new).collect(Collectors.toList());
    }

    public void deleteProduct(List<Long> id) {
        for(Long i : id)
        productRepository.deleteById(i);
    }

    public static Sort sortBy(String request) {

        String[] result = request.split("-", 2);
        String order = result[1];
        String sortRequest = result[0];

        if(order.equals("ASC")) {
            return new Sort(Sort.Direction.ASC, sortRequest);
        } else {
            return new Sort(Sort.Direction.DESC, sortRequest);
        }

    }
}
