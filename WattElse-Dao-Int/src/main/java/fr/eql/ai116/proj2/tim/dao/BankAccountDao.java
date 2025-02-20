package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.BankAccount;


import java.util.List;

// Compte bancaire
public interface BankAccountDao {
    boolean registerBankAccount(BankAccount bankAccount, Long userId);
    boolean closeBankAccount(Long bankAccountId);
    boolean modifyBankAccount(BankAccount bankAccount);
    List<BankAccount> getBankAccounts(String token);
    BankAccount getBankAccountById(Long idBankAccount);

}
