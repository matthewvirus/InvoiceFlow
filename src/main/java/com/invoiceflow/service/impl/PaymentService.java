package com.invoiceflow.service.impl;

import com.invoiceflow.dto.payment.PaymentCreateDTO;
import com.invoiceflow.dto.payment.PaymentDTO;
import com.invoiceflow.mapper.PaymentMapper;
import com.invoiceflow.model.Payment;
import com.invoiceflow.repository.PaymentRepository;
import com.invoiceflow.service.InvoiceFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService implements InvoiceFlowService<PaymentDTO, PaymentCreateDTO, UUID> {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDTO create(PaymentCreateDTO paymentCreateDTO) {
        Payment payment = PaymentMapper.toEntity(paymentCreateDTO);
        log.info("PaymentController created: {}", payment.getId());
        return PaymentMapper.toDTO(paymentRepository.save(payment));
    }

    @Override
    public PaymentDTO getById(UUID id) {
        log.info("Fetching payment by id: {}", id);
        return paymentRepository.findById(id).map(PaymentMapper::toDTO)
                .orElseThrow(() -> {
                    log.error("PaymentController with id {} not found!", id);
                    return new RuntimeException("PaymentController not found!");
                });
    }

    @Override
    public List<PaymentDTO> getAll() {
        log.info("Fetching all payments.");
        return PaymentMapper.toDTOList(paymentRepository.findAll());
    }

    @Override
    public void deleteById(UUID id) {
        paymentRepository.deleteById(id);
        log.info("PaymentController with id {} deleted.", id);
    }
}