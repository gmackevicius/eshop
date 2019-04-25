package lt.bit.eshop.controller;


import lt.bit.eshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebServiceController {

    @Autowired
    CategoryService categoryService;
}
