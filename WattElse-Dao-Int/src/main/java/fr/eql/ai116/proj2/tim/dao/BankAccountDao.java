package fr.eql.ai116.proj2.tim.dao.impl;

import java.util.List;

// Compte bancaire
public interface BankAccountDao {
    void addBankAccount(BankAccount bankAccount, long userId);
    void closeBankAccount(BankAccount bankAccount);
    void modifyBankAccount(BankAccount bankAccount);
    List<BankAccount> getBankAccounts(long userId);
}
