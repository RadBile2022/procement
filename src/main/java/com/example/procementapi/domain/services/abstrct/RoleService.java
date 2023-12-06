package com.example.procementapi.domain.services.abstrct;

import com.example.procementapi.entities.Role;
import com.example.procementapi.entities.constant.ERole;

public interface RoleService {
    Role getOrSave (ERole eRole);
}
