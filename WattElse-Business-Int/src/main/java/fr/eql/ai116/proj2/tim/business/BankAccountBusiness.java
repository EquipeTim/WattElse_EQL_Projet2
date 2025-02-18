package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.BankAccount;
import fr.eql.ai116.proj2.tim.entity.dto.BankAccountDto;

import java.util.List;

public interface BankAccountBusiness {
    void addBankAccount(BankAccountDto bankAccountDto);
    List<BankAccount> getBankAccounts(String token);
}
