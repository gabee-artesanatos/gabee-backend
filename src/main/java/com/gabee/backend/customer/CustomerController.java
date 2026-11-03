package com.gabee.backend.customer;

import com.gabee.backend.customer.dto.CustomerDTO;
import com.gabee.backend.customer.dto.CustomerResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll(@RequestParam(defaultValue = "true") boolean isActive) {
        List<CustomerResponseDTO> customers = service.findAll(isActive);
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable Long id) {
        CustomerResponseDTO customer = service.findById(id);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> insert(@Valid @RequestBody CustomerDTO customer) {
        CustomerResponseDTO response = service.insert(customer);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping(value = "/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        service.restore(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CustomerDTO customer) {
        CustomerResponseDTO updatedCustomer = service.update(id, customer);
        return ResponseEntity.ok().body(updatedCustomer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
