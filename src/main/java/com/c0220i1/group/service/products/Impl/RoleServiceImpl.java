package com.c0220i1.group.service.products.Impl;

import com.c0220i1.group.model.RoLe;
import com.c0220i1.group.repository.account.RoleRepository;
import com.c0220i1.group.service.products.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void save(RoLe role) {
        roleRepository.save(role);
    }

    @Override
    public RoLe findByName(String name) {
       return roleRepository.findByName(name);
    }
}
