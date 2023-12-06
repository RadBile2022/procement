package com.example.procementapi.domain.services.abstrct;

import com.example.procementapi.domain.models.requests.AuthRequest;
import com.example.procementapi.domain.models.responses.LoginResponse;
import com.example.procementapi.domain.models.responses.RegisterResponse;

public interface AuthService {
    RegisterResponse registerAdmin(AuthRequest request);
    RegisterResponse registerVendor(AuthRequest request);
    LoginResponse login (AuthRequest request);
}
