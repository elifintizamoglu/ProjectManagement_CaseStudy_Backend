package com.elifintizam.projectManagement.business.abstracts;

import com.elifintizam.projectManagement.business.dtos.requests.user.CreateUserRequest;
import com.elifintizam.projectManagement.business.dtos.requests.user.UpdateUserRequest;
import com.elifintizam.projectManagement.business.dtos.responses.user.*;

import java.util.List;

public interface UserService {

    CreateUserResponse addUser(CreateUserRequest createUserRequest);

    List<GetAllUsersResponse> getAllUsers();

    void deleteUserById(int id);

    UpdateUserResponse updateUserById(int id, UpdateUserRequest updateUserRequest);

    GetUserByIdResponse getUserById(int id);

    GetUserByEmailResponse getUserByEmail(String email);
}
