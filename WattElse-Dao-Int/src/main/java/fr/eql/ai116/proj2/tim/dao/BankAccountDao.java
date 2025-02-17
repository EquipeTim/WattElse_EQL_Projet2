package fr.eql.ai116.proj2.tim.dao;

import fr.eql.ai116.proj2.tim.entity.BankAccount;

import java.util.List;

// Compte bancaire
public interface BankAccountDao {
    void addBankAccount(BankAccount bankAccount, Long userId);
    void closeBankAccount(BankAccount bankAccount);
    void modifyBankAccount(BankAccount bankAccount);
    List<BankAccount> getBankAccounts(String token);
}
