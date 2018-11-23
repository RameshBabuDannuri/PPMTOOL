package com.ramesh.service;

import com.ramesh.domain.Project;
import com.ramesh.exception.ProjectIdException;
import com.ramesh.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toLowerCase()+"' already existed");
        }

    }



    public List<Project> getAll() {
        return projectRepository.findAll();
    }


    public Project getByProjectId(String projectId) {
        return projectRepository.findByProjectIdentifier(projectId);
    }

    public void deleteProject(String  projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIdException("Cannont project with id '"+ projectId+"' this project does not exist");
        }
        projectRepository.delete(project);
    }
}
