package com.infosys.taskmanager.repository;


import com.infosys.taskmanager.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

