package com.enigma.eprocurement.user.domain.services.impl;

import com.enigma.eprocurement.user.domain.services.abst.AdminService;
import com.enigma.eprocurement.user.domain.services.abst.AuthService;
import com.enigma.eprocurement.user.domain.services.abst.RoleService;
import com.enigma.eprocurement.user.domain.services.abst.VendorService;
import com.enigma.eprocurement.user.domain.model.response.RegisterResponse;
import com.enigma.eprocurement.user.entity.*;
import com.enigma.eprocurement.user.entity.constant.ERole;
import com.enigma.eprocurement.user.domain.model.request.AuthRequest;
import com.enigma.eprocurement.user.domain.model.response.LoginResponse;
import com.enigma.eprocurement.user.domain.repository.UserCredentialRepository;
import com.enigma.eprocurement.user.utils.security.BCryptUtils;
import com.enigma.eprocurement.user.utils.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptUtils bCryptUtils;
    private final RoleService roleService;
    private final AdminService adminService;
    private final VendorService vendorService;
    private final JwtUtils jwtUtils;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_ADMIN);
            UserCredential credential = UserCredential.builder()
                    .email(request.getEmail())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .roles(List.of(role))
                    .build();
            userCredentialRepository.saveAndFlush(credential);

            String name = request.getEmail().split("@")[0];

            Admin admin = Admin.builder()
                    .name(name)
                    .email(request.getEmail())
                    .userCredential(credential)
                    .build();
            adminService.create(admin);
            return RegisterResponse.builder().email(credential.getEmail()).build();
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerVendor(AuthRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Role role = roleService.getOrSave(ERole.ROLE_VENDOR);
            UserCredential credential = UserCredential.builder()
                    .email(request.getEmail())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .roles(List.of(role))
                    .build();
            userCredentialRepository.saveAndFlush(credential);

            String name = request.getEmail().split("@")[0];

            Vendor vendor = Vendor.builder()
                    .name(name)
                    .email(request.getEmail())
                    .createdBy(authentication.getName())
                    .userCredential(credential)
                    .build();
            vendorService.create(vendor);
            return RegisterResponse.builder().email(credential.getEmail()).build();
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String token = jwtUtils.generateToken(userDetails.getEmail());

        return LoginResponse.builder()
                .email(userDetails.getEmail())
                .roles(roles)
                .token(token)
                .build();
    }
}