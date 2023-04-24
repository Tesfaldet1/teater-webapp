package se.lexicon.teaterwebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.teaterwebapp.model.entity.Member;
import se.lexicon.teaterwebapp.model.entity.Staff;
import se.lexicon.teaterwebapp.model.entity.User;
import se.lexicon.teaterwebapp.repository.MemberRepository;
import se.lexicon.teaterwebapp.repository.StaffRepository;
import se.lexicon.teaterwebapp.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        memberRepository.save(new Member("Tesfaldet","Weldemicheal", "tweldemicheal@gmail.com", "Tesfaldet","tesfaldet"));
        memberRepository.save(new Member("Lubna","Farheen", "Lubna@gmail.com", "Lubna","Lubna" ));
        memberRepository.save(new Member("Emil ","Rutberg", "Emil_skull@hotmail.com", "Emil", "Emil" ));

        staffRepository.save(new Staff("Esther","Oluwaseun", LocalDate.parse("1999-04-05"),"ogeariyo@gmail.com"));
        staffRepository.save(new Staff("Tahsin ","Ferdous",LocalDate.parse("1999-04-05"),"tahsinkhan86@gmail.com"));

        userRepository.save(new User("Tesfaldet","tesfaldet","tweldemicheal@gmail.com"));
        userRepository.save(new User("Lubna","Lubna","Lubna@gmail.com"));
        userRepository.save(new User("Emil", "Emil", "Emil_skull@hotmail.com"));
        userRepository.save(new User("Esther","Esther","ogeariyo@gmail.com"));
        userRepository.save(new User("Tahsin", "Tahsin", "tahsinkhan86@gmail.com"));
    }
}
