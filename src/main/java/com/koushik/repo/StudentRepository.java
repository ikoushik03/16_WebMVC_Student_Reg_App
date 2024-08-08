package com.koushik.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koushik.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{

}
