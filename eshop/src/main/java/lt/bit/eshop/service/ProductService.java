package lt.bit.eshop.service;


import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.entity.Product;
import lt.bit.eshop.form.CategoryModel;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.repository.CategoryRepository;
import lt.bit.eshop.repository.ProductRepository;
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

    public void createProduct(ProductModel productModel){

        Product productEntity = new Product();

        productEntity.setName(productModel.getName());
        productEntity.setDescription(productModel.getDescription());
        productEntity.setPrice(productModel.getPrice());

        Optional<CategoryEntity> categoryEnt = categoryRepository.findById(productModel.getCategoryId());

        CategoryEntity categoryEntity = categoryEnt.get();


        productEntity.setCategory(categoryEntity);

        productRepository.save(productEntity);
    }

    public ProductModel getById(Long id) {

        Product productEntity = null;
        Optional<Product> product =  productRepository.findById(id);
        if(product.isPresent()) {
           productEntity = product.get();
        }

        return new ProductModel(productEntity);
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

        Optional<CategoryEntity> categoryEnt = categoryRepository.findById(model.getCategoryId());

        CategoryEntity categoryEntity = categoryEnt.get();
        productEntity.setCategory(categoryEntity);

        productRepository.save(productEntity);
    }

    public List<ProductModel> getProducts(String sortBy) {


        Sort sort = new Sort(ASC, "price");

        List<ProductModel> productList;

        List<Product> products = (List<Product>) productRepository.findAll(sortBy(sortBy));

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

        List<ProductModel> productList;

        List<Product> products = (List<Product>) productRepository.findByCategory(category, sortBy(sortBy));

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

        if(order.equals(ASC)) {
            return new Sort(Sort.Direction.ASC, result[0]);
        } else {
            return new Sort(Sort.Direction.DESC, result[0]);
        }

    }
}
