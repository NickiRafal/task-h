package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendInformationEmail_TaskCountLessThanTwo_ShouldSendSingularTaskEmail() {
        // Given
        when(taskRepository.count()).thenReturn(1L);
        when(adminConfig.getAdminMail()).thenReturn("admin@test.com");

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(emailService, times(1)).send(any(Mail.class));
        verify(emailService, times(1)).send(argThat(mail -> mail.getMailTo().equals("admin@test.com")
                && mail.getSubject().equals("Tasks: Once a day email")
                && mail.getMessage().contains("Currently in database you got: 1 task")));
    }

    @Test
    void sendInformationEmail_TaskCountMoreThanOne_ShouldSendPluralTasksEmail() {
        // Given
        when(taskRepository.count()).thenReturn(5L);
        when(adminConfig.getAdminMail()).thenReturn("admin@test.com");

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(emailService, times(1)).send(any(Mail.class));
        verify(emailService, times(1)).send(argThat(mail -> mail.getMailTo().equals("admin@test.com")
                && mail.getSubject().equals("Tasks: Once a day email")
                && mail.getMessage().contains("Currently in database you got: 5 tasks")));
    }
}
