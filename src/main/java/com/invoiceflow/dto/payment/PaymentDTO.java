package com.invoiceflow.dto.payment;

import com.invoiceflow.model.utils.Method;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private UUID id;
    private BigDecimal amount;
    private Method method;
    private LocalDate paymentDate;
}