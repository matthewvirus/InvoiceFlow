package com.invoiceflow.mapper;

import com.invoiceflow.dto.customer.CustomerCreateDTO;
import com.invoiceflow.dto.customer.CustomerDTO;
import com.invoiceflow.dto.invoice.InvoiceDTO;
import com.invoiceflow.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;
        List<InvoiceDTO> invoiceDTOs = customer.getInvoices() != null
                ? customer.getInvoices().stream()
                    .map(InvoiceMapper::toDTO)
                    .collect(Collectors.toList())
                : null;
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                invoiceDTOs
        );
    }

    public static Customer toEntity(CustomerCreateDTO dto) {
        return dto != null
                ? Customer.builder()
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .build()
                : null;
    }

    public static List<CustomerDTO> toDTOList(List<Customer> customerList) {
        return customerList != null
                ? customerList.stream()
                    .map(CustomerMapper::toDTO)
                    .collect(Collectors.toList())
                : null;
    }
}