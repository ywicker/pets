package com.pets.profil.usecase;

import com.pets.profil.entity.UserAccount;

import java.util.Optional;

public interface UserAccountRepository {
    Optional<UserAccount> fetchUserAccount();
}
