package com.br.AgileFlow.backend.util.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.AgileFlow.backend.dto.request.TaskRequest;
import com.br.AgileFlow.backend.dto.response.TaskResponse;
import com.br.AgileFlow.backend.model.Task;

@Component
public class TaskMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Task toEntity(TaskRequest taskRequest) {
		return modelMapper.map(taskRequest, Task.class);
	}
	
	public TaskResponse toResponse(Task task) {
		return modelMapper.map(task, TaskResponse.class);
	}
	
	public List<TaskResponse> toResponseList(List<Task> tasks) {
        return tasks.stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());
    }

}
