package com.mycompany.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);

    //userservice test nemew
    Optional<User> findByEmail(String mail);

    Optional<User> findById(int userId);

}
