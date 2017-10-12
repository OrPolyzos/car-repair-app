package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.User;

public interface AccountService {

    void login(String username, String password);// throws AuthenticationException;

    void logout(String username);// throws Exception;

    void register(User user);// throws Exception;
}
