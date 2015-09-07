package com.sirustasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sirustasks.model.Project;

@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query("select p from Project p where p.title = :title")
	Project findByName(@Param("title") String title);
	
}
