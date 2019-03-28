package lt.bit.eshop.controller;


import lt.bit.eshop.form.*;
import lt.bit.eshop.service.*;
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

    @Autowired
    private CustomUserDetailService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthorityService authorityService;

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

            model.addAttribute("productList", productService.getProducts("id-DESC"));
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

        model.addAttribute("productList", productService.getProducts("id-DESC"));

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

        model.addAttribute("productList", productService.getProducts("id-DESC"));

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

    @RequestMapping("/user-list")
    public String userList(Model model) {

        model.addAttribute("userModel", new UserModel());
        model.addAttribute("userList", userService.getAllUsers());

        return "user-list";
    }

    @RequestMapping("/create/role")
    public String roleAndauthorityForm(Model model) {

        model.addAttribute("roleModel", new RoleModel());
        model.addAttribute("authorityModel", new AuthorityModel());
        model.addAttribute("roleList", roleService.getAllRoles());

        return "role-form";
    }

    @RequestMapping(value = "/create/role", method = RequestMethod.POST)
    public String createRole(@Valid @ModelAttribute RoleModel roleModel, @ModelAttribute AuthorityModel authorityModel, BindingResult bindingResult, Model model) {


        if(!bindingResult.hasErrors()) {
            roleService.createRole(roleModel);
            model.addAttribute("roleModel", new RoleModel());
            model.addAttribute("roleList", roleService.getAllRoles());
            return "redirect:role";
        }
        System.out.println(bindingResult.hasErrors());
        return "role-form";
    }

    @RequestMapping(value = "/create/authority", method = RequestMethod.POST)
    public String createAuthority(@Valid @ModelAttribute AuthorityModel authorityModel, @ModelAttribute RoleModel roleModel, BindingResult bindingResult, Model model) {


        if(!bindingResult.hasErrors()) {
            authorityService.createAuthority(authorityModel);
            model.addAttribute("authorityModel", new AuthorityModel());
//            model.addAttribute("roleList", roleService.getAllRoles());
            return "redirect:role";
        }
        System.out.println(bindingResult.hasErrors());
        return "role-form";
    }

    @RequestMapping(value = "/give-authorities/{id}")
    public String authSelection(@PathVariable Long id, @ModelAttribute AuthorityModel authorityModel, @ModelAttribute RoleModel roleModel, Model model) {

        model.addAttribute("roleModel", new RoleModel());
        model.addAttribute("roleList", roleService.getAllRoles());
        model.addAttribute("authorityList", authorityService.getAllAuthorities());

        return "role-form";
    }




}
