package com.elifintizam.projectManagement.business.abstracts;

import com.elifintizam.projectManagement.business.dtos.requests.auth.AuthenticationRequest;
import com.elifintizam.projectManagement.business.dtos.requests.auth.RegistrationRequest;
import com.elifintizam.projectManagement.business.dtos.responses.auth.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegistrationRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
