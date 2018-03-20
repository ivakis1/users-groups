package com.softuni.residentEvil.repositories;

import com.softuni.residentEvil.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository  extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g WHERE g.administrator_id = :id")
    List<Group> findByAdministrator_idEquals(@Param("id") Long id);
}
