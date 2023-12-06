package com.example.procementapi.domain.services;

import com.example.procementapi.domain.models.requests.AuthRequest;
import com.example.procementapi.domain.models.responses.LoginResponse;
import com.example.procementapi.domain.models.responses.RegisterResponse;
import com.example.procementapi.domain.repositories.UserCredentialRepository;
import com.example.procementapi.domain.services.abstrct.AdminService;
import com.example.procementapi.domain.services.abstrct.AuthService;
import com.example.procementapi.domain.services.abstrct.RoleService;
import com.example.procementapi.domain.services.abstrct.VendorService;
import com.example.procementapi.entities.Admin;
import com.example.procementapi.entities.Role;
import com.example.procementapi.entities.UserCredential;
import com.example.procementapi.entities.Vendor;
import com.example.procementapi.entities.constant.ERole;
import com.example.procementapi.utils.security.BCryptUtils;
import com.example.procementapi.utils.security.JwtUtils;
import jakarta.transaction.Transactional;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    final private RoleService roleService;
    final private UserCredentialRepository userCredentialRepository;
    final private AdminService adminService;
    final private VendorService vendorService;
    final private BCryptUtils bCryptUtils;
    final private AuthenticationManager authenticationManager;
    final private JwtUtils jwtUtils;

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
        }catch (DataIntegrityViolationException e){
            throw  new ResponseStatusException(HttpStatus.CONFLICT,"user already exist");
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
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
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
