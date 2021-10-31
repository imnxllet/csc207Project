package com.savvypro.csc207Project.repository;

import com.savvypro.csc207Project.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {
}