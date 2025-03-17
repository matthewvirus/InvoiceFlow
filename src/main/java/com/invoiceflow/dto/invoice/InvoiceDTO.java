package com.invoiceflow.dto.invoice;

import com.invoiceflow.dto.payment.PaymentDTO;
import com.invoiceflow.model.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    private UUID id;
    private BigDecimal amount;
    private Status status;
    private LocalDate dueDate;
    private LocalDate issuedDate;
    private List<PaymentDTO> paymentList;
}