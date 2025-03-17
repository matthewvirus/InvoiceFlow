package com.invoiceflow.mapper;

import com.invoiceflow.dto.invoice.InvoiceCreateDTO;
import com.invoiceflow.dto.invoice.InvoiceDTO;
import com.invoiceflow.dto.payment.PaymentDTO;
import com.invoiceflow.model.Invoice;

import java.util.List;
import java.util.stream.Collectors;

public class InvoiceMapper {

    public static InvoiceDTO toDTO(Invoice invoice) {
        if (invoice == null) return null;
        List<PaymentDTO> paymentDTOs = invoice.getPayments() != null
                ? invoice.getPayments().stream()
                    .map(PaymentMapper::toDTO)
                    .collect(Collectors.toList())
                : null;
        return new InvoiceDTO(
                invoice.getId(),
                invoice.getAmount(),
                invoice.getStatus(),
                invoice.getDueDate(),
                invoice.getIssuedDate(),
                paymentDTOs);
    }

    public static Invoice toEntity(InvoiceCreateDTO dto) {
        return dto != null
                ? Invoice.builder()
                    .amount(dto.getAmount())
                    .dueDate(dto.getDueDate())
                    .issuedDate(dto.getIssuedDate())
                    .build()
                : null;
    }

    public static List<InvoiceDTO> toDTOList(List<Invoice> invoiceList) {
        return invoiceList != null
                ? invoiceList.stream()
                    .map(InvoiceMapper::toDTO)
                    .collect(Collectors.toList())
                : null;
    }
}