package com.example.procementapi.domain.services;

import com.example.procementapi.domain.repositories.RoleRepository;
import com.example.procementapi.domain.services.abstrct.RoleService;
import com.example.procementapi.entities.Role;
import com.example.procementapi.entities.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    final private RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole eRole) {
        return roleRepository.findByRole(eRole).orElseGet(() -> roleRepository.save(Role.builder().build()));
    }
}
