package com.uniovi.sdi2122212spring.repositories;

import com.uniovi.sdi2122212spring.entities.*;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long>{
    User findByDni(String dni);
}
