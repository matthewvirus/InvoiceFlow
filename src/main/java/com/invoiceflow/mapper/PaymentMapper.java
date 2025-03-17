package com.invoiceflow.mapper;

import com.invoiceflow.dto.payment.PaymentCreateDTO;
import com.invoiceflow.dto.payment.PaymentDTO;
import com.invoiceflow.model.Payment;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMapper {

    public static PaymentDTO toDTO(Payment payment) {
        return payment != null
                ? new PaymentDTO(
                    payment.getId(),
                    payment.getAmount(),
                    payment.getMethod(),
                    payment.getPaymentDate())
                : null;
    }

    public static Payment toEntity(PaymentCreateDTO dto) {
        return dto != null
                ? Payment.builder()
                    .amount(dto.getAmount())
                    .method(dto.getMethod())
                    .build()
                : null;
    }

    public static List<PaymentDTO> toDTOList(List<Payment> paymentList) {
        return paymentList != null
                ? paymentList.stream()
                    .map(PaymentMapper::toDTO)
                    .collect(Collectors.toList())
                : null;
    }
}