package com.nova.talentnova.repository;

import com.nova.talentnova.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByCompanyId(Long id);

    boolean existsByNameAndCompanyId(String name, Long id);

}