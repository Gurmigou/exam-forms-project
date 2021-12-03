package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.model.entity.AccountForm;
import com.infpulse.studentspoll.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public void sendWelcomeEmail(User user) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", user);

        String process = templateEngine.process("welcome", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        createHelloMessage(user, process, mimeMessage);
        javaMailSender.send(mimeMessage);
    }

    public void sendFormResult(AccountForm accountForm) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", accountForm.getUser());
        context.setVariable("formScore", accountForm.getResultScore());

        String process = templateEngine.process("formResult", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        createCongratsMessage(accountForm, process, mimeMessage);
        javaMailSender.send(mimeMessage);
    }

    private void createHelloMessage(User user,
                                    String process,
                                    MimeMessage mimeMessage) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome " + user.getName());
        helper.setText(process, true);
        helper.setTo(user.getEmail());
    }

    private void createCongratsMessage(AccountForm accountForm, 
                                       String process, 
                                       MimeMessage mimeMessage) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Congratulations " + accountForm.getUser().getName() +
                " you have completed form " +
                accountForm.getForm().getTopicName() + ".");
        helper.setText(process, true);
        helper.setTo(accountForm.getUser().getEmail());
    }
}
