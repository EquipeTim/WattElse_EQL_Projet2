package fr.eql.ai116.proj2.tim.business;

import fr.eql.ai116.proj2.tim.entity.BankAccount;
import fr.eql.ai116.proj2.tim.entity.dto.BankAccountDto;
import fr.eql.ai116.proj2.tim.entity.dto.PaymentCloseDto;

import java.util.List;

public interface BankAccountBusiness {
    boolean registerBankAccount(BankAccountDto bankAccountDto, Long userId);
    List<BankAccount> getBankAccounts(String token);
    boolean closeBankAccount(PaymentCloseDto paymentCloseDto, Long idBankAccount);
}
