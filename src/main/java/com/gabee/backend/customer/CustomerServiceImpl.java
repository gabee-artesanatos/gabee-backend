package com.gabee.backend.customer;

import com.gabee.backend.customer.dto.CustomerDTO;
import com.gabee.backend.customer.dto.CustomerResponseDTO;
import com.gabee.backend.exceptions.ResourceConflictException;
import com.gabee.backend.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public List<CustomerResponseDTO> findAll(boolean isActive) {
        return repository.findByActive(isActive)
                .stream()
                .map(customer -> CustomerMapper.toResponseDTO(customer))
                .toList();
    }

    public CustomerResponseDTO findById(Long id) {
        Customer customer = findCustomerById(id);

        return CustomerMapper.toResponseDTO(customer);
    }

    public Customer findCustomerById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public CustomerResponseDTO insert(CustomerDTO dto) {
        String normalizedCpf = normalizeCpf(dto.cpf());

        if (repository.existsByCpf(normalizedCpf)) {
            throw new ResourceConflictException("CPF já cadastrado!");
        }

        if (repository.existsByEmail(dto.email())) {
            throw new ResourceConflictException("Email já cadastrado!");
        }

        String normalizedCep = normalizeCep(dto.address().getCep());

        Customer customer = CustomerMapper.toEntity(dto);

        customer.setCpf(normalizedCpf);
        customer.getAddress().setCep(normalizedCep);
        customer.setEmail(normalizeEmail(dto.email()));

        Customer savedCustomer = repository.save(customer);

        return CustomerMapper.toResponseDTO(savedCustomer);
    }

    public CustomerResponseDTO update(Long id, CustomerDTO dto) {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        String normalizedCpf = normalizeCpf(dto.cpf());

        if (repository.existsByCpfAndIdNot(normalizedCpf, id)) {
            throw new ResourceConflictException("CPF já está sendo utilizado por outro cadastro!");
        }

        if (repository.existsByEmailAndIdNot(dto.email(), id)) {
            throw new ResourceConflictException("Email já está sendo utilizado por outro cadastro!");
        }

        String normalizedCep = normalizeCep(dto.address().getCep());

        CustomerMapper.updateCustomer(dto, customer);

        customer.setCpf(normalizedCpf);
        customer.getAddress().setCep(normalizedCep);
        customer.setEmail(normalizeEmail(dto.email()));

        Customer customerUpdated = repository.save(customer);

        return CustomerMapper.toResponseDTO(customerUpdated);
    }

    public void delete(Long id) {
        Customer deletedCustomer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        deletedCustomer.setActive(false);

        repository.save(deletedCustomer);
    }

    public void restore(Long id) {
        Customer restoredCustomer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        restoredCustomer.setActive(true);

        repository.save(restoredCustomer);
    }

    private String normalizeCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private String normalizeCep(String cep) {
        return cep.replaceAll("\\D", "");
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
