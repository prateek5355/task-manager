package com.infosys.taskmanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"controller","service","model", "repository"})
@OpenAPIDefinition(info = @Info(title = "Task Manager Application",
description = "Task management system where team members can create, track, and manage their daily tasks."))
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}
}
