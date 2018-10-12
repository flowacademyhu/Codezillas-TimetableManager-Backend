package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    private UserRepository userRepo;

    @Value("${spring.mail.username}")
    private String MESSAGE_FROM;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserRepository userRepository) {
        this.javaMailSender = javaMailSender;
        this.userRepo = userRepository;
    }

    public boolean sendMessage(String emailTo) {

        SimpleMailMessage message = null;
        try {
            message = new SimpleMailMessage();
            message.setFrom(MESSAGE_FROM);
            message.setTo(emailTo);
            message.setSubject("Aktivációs üzenet!");
            message.setText(getMessage(emailTo));
            javaMailSender.send(message);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

    private String getMessage(String emailTo) {
        User user = userRepo.findByEmail(emailTo);
        String activationString = user.getActivationCode();
//        String link = "<a href=\"WWW.google.es\">ACTIVAR CUENTA</a>";
        return "Kedves 'hallgatónk', \n" +
                "kérjük hogy az alábbi linkre kattintva, aktiváld ezt a regisztrációs folyamatot," +
                "hogy a továbbiakban hozzáférhess az órarendjeidhez. \n\n" +
                "http://www.flow.timetable-manager.hu/reg?" + activationString + "\n\n" +
                "vagy...\n\n" +
                "http://localhost:4200/regisztracio?" + activationString + "\n\n" +
                "Köszönettel,\n" + "A 'Flow Academy' csapata";
    }
}
