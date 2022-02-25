package com.uniovi.sdi2122212spring.repositories;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.sdi2122212spring.entities.Mark;
import org.springframework.data.repository.CrudRepository;
public interface MarksRepository extends CrudRepository<Mark, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Mark SET resend = ?1 WHERE id = ?2")
    void updateResend(Boolean resend, Long id);
}
