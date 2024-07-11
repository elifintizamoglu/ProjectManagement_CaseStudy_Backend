package com.elifintizam.projectManagement.business.rules;

import com.elifintizam.projectManagement.business.constants.UserMessages;
import com.elifintizam.projectManagement.core.utilities.exceptions.types.BusinessException;
import com.elifintizam.projectManagement.dataAccess.abstracts.UserRepository;
import com.elifintizam.projectManagement.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserBusinessRules {

    UserRepository userRepository;

    public void emailCanNotBeDuplicated(String email) {

        String trimmedEmail = email.trim();
        Optional<User> user = userRepository.findByEmail(trimmedEmail);

        if (user.isPresent()) {
            throw new BusinessException(UserMessages.EmailAlreadyExists);
        }
    }

    public void isUserExists(int userId) {

        boolean isExists = userRepository.existsById(userId);
        if (!isExists) {
            throw new BusinessException(UserMessages.UserNotFound);
        }
    }

}
