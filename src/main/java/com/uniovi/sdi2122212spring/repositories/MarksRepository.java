package com.uniovi.sdi2122212spring.repositories;

import com.uniovi.sdi2122212spring.entities.Mark;
import org.springframework.data.repository.CrudRepository;
public interface MarksRepository extends CrudRepository<Mark, Long> {
}
