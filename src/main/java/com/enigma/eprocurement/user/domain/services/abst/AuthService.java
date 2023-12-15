package com.enigma.eprocurement.user.domain.services.abst;

import com.enigma.eprocurement.user.domain.model.response.RegisterResponse;
import com.enigma.eprocurement.user.domain.model.request.AuthRequest;
import com.enigma.eprocurement.user.domain.model.response.LoginResponse;

public interface AuthService {

    RegisterResponse registerAdmin(AuthRequest request);
    RegisterResponse registerVendor(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
