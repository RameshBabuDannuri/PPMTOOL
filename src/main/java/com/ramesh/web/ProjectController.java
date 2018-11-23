package com.ramesh.web;

import com.ramesh.domain.Project;
import com.ramesh.exception.ProjectIdException;
import com.ramesh.service.MapValidationErrorService;
import com.ramesh.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null){
            return errorMap;
        }
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project1, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    List<Project> getAll(){
        return projectService.getAll();
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId){

        Project project = projectService.getByProjectId(projectId.toUpperCase());

        if (project == null){
            throw new ProjectIdException("project id '"+projectId+"' does not exist");
        }
        return new ResponseEntity<>(project , HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectId){
        projectService.deleteProject(projectId);
        return new ResponseEntity<String>("project with id '"+projectId+"' was deleted",HttpStatus.OK);
     }
}
