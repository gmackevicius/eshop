package lt.bit.eshop.controller;


import lt.bit.eshop.form.*;
import lt.bit.eshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

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

    @RequestMapping("/user-list/edit-roles/{id}")
    public String triggerEditRoleForm(@PathVariable Long id, Model model) {

        List<RoleModel> allRoles = roleService.getAllRoles();
        List<RoleModel> userRoles =  userService.getById(id).getRoles();
        List<RoleModel> userOwnedRoles = new ArrayList<>();
        List<RoleModel> nonOwnedRoles = new ArrayList<>();

        for(RoleModel r: allRoles) {
            if(userRoles.contains(r)) {
                userOwnedRoles.add(r);
            }
            else{
                nonOwnedRoles.add(r);
            }
        }

        model.addAttribute("checked", userOwnedRoles);
        model.addAttribute("unchecked", nonOwnedRoles);
        model.addAttribute("triggered", true);

        return userList(model);
    }

    @RequestMapping(value = "/user-list/edit-roles/{ID}", method = RequestMethod.POST)
    public String editUserRole(@PathVariable Long ID, Model model, @RequestParam(value="id", required = false) List<Long> id) {


        if(id != null) {
            userService.giveRole(ID, id);        }
        else {
            id = new ArrayList<>();
            userService.giveRole(ID, id);
        }

        model.addAttribute("triggered", false);


        return "redirect:/admin/user-list";
    }

    @RequestMapping("/create/role")
    public String roleAndauthorityForm(Model model) {

        model.addAttribute("roleModel", new RoleModel());
        model.addAttribute("authorityModel", new AuthorityModel());
        model.addAttribute("roleList", roleService.getAllRoles());


        return "role-form";
    }

    @RequestMapping(value = "/create/role", method = RequestMethod.POST)
    public String createRole(@Valid @ModelAttribute RoleModel roleModel, BindingResult bindingResult, Model model) {


        if(!bindingResult.hasErrors()) {
            roleService.createRole(roleModel);
            model.addAttribute("roleModel", new RoleModel());
            return "redirect:role";
        }
        model.addAttribute("authorityModel", new AuthorityModel());
        model.addAttribute("roleList", roleService.getAllRoles());
        model.addAttribute("authorityList", authorityService.getAllAuthorities());
        System.out.println(bindingResult.hasErrors());
        return "role-form";
    }

    @RequestMapping(value = "/delete-role/", method = RequestMethod.POST)
    public String deleteRole(@RequestParam Long id, Model model) {
        roleService.deleteRole(id);
        return roleAndauthorityForm(model);
    }

    @RequestMapping(value = "/create/authority", method = RequestMethod.POST)
    public String createAuthority(@Valid @ModelAttribute AuthorityModel authorityModel, BindingResult bindingResult, Model model) {


        if(!bindingResult.hasErrors()) {
            authorityService.createAuthority(authorityModel);
            model.addAttribute("authorityModel", new AuthorityModel());
            return "redirect:role";
        }
        model.addAttribute("roleModel", new RoleModel());
        model.addAttribute("roleList", roleService.getAllRoles());
        model.addAttribute("authorityList", authorityService.getAllAuthorities());
        System.out.println(bindingResult.hasErrors());
        return "role-form";
    }

    @RequestMapping("/delete-authorities/")
    public String triggerAuthDelete(Model model) {
        model.addAttribute("trigger", true);
        roleAndauthorityForm(model);
        model.addAttribute("authorityList", authorityService.getAllAuthorities());


        return "role-form";
    }

    @RequestMapping(value = "/delete-authorities/", method = RequestMethod.POST)
    public String deleteAuthorities(@RequestParam List<Long> id, Model model) {

        authorityService.deleteAuthority(id);

        model.addAttribute("trigger", false);

        return roleAndauthorityForm(model);
    }

    @RequestMapping(value = "/give-authorities/{id}")
    public String triggerAuthSelection(@PathVariable Long id, @ModelAttribute AuthorityModel authorityModel, @ModelAttribute RoleModel roleModel, Model model) {

        List<AuthorityModel> allAuthorities = authorityService.getAllAuthorities();
        List<AuthorityModel> roleAuthorities = roleService.getById(id).getAuthorities();
        List<AuthorityModel> roleOwnedAuthorities = new ArrayList<>();
        List<AuthorityModel> nonOwnedAuthorities = new ArrayList<>();

        for(AuthorityModel a: allAuthorities) {
            if(roleAuthorities.contains(a)) {
                roleOwnedAuthorities.add(a);
            }
            else{
                nonOwnedAuthorities.add(a);
            }
        }

        model.addAttribute("checked", roleOwnedAuthorities);
        model.addAttribute("unchecked", nonOwnedAuthorities);
        model.addAttribute("triggered", true);
        model.addAttribute("selectedRoleId", id);
        roleAndauthorityForm(model);

        return "role-form";
    }

    @RequestMapping(value = "/give-authorities/{ID}", method = RequestMethod.POST)
    public String editRoleAuth(@PathVariable Long ID, Model model, RoleModel roleModel, @RequestParam(value = "id", required = false) List<Long> id) {


        if(id != null) {
            roleService.giveAuthorities(ID, id);
        }
        else {
            id = new ArrayList<>();
            roleService.giveAuthorities(ID, id);
        }

        model.addAttribute("triggered", false);


        return "redirect:/admin/create/role";
    }




}
