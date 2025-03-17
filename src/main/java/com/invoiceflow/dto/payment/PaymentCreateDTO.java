package com.invoiceflow.dto.payment;

import com.invoiceflow.model.utils.Method;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreateDTO {
    private BigDecimal amount;
    private Method method;
}