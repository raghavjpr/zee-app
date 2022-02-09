package com.zee.zee5app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zee.zee5app.dto.Role;
import com.zee.zee5app.repository.RoleRepository;
import com.zee.zee5app.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addRole(Role role) {
		Role role2 = roleRepository.save(role);
		if (role2 != null) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public void deleteRole(int roleId) {
	}

}
