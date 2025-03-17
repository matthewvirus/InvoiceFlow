package com.invoiceflow.service.impl;

import com.invoiceflow.dto.customer.CustomerCreateDTO;
import com.invoiceflow.dto.customer.CustomerDTO;
import com.invoiceflow.mapper.CustomerMapper;
import com.invoiceflow.model.Customer;
import com.invoiceflow.repository.CustomerRepository;
import com.invoiceflow.service.InvoiceFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements InvoiceFlowService<CustomerDTO, CustomerCreateDTO, UUID> {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO create(CustomerCreateDTO customerCreateDTO) {
        Customer customer = CustomerMapper.toEntity(customerCreateDTO);
        log.info("Customer created: {}", customer.getEmail());
        return CustomerMapper.toDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO getById(UUID id) {
        log.info("Fetching customer by id: {}", id);
        return customerRepository.findById(id).map(CustomerMapper::toDTO)
                .orElseThrow(() -> {
                    log.error("Customer with id {} not found.", id);
                    return new RuntimeException("Customer not found!");
                });
    }

    @Override
    public List<CustomerDTO> getAll() {
        log.info("Fetching all customers.");
        return CustomerMapper.toDTOList(customerRepository.findAll());
    }

    @Override
    public void deleteById(UUID id) {
        customerRepository.deleteById(id);
        log.info("Customer with id {} deleted.", id);
    }
}