package Entity.services;

import Entity.models.Account;
import Entity.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {
        Optional<Account> account = this.accountRepository.findById(id);

        if(account.isEmpty()){
            throw new RuntimeException("Account does not exist");
        }
        BigDecimal curent = account.get().getBalance();

        if(amount.compareTo(curent)>0){
            throw new RuntimeException("Cannot withdraw!");
        }
        account.get().setBalance(curent.subtract(amount));
        this.accountRepository.save(account.get());
    }

    @Override
    public void depositMoney(BigDecimal amount, Long id) {
        System.out.println(id);
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("sorry no account"));

        BigDecimal balance = account.getBalance();
        BigDecimal add = balance.add(amount);
        account.setBalance(add);

        this.accountRepository.save(account);

    }

    @Override
    @Transactional
    public void transferMoney(Long accountFrom, Long accountTo, BigDecimal amount) {
        Account fromAccount = this.accountRepository.findById(accountFrom).orElseThrow();
        Account toAccount = this.accountRepository.findById(accountTo).orElseThrow();

        BigDecimal balanceFrom = fromAccount.getBalance();
        if(balanceFrom.compareTo(amount)<0){
            throw new RuntimeException("Not enough money!");
        }
        fromAccount.setBalance(balanceFrom.subtract(amount));

        BigDecimal balance = toAccount.getBalance();
        toAccount.setBalance(balance.add(amount));
    }
}
