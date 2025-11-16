package syssy2025.gym.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import syssy2025.gym.domain.Customer;
import syssy2025.gym.domain.CustomerRepository;

@RestController
public class CustomerRestController {

    private final CustomerRepository cuRepository;

    public CustomerRestController(CustomerRepository cuRepository) {
        this.cuRepository = cuRepository;
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return (List<Customer>) cuRepository.findAll();
    }
    @GetMapping("/{id}")
	public @ResponseBody Optional<Customer> findCustomerRest(@PathVariable("id") Long customer_id) {	
		return cuRepository.findById(customer_id);
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Customer> createCustomerRest(@RequestBody Customer customer) {
        Customer saved = cuRepository.save(customer);
        return ResponseEntity.ok(saved);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
	public @ResponseBody List<Customer> deleteCustomer(@PathVariable("id") Long customer_id) {	
    	cuRepository.deleteById(customer_id);
		return (List<Customer>) cuRepository.findAll();
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
	public  ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long customer_id, @RequestBody Customer customer) {
		customer.setCustomer_id(customer_id);
		Customer uCustomer = cuRepository.save(customer);
		return ResponseEntity.ok(uCustomer);
	}
}
