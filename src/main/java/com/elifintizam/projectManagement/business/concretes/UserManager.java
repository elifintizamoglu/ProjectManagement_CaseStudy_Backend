package com.elifintizam.projectManagement.business.concretes;

import com.elifintizam.projectManagement.business.abstracts.UserService;
import com.elifintizam.projectManagement.business.dtos.requests.user.CreateUserRequest;
import com.elifintizam.projectManagement.business.dtos.requests.user.UpdateUserRequest;
import com.elifintizam.projectManagement.business.dtos.responses.user.*;
import com.elifintizam.projectManagement.core.utilities.exceptions.types.BusinessException;
import com.elifintizam.projectManagement.core.utilities.mapping.ModelMapperService;
import com.elifintizam.projectManagement.dataAccess.abstracts.UserRepository;
import com.elifintizam.projectManagement.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserManager implements UserService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;

    @Override
    public CreateUserResponse addUser(CreateUserRequest createUserRequest) {

        User user = modelMapperService.forRequest().map(createUserRequest, User.class);
        user.setCreatedDate(LocalDateTime.now());
        userRepository.save(user);

        CreateUserResponse response = modelMapperService.forResponse().map(user, CreateUserResponse.class);
        return response;
    }

    @Override
    public List<GetAllUsersResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        List<GetAllUsersResponse> response = users.stream()
                .map(user -> modelMapperService.forResponse()
                        .map(user, GetAllUsersResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public void deleteUserById(int id) {

        userRepository.deleteById(id);
    }

    @Override
    public UpdateUserResponse updateUserById(int id, UpdateUserRequest updateUserRequest) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("UserNotFound"));

        User updatedUser = modelMapperService.forRequest().map(updateUserRequest, User.class);

        user.setUpdatedDate(LocalDateTime.now());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        userRepository.save(user);

        UpdateUserResponse response = modelMapperService.forResponse().map(user, UpdateUserResponse.class);
        return response;
    }

    @Override
    public GetUserByIdResponse getUserById(int id) {

        User user = userRepository.findById(id).orElseThrow(() -> new BusinessException("UserNotFound"));

        GetUserByIdResponse response = modelMapperService.forResponse().map(user, GetUserByIdResponse.class);
        return response;
    }

    @Override
    public GetUserByEmailResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("UserNotFound"));
        GetUserByEmailResponse response = modelMapperService.forResponse().map(user, GetUserByEmailResponse.class);
        return response;
    }
}
