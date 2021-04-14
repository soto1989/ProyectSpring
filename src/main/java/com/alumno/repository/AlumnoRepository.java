package com.alumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alumno.model.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

}
