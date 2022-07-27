package com.example.booksapplication.service;

import com.example.booksapplication.dao.entity.RoleEntity;
import com.example.booksapplication.dao.entity.UserEntity;
import com.example.booksapplication.dao.repository.RoleRepository;
import com.example.booksapplication.dao.repository.UserRepository;
import com.example.booksapplication.mapper.UserMapper;
import com.example.booksapplication.model.dto.UserRegisterDto;
import com.example.booksapplication.model.exception.UniquenessViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.example.booksapplication.model.constants.ExceptionConstants.VALIDATION_EXCEPTION_CODE;
import static com.example.booksapplication.model.constants.ExceptionConstants.VALIDATION_MESSAGE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByUsername(username).orElseThrow(RuntimeException::new);
        if (user == null) {
            log.error("UserEntity not found in the database");
            throw new UsernameNotFoundException("UserEntity not found in the database");
        } else {
            log.info("UserEntity found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    public void registerUser(UserRegisterDto user) {
        log.info("ActionLog.registerUser.start");
        Optional<UserEntity> checkEmailAndUsername = userRepo.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (checkEmailAndUsername.isEmpty()) {
            RoleEntity role = roleRepo.findByName("USER");
            UserEntity entity = UserMapper.mapRegisterDtoToEntity(user);

            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            entity.setRoles(List.of(role));

            userRepo.save(entity);
            log.info("ActionLog.registerUser.success");
        } else {
            log.error("ActionLog.registerUser.error");
            throw new UniquenessViolationException(String.format(VALIDATION_MESSAGE, "email", "or", "username"),
                    VALIDATION_EXCEPTION_CODE);
        }
    }


    public void hey() {
        System.out.println("HEY EVERYONE");
    }

    public UserEntity findByUsername(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username).orElseThrow();
    }

}
