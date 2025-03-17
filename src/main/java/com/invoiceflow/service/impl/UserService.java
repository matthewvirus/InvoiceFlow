package com.invoiceflow.service.impl;

import com.invoiceflow.dto.user.UserCreateDTO;
import com.invoiceflow.dto.user.UserDTO;
import com.invoiceflow.mapper.UserMapper;
import com.invoiceflow.model.User;
import com.invoiceflow.repository.UserRepository;
import com.invoiceflow.service.InvoiceFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements InvoiceFlowService<UserDTO, UserCreateDTO, UUID> {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserCreateDTO userCreateDTO) {
        User user = UserMapper.toEntity(userCreateDTO);
        log.info("Creating user: {}", user.getEmail());
        return UserMapper.toDTO(userRepository.save((user)));
    }

    @Override
    public UserDTO getById(UUID id) {
        log.info("Fetching user by id: {}", id);
        return userRepository.findById(id).map(UserMapper::toDTO)
                .orElseThrow(() -> {
                    log.error("User with id {} not found!", id);
                    return new RuntimeException("User not found!");
                });
    }

    @Override
    public List<UserDTO> getAll() {
        log.info("Fetching all users.");
        return UserMapper.toDTOList(userRepository.findAll());
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
        log.info("User with id {} deleted.", id);
    }
}
