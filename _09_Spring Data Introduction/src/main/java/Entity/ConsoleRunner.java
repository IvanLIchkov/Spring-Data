package Entity;

import Entity.models.User;
import Entity.services.AccountService;
import Entity.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        this.userService.register("testFirst", 20);
//        this.userService.register("testSecond",25);
        User user = this.userService.findByUsername("testFirst");
        User user2 = this.userService.findByUsername("testSecond");
//        this.accountService.depositMoney(BigDecimal.valueOf(24),user2.getAccounts().get(0));
//
//        this.accountService.depositMoney(BigDecimal.valueOf(24),user2.getId());
//
        this.accountService.transferMoney(user2.getAccounts().get(0), user.getAccounts().get(0),BigDecimal.valueOf(23));

//        this.accountService.withdrawMoney(BigDecimal.ONE, user.getAccounts().get(0));
    }
}
