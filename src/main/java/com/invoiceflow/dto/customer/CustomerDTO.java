package com.invoiceflow.dto.customer;

import com.invoiceflow.dto.invoice.InvoiceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private List<InvoiceDTO> invoiceList;
}