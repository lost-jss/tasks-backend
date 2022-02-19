package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {
    @Mock
    public TaskRepo taskRepo;
    @InjectMocks
    public TaskController taskController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveDispararValidationExceptionQuandoTaskForNull() {
        Task task = new Task();
        task.setDueDate(LocalDate.now());

        try {
            taskController.save(task);
            Assert.fail("Error expected exception");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void deveDispararValidationExceptionQuandoDueDateForNull() {
        Task task = new Task();
        task.setTask("Task");

        try {
            taskController.save(task);
            Assert.fail("Error expected exception");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void deveDispararValidationExceptionQuandoDueDateEstiverNoPassado() {
        Task task = new Task();
        task.setTask("Task");
        task.setDueDate(LocalDate.now().minusDays(1));

        try {
            taskController.save(task);
            Assert.fail("Error expected exception");
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void deveSalvaQuandoTaskEstiverCompleta() throws ValidationException {
        Task task = new Task();
        task.setTask("Task");
        task.setDueDate(LocalDate.now());

        taskController.save(task);

        Mockito.verify(taskRepo).save(task);
    }
}