//package com.br.AgileFlow.backend.core.util.mapper;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.br.AgileFlow.backend.api.controller.dto.request.ProjectRequest;
//import com.br.AgileFlow.backend.api.controller.dto.response.ProjectResponse;
//import com.br.AgileFlow.backend.domain.model.Project;
//
//@Component
//public class ProjectMapper {
//
//	@Autowired
//	private ModelMapper modelMapper;
//
//	public Project toEntity(ProjectRequest projectRequest) {
//		return modelMapper.map(projectRequest, Project.class);
//	}
//
//	public ProjectResponse toResponse(Project project) {
//		return modelMapper.map(project, ProjectResponse.class);
//	}
//
//	public List<ProjectResponse> toResponseList(List<Project> projects){
//		return projects.stream()
//				.map(this::toResponse)
//				.collect(Collectors.toList());
//	}
//
//}
