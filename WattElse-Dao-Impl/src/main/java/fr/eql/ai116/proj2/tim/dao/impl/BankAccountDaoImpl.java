package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.BankAccountDao;
import fr.eql.ai116.proj2.tim.entity.BankAccount;

import java.util.Collections;
import java.util.List;

public class BankAccountDaoImpl  implements BankAccountDao {
    private static final String REQ_CLOSE_BANK_ACC = "UPDATE bank_account SET closing_date_bank_account = ? WHERE id_user = ?";

    @Override
    public void addBankAccount(BankAccount bankAccount, long userId) {

    }

    @Override
    public void closeBankAccount(BankAccount bankAccount) {

    }

    @Override
    public void modifyBankAccount(BankAccount bankAccount) {

    }

    @Override
    public List<BankAccount> getBankAccounts(long userId) {
        return Collections.emptyList();
    }
}
