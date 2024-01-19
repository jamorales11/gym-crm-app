package com.epam.gymcrm.infrastructure.repository;


import com.epam.gymcrm.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    List<UserEntity> findUsersByFirstNameAndLastName(String firstName, String lastName);

    @Modifying
    @Query("update UserEntity u set u.password = :password where u.username = :username")
    int updatePassword(@Param("username") String username, @Param("password") String password);


    @Modifying
    @Query("update UserEntity u set u.isActive = true where u.username = :username")
    int activateUser(@Param("username") String username);

    @Modifying
    @Query("update UserEntity u set u.isActive = false where u.username = :username")
    int deactivateUser(@Param("username") String username);

}
