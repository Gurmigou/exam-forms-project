package com.infpulse.studentspoll.service;

import com.infpulse.studentspoll.model.entity.AccountForm;
import com.infpulse.studentspoll.model.entity.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
public class EmailService {
    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;
    private InputStreamSource imageSource;
    @Value("classpath:images/infopulse-footer.png")
    private Resource resource;

    @Autowired
    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @SneakyThrows
    @PostConstruct
    public void initMethod() {
        byte[] imageByte = FileCopyUtils.copyToByteArray(resource.getInputStream());
        imageSource = new ByteArrayResource(imageByte);
    }

    public void sendWelcomeEmail(User user) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("image", resource.getFilename());

        String process = templateEngine.process("welcome", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        createHelloMessage(user, process, mimeMessage);
        javaMailSender.send(mimeMessage);
    }

    public void sendFormResult(AccountForm accountForm) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", accountForm.getUser());
        context.setVariable("formScore", accountForm.getResultScore());
        context.setVariable("image", resource.getFilename());

        String process = templateEngine.process("formResult", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        createCongratsMessage(accountForm, process, mimeMessage);
        javaMailSender.send(mimeMessage);
    }

    private void createHelloMessage(User user,
                                    String process,
                                    MimeMessage mimeMessage) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("Welcome " + user.getName());
        helper.setText(process, true);
        helper.addInline(Objects.requireNonNull(resource.getFilename()), imageSource, "image/png");
        helper.setTo(user.getEmail());
    }

    private void createCongratsMessage(AccountForm accountForm,
                                       String process,
                                       MimeMessage mimeMessage) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setSubject("Congratulations " + accountForm.getUser().getName() +
                " you have completed form " +
                accountForm.getForm().getTopicName() + ".");
        helper.setText(process, true);
        helper.addInline(Objects.requireNonNull(resource.getFilename()), imageSource, "image/png");
        helper.setTo(accountForm.getUser().getEmail());
    }
}
