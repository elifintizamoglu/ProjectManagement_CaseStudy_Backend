package com.elifintizam.projectManagement.business.dtos.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
