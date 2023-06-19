package se.lexicon.teaterwebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.teaterwebapp.model.entity.*;
import se.lexicon.teaterwebapp.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactInformationRepository contactInformationRepository;

    @Autowired
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    @Autowired
    private EventRepository eventRepository;

    public InitData(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       Member createdMember1 = memberRepository.save(new Member("Tesfaldet ","Weldemicheal",LocalDate.parse("1989-12-10"),"tweldemicheal@gmail.com"));
        memberRepository.save(new Member("Lubna", "Fraheen", LocalDate.parse("1999-10-23"), "lubna@gmail.com"));
        memberRepository.save(new Member("Emil", "Rutberg",LocalDate.parse("1992-11-26"), "Emil_skull@hotmail.com"));

        staffRepository.save(new Staff("Esther","Oluwaseun", LocalDate.parse("1999-04-05"),"ogeariyo@gmail.com"));
        staffRepository.save(new Staff("Tahsin ","Ferdous",LocalDate.parse("1999-04-05"),"tahsinkhan86@gmail.com"));

        ContactInformation createdContactInformation = contactInformationRepository.save(new ContactInformation("0762169989","Kärrhögsgatan 92","Jönköping "));
        Account createdAccount = accountRepository.save(new Account("Tesfaldet", "tesfaldet"));
        //Role createdMemberRole = roleRepository.save(new Role(RoleType.MEMBER));
      //  Role createdStaffRole =roleRepository.save(new Role(RoleType.STAFF));
        User createdUser1 = userRepository.save(new User("tesfaldet", "tesfaldet","tweldemicheal@gmail.com"));
        User createdUser2 = userRepository.save(new User("nuna", "nesik", "nuna@gmail.com"));

        Event createdEvent1 = eventRepository.save(new Event(
                "Opening Night Jitters",
                LocalDateTime.parse("2023-10-23T10:00:00"),
                LocalDateTime.parse("2023-10-23T12:00:00")
        ));

        Event createdEvent2 = eventRepository.save(new Event(
                "Head Start Theatre Co",
                LocalDateTime.parse("2023-06-23T10:00:00"),
                LocalDateTime.parse("2023-06-23T13:00:00")
        ));



        //userRepository.save(new User(createdAccount, (Set<Role>) createdRole, Collections.singletonList(createdContactInformation)));
    }
}
