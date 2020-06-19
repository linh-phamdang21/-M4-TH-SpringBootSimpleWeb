package com.codegym.demo.service.appuser;

import com.codegym.demo.model.AppUser;

public interface AppUserService {
    AppUser getUserByUserName(String username);
}
