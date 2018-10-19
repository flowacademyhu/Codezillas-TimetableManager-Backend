package hu.flowacademy.timetablemanager.service.authentication;

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
        SimpleMailMessage message = new SimpleMailMessage();
        try {
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
        return "Kedves 'új felhasználónk', \n" +
                "Az alábbi linken a regisztációs gombot megnyomva tudod aktiválni az accountodat. " +
                "A regisztrációs folyamat sikeres befejezéséhez, minden mezőt ki kell töltened, " +
                "és meg kell adnod a következő aktiváló kódot: " + activationString + "\n\n" +
                "/Az accountot az alábbi email-címmel regisztáltuk: " + emailTo + " / \n\n" +
                "A weboldalunk címe:" + "http://localhost:4200/" +"\n\n" +
                "Köszönettel,\n" + "A 'Flow Academy' csapata";
    }
}
