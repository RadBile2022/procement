package com.enigma.eprocurement.user.domain.services.impl;

import com.enigma.eprocurement.user.domain.services.abst.RoleService;
import com.enigma.eprocurement.user.domain.repository.RoleRepository;
import com.enigma.eprocurement.user.entity.Role;
import com.enigma.eprocurement.user.entity.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findByRole(role).orElseGet(() -> roleRepository.save(Role.builder().role(role).build()));
    }
}
