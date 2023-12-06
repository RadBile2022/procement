package com.example.procementapi.domain.services;

import com.example.procementapi.domain.repositories.AdminRepository;
import com.example.procementapi.domain.services.abstrct.AdminService;
import com.example.procementapi.entities.Admin;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    @Transactional(rollbackOn = Exception.class)
    @Override
    public Admin create(Admin admin) {
        try{
            return adminRepository.save(admin);
        }catch (DataIntegrityViolationException exception){
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "username already exist");
        }
     }
}
