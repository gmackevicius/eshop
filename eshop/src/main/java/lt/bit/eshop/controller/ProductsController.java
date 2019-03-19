package lt.bit.eshop.controller;

import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.service.CategoryService;
import lt.bit.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category-list")
    public String index(Model model) {

        model.addAttribute("productList", productService.getProducts());
        model.addAttribute("categoryList", categoryService.getCategories());

        return "category-list";
    }

    @GetMapping("/{categorySlug}")
    public String products(@PathVariable String categorySlug, Model model){

        CategoryEntity categoryEntity = categoryService.findCategory(categorySlug);
        model.addAttribute("categoryList", categoryService.getCategories());
        model.addAttribute("productList", productService.getProductsByCategory(categoryEntity));

        return "category-list";
    }
}
