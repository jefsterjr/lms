package org.arcone.lmsapi.user.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.arcone.lmsapi.auth.model.dto.SignupDTO;
import org.arcone.lmsapi.student.service.StudentService;
import org.arcone.lmsapi.user.model.entity.User;
import org.arcone.lmsapi.user.repository.UserRepository;
import org.arcone.lmsapi.user.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final StudentService studentService;
    private final BCryptPasswordEncoder encoder;


    @Override
    @Transactional
    public void signup(SignupDTO request) {
        User user = new User(null, request.email(),
                encoder.encode(request.password()),
                "STUDENT");
        repository.save(user);
        studentService.create(request, user);
    }

    @Override
    public boolean checkUserName(String email) {
        return repository.existsByUsername(email);
    }
}
