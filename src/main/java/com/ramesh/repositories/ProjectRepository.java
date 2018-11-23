package com.ramesh.repositories;

import com.ramesh.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository  extends JpaRepository<Project , Long> {

    Project findByProjectIdentifier(String projectId);

}
