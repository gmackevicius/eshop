package lt.bit.eshop.controller;


import lt.bit.eshop.form.CategoryModel;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.service.CategoryService;
import lt.bit.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/create/products")
    public String productForm(Model model) {
        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("categoryList", categoryService.getCategories());
        return "product-form";
    }

    @RequestMapping( value = "/create/products", method = RequestMethod.POST)
    public String createProduct(@Valid @ModelAttribute ProductModel productModel, BindingResult bindingResult, Model model) {


        if(!bindingResult.hasErrors()) {
            productService.createProduct(productModel);
            model.addAttribute("productModel", new ProductModel());

            model.addAttribute("productList", productService.getProducts());
            return "product-list";
        }
        System.out.println(bindingResult.hasErrors());
        return "product-form";
    }

    @RequestMapping(value = "create/products/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {

        model.addAttribute("categoryList", categoryService.getCategories());
        ProductModel pm = productService.getById(id);
        model.addAttribute("productModel", pm );

        return "product-form";
    }

    @RequestMapping(value = "create/products/{id}", method = RequestMethod.POST )
    public String editProduct(@PathVariable Long id, Model model, ProductModel productModel) {

        productService.editProduct(productModel, id);

        model.addAttribute("productList", productService.getProducts());

        return "product-list";
    }

    @RequestMapping("/create/category")
    public String categoryForm(Model model) {
        model.addAttribute("categoryModel", new CategoryModel());
        model.addAttribute("categoryList", categoryService.getCategories());
        return "category-form";
    }

    @RequestMapping( value = "/create/category", method = RequestMethod.POST)
    public String createCategory(@Valid @ModelAttribute CategoryModel categoryModel, BindingResult bindingResult, Model model) {


        if(!bindingResult.hasErrors()) {
            categoryService.createCategory(categoryModel);
            model.addAttribute("categoryModel", new CategoryModel());
            model.addAttribute("categoryList", categoryService.getCategories());
            return "redirect:category";
        }
        System.out.println(bindingResult.hasErrors());
        return "category-form";
    }

    @RequestMapping("/product-list")
    public String productList(Model model) {

        model.addAttribute("productModel", new ProductModel());

        model.addAttribute("productList", productService.getProducts());

        return "product-list";
    }

    @RequestMapping( value = "/product-list", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam List<Long> id, Model model) {

        productService.deleteProduct(id);


        return productList(model);
    }

    @RequestMapping( value = "/create/category", method = RequestMethod.DELETE)
    public String deleteCategory(@RequestParam Long id, Model model) {

        categoryService.deleteCategory(id);


        return "redirect:category";
    }




}
