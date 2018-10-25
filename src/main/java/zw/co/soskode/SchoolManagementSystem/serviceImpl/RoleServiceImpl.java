package zw.co.soskode.SchoolManagementSystem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.soskode.SchoolManagementSystem.repository.RoleRepository;
import zw.co.soskode.SchoolManagementSystem.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


}
