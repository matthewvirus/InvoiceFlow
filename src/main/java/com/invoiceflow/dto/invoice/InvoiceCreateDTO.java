package com.invoiceflow.dto.invoice;

import com.invoiceflow.model.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCreateDTO {
    private BigDecimal amount;
    private Status status;
    private LocalDate dueDate;
    private LocalDate issuedDate;
}