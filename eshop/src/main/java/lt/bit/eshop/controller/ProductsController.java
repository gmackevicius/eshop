package lt.bit.eshop.controller;

import lt.bit.eshop.entity.CategoryEntity;
import lt.bit.eshop.form.FilterModel;
import lt.bit.eshop.form.ProductModel;
import lt.bit.eshop.service.CategoryService;
import lt.bit.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category-list/{sort}")
    public String index(Model model, @PathVariable String sort, @ModelAttribute FilterModel filterModel) {

        model.addAttribute("categoryList", categoryService.getCategories());
        model.addAttribute("filterModel", filterModel);
        model.addAttribute("productList", productService.getProducts(sort, filterModel.getName()));

        return "category-list";
    }

    @GetMapping("/category-list/{categorySlug}/{sort}")
    public String products(@PathVariable String categorySlug, Model model, @PathVariable String sort, @ModelAttribute FilterModel filterModel){

        model.addAttribute("filterModel", filterModel);
        CategoryEntity categoryEntity = categoryService.findCategory(categorySlug);
        model.addAttribute("categoryList", categoryService.getCategories());
        model.addAttribute("productList", productService.getProductsByCategory(categoryEntity, sort, filterModel.getName()));

        model.addAttribute("slug", categorySlug);

        return "category-list";
    }

//    @RequestMapping( value = "/category-list", method = RequestMethod.POST)
//    public String findProduct(@RequestParam String name, Model model) {
//
//        model.addAttribute("productModel", new ProductModel());
//        model.addAttribute("productList", productService.getByName(name));
//        model.addAttribute("categoryList", categoryService.getCategories());
//
//        return "category-list";
//    }


}
