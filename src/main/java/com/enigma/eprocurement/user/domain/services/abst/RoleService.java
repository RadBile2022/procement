package com.enigma.eprocurement.user.domain.services.abst;

import com.enigma.eprocurement.user.entity.Role;
import com.enigma.eprocurement.user.entity.constant.ERole;

public interface RoleService {

    Role getOrSave(ERole role);

}

