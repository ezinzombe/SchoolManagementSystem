package zw.co.soskode.SchoolManagementSystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import zw.co.soskode.SchoolManagementSystem.dto.UserRegistrationDto;
import zw.co.soskode.SchoolManagementSystem.model.User;


public interface UserService extends UserDetailsService, IService<User> {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
