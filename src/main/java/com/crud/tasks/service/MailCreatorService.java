package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private Environment environment;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("GOODBYE_MESSAGE",  "Wishing you a successful day and a week full of success! See you soon.");

        String companyName = environment.getProperty("info.company.name");
        String companyGoal = environment.getProperty("info.company.goal");
        String companyEmail = environment.getProperty("info.company.email");

        context.setVariable("company_name", companyName);
        context.setVariable("company_goal", companyGoal);
        context.setVariable("company_email", companyEmail);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}