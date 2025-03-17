package com.invoiceflow.service.impl;

import com.invoiceflow.dto.invoice.InvoiceCreateDTO;
import com.invoiceflow.dto.invoice.InvoiceDTO;
import com.invoiceflow.mapper.InvoiceMapper;
import com.invoiceflow.model.Invoice;
import com.invoiceflow.repository.InvoiceRepository;
import com.invoiceflow.service.InvoiceFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService implements InvoiceFlowService<InvoiceDTO, InvoiceCreateDTO, UUID> {

    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public InvoiceDTO create(InvoiceCreateDTO invoiceCreateDTO) {
        Invoice invoice = InvoiceMapper.toEntity(invoiceCreateDTO);
        log.info("Invoice created: {}", invoice.getId());
        return InvoiceMapper.toDTO(invoiceRepository.save(invoice));
    }

    @Override
    public InvoiceDTO getById(UUID id) {
        log.info("Fetching invoice by id: {}", id);
        return invoiceRepository.findById(id).map(InvoiceMapper::toDTO)
                .orElseThrow(() -> {
                    log.error("Invoice with id {} not found!", id);
                    return new RuntimeException("Invoice not found!");
                });
    }

    @Override
    public List<InvoiceDTO> getAll() {
        log.info("Fetching all invoices.");
        return InvoiceMapper.toDTOList(invoiceRepository.findAll());
    }

    @Override
    public void deleteById(UUID id) {
        invoiceRepository.deleteById(id);
        log.info("Invoice with id {} deleted.", id);
    }
}