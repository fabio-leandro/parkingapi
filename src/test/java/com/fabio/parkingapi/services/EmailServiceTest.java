package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.EmailDto;
import com.fabio.parkingapi.entities.EmailModel;
import com.fabio.parkingapi.entities.enums.StatusEmail;
import com.fabio.parkingapi.repositories.EmailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private JavaMailSender mailSender;

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("When called It must send a email and return a EmailDto.")
    void sholudSendEmailThenRetunrEmailDto(){
        EmailDto emailDto = new EmailDto("Fabio","fabio@gmail.com","dev@gmail.com","Teste de Envio","We need to send a email teste.");
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto,emailModel);
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(emailModel.getEmailFrom());
        message.setTo(emailModel.getEmailTo());
        message.setSubject(emailModel.getSubject());
        message.setText(emailModel.getText());

        Mockito.when(emailRepository.save(emailModel)).thenReturn(emailModel);
        emailModel.setStatusEmail(StatusEmail.SENT);
        BeanUtils.copyProperties(emailModel,emailDto);

        EmailDto dtoAtual = emailService.sendEmail(emailDto);

        Assertions.assertEquals(emailDto,dtoAtual);


    }
}
