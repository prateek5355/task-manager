package com.infosys.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.infosys.taskmanager.dto.CommentDto;
import com.infosys.taskmanager.dto.TaskDeleteResponse;
import com.infosys.taskmanager.dto.TaskDto;
import com.infosys.taskmanager.entity.Comment;
import com.infosys.taskmanager.entity.Task;
import com.infosys.taskmanager.enums.Priority;
import com.infosys.taskmanager.enums.Status;
import com.infosys.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskServiceImpl taskService;

	private Task task;
	private TaskDto taskDto;
	private CommentDto commentDto;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		task = new Task();
		task.setId(1L);
		task.setTitle("Test Task");
		task.setDescription("Test Description");
		task.setPriority(Priority.HIGH);
		task.setAssignee("user");
		task.setDueDate(new Date());
		task.setStatus(Status.TODO);
		task.setCreator("current-user");

		taskDto = new TaskDto();
		taskDto.setTitle("Test Task");
		taskDto.setDescription("Test Description");
		taskDto.setPriority(Priority.HIGH);
		taskDto.setAssignee("user");
		taskDto.setDueDate(new Date());

		commentDto = new CommentDto();
		commentDto.setText("Test Comment");
		commentDto.setAuthor("user");
	}

	@Test
	public void testCreateTask() {
		when(taskRepository.save(any(Task.class))).thenReturn(task);

		Task createdTask = taskService.createTask(taskDto);

		assertNotNull(createdTask);
		assertEquals("Test Task", createdTask.getTitle());
		assertEquals("Test Description", createdTask.getDescription());
		assertEquals(Priority.HIGH, createdTask.getPriority());
		assertEquals("user", createdTask.getAssignee());
		assertEquals(Status.TODO, createdTask.getStatus());
		verify(taskRepository, times(1)).save(any(Task.class));
	}

	@Test
	public void testListTasks_WithStatusAndPriority() {
		when(taskRepository.findByStatusAndPriority(Status.TODO, Priority.HIGH)).thenReturn(Collections.singletonList(task));

		List<Task> tasks = taskService.listTasks(Status.TODO, Priority.HIGH);

		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		assertEquals(task, tasks.get(0));
		verify(taskRepository, times(1)).findByStatusAndPriority(Status.TODO, Priority.HIGH);
	}

	@Test
	public void testListTasks_WithStatusOnly() {
		when(taskRepository.findByStatus(Status.TODO)).thenReturn(Collections.singletonList(task));

		List<Task> tasks = taskService.listTasks(Status.TODO, null);

		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		assertEquals(task, tasks.get(0));
		verify(taskRepository, times(1)).findByStatus(Status.TODO);
	}

	@Test
	public void testListTasks_WithPriorityOnly() {
		when(taskRepository.findByPriority(Priority.HIGH)).thenReturn(Collections.singletonList(task));

		List<Task> tasks = taskService.listTasks(null, Priority.HIGH);

		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		assertEquals(task, tasks.get(0));
		verify(taskRepository, times(1)).findByPriority(Priority.HIGH);
	}

	@Test
	public void testListTasks_WithoutFilters() {
		when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));

		List<Task> tasks = taskService.listTasks(null, null);

		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		assertEquals(task, tasks.get(0));
		verify(taskRepository, times(1)).findAll();
	}

	@Test
	public void testGetTask() {
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

		Task foundTask = taskService.getTask(1L);

		assertNotNull(foundTask);
		assertEquals(task, foundTask);
		verify(taskRepository, times(1)).findById(1L);
	}

	@Test
	public void testUpdateTask() {
		Task updatedTask = new Task();
		updatedTask.setTitle("Updated Title");
		updatedTask.setDescription("Updated Description");
		updatedTask.setPriority(Priority.MEDIUM);
		updatedTask.setAssignee("updated-user");
		updatedTask.setDueDate(new Date());

		when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
		when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

		Task result = taskService.updateTask(1L, taskDto);

		assertNotNull(result);
		assertEquals("Updated Title", result.getTitle());
		assertEquals("Updated Description", result.getDescription());
		assertEquals(Priority.MEDIUM, result.getPriority());
		assertEquals("updated-user", result.getAssignee());
		verify(taskRepository, times(1)).findById(1L);
		verify(taskRepository, times(1)).save(any(Task.class));
	}

	@Test
	public void testDeleteTask() {
		doNothing().when(taskRepository).deleteById(1L);

		TaskDeleteResponse response = taskService.deleteTask(1L);

		assertNotNull(response);
		assertEquals("Task with id:1 is deleted successfully", response.getMessage());
		verify(taskRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testAddComment() {
		Comment comment = new Comment();
		comment.setText(commentDto.getText());
		comment.setAuthor(commentDto.getAuthor());
		comment.setCreatedAt(new Date());
		task.setComments(Collections.singletonList(comment));
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
		when(taskRepository.save(any(Task.class))).thenReturn(task);
		Task updatedTask = taskService.addComment(1L, commentDto);

		assertNotNull(updatedTask);
		assertEquals(1, updatedTask.getComments().size());
		assertEquals(commentDto.getText(), updatedTask.getComments().get(0).getText());
		verify(taskRepository, times(1)).findById(1L);
		verify(taskRepository, times(1)).save(any(Task.class));
	}

	@Test
	public void testSearchTasks() {
		when(taskRepository.findByTitleAndDescription("Test")).thenReturn(Collections.singletonList(task));

		List<Task> tasks = taskService.searchTasks("Test");

		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		assertEquals(task, tasks.get(0));
		verify(taskRepository, times(1)).findByTitleAndDescription("Test");
	}
}
