package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Customer;
import com.geekbrains.spring.mvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAll(Model model){
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm(){
        return "customer_add_form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCustomer(
            @ModelAttribute Customer newCustomer
    ){
        customerService.saveOrUpdate(newCustomer);
        return "redirect:/customers/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(
            @PathVariable Long id, Model model
    ){
        model.addAttribute("customer", customerService.findById(id));
        return "customer_edit_form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String modifyCustomer(
            @ModelAttribute Customer modifyCustomer
    ){
        customerService.saveOrUpdate(modifyCustomer);
        return "redirect:/customers/";
    }
}
