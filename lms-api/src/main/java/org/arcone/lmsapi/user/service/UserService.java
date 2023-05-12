package org.arcone.lmsapi.user.service;

import org.arcone.lmsapi.auth.model.dto.SignupDTO;

public interface UserService {

    void signup(SignupDTO request);

    boolean checkUserName(String email);
}
