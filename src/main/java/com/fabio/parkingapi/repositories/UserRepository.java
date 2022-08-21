package com.fabio.parkingapi.repositories;

import com.fabio.parkingapi.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
}
