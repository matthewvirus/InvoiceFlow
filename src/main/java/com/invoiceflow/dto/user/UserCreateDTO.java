package com.invoiceflow.dto.user;

import com.invoiceflow.model.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    private String email;
    private String password;
    private Role role;
}