package lt.bit.eshop.controller;


import lt.bit.eshop.form.CategoryModel;
import lt.bit.eshop.form.ProductModel;
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

    @RequestMapping("/create/products")
    public String productForm(Model model) {
        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("categoryList", productService.getCategories());
        return "product-form";
    }

    @RequestMapping( value = "/create/products", method = RequestMethod.POST)
    public String createProduct(@Valid @ModelAttribute ProductModel productModel, BindingResult bindingResult, Model model) {


        if(!bindingResult.hasErrors()) {
            productService.createProduct(productModel);
            model.addAttribute("productModel", new ProductModel());
            return "redirect:products";
        }
        System.out.println(bindingResult.hasErrors());
        return "product-form";
    }

    @RequestMapping("/create/category")
    public String categoryForm(Model model) {
        model.addAttribute("categoryModel", new CategoryModel());
        return "category-form";
    }

    @RequestMapping( value = "/create/category", method = RequestMethod.POST)
    public String createCategory(@Valid @ModelAttribute CategoryModel categoryModel, BindingResult bindingResult, Model model) {


        if(!bindingResult.hasErrors()) {
            productService.createCategory(categoryModel);
            model.addAttribute("categoryModel", new CategoryModel());
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




}
