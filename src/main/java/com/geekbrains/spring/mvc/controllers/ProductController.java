package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAll(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        return "products";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm(){
        return "product_add_form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(
            @ModelAttribute Product newProduct
    ){
        productService.saveOrUpdate(newProduct);
        return "redirect:/products/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(
            @PathVariable Long id, Model model
    ){
        model.addAttribute("product", productService.findById(id));
        return"product_edit_form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String modifyProduct(
            @ModelAttribute Product modifyProduct
    ){
        productService.saveOrUpdate(modifyProduct);
        return "redirect:/products/";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String showProduct(
            @PathVariable Long id,
            Model model
    ){
        model.addAttribute("product",productService.findById(id));
        return "product";
    }
}
