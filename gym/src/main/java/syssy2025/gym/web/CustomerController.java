package syssy2025.gym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import syssy2025.gym.domain.Customer;
import syssy2025.gym.domain.CustomerRepository;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository cuRepository;

    @GetMapping
    public String customerList(Model model) {
        model.addAttribute("customers", cuRepository.findAll());
        return "customers";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "addcustomer";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public String saveCustomer(Customer customer) {
        cuRepository.save(customer); 
        return "redirect:/customers";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long customer_id, Model model) {
        cuRepository.deleteById(customer_id);
        return "redirect:/customers";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") Long customer_id, Model model) {
        model.addAttribute("customer", cuRepository.findById(customer_id));
        return "editcustomer";
    }
}
