package com.invoiceflow.service;

import java.util.List;

public interface InvoiceFlowService<DTO, CreateDTO, ID> {
    DTO create(CreateDTO createDTO);
    DTO getById(ID id);
    List<DTO> getAll();
    void deleteById(ID id);
}