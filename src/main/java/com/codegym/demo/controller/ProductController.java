package com.codegym.demo.controller;

import com.codegym.demo.model.Product;
import com.codegym.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;
import java.util.Locale;
import java.util.Optional;

@Controller
//@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/user")
    public ModelAndView showAllProductUser(@RequestParam("s") Optional<String> s, @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "7") int size){
        String s1 = messageSource.getMessage("homepage.add", null, LocaleContextHolder.getLocale());
        System.out.println(s);
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products;
        if (s.isPresent()){
            products = productService.findAllByName(s.get(),pageable) ;
        } else {
            products = productService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("user/homepage");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView showAllProductAdmin(@RequestParam("s") Optional<String> s, @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "7") int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products;
        if (s.isPresent()){
            products = productService.findAllByName(s.get(),pageable) ;
        } else {
            products = productService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("admin/homepage");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("admin/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute("product") Product product, Locale local){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("admin/create");
        String message =  messageSource.getMessage("create.success", null, local);
        modelAndView.addObject("message", message );
        return modelAndView;
    }

    @GetMapping("/edit/{productId}")
    public ModelAndView showEditForm(@PathVariable("productId") Long id){
        Product product = productService.findById(id);
        if (product != null){
            ModelAndView modelAndView = new ModelAndView("admin/edit");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("admin/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView editProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("admin/edit");
        modelAndView.addObject("message", "Edit product's infomation succesfully!");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/delete/{productId}")
    public ModelAndView showDeleteForm(@PathVariable("productId") Long id){
        Product product = productService.findById(id);
        if (product != null){
            ModelAndView modelAndView = new ModelAndView("admin/delete");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("admin/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.remove(product.getProductId());
        return "redirect:/";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "error.404";
    }
}
