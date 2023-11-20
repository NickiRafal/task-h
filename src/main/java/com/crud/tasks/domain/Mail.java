package com.crud.tasks.domain;

import lombok.*;
import org.springframework.mail.SimpleMailMessage;

import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;


}
