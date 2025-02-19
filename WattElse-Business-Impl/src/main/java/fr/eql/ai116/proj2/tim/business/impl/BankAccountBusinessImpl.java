package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.BankAccountBusiness;
import fr.eql.ai116.proj2.tim.dao.BankAccountDao;
import fr.eql.ai116.proj2.tim.entity.BankAccount;
import fr.eql.ai116.proj2.tim.entity.dto.BankAccountDto;
import fr.eql.ai116.proj2.tim.entity.dto.PaymentCloseDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;

@Remote(BankAccountBusiness.class)
@Stateless
public class BankAccountBusinessImpl implements BankAccountBusiness {

    @EJB
    private BankAccountDao bankAccountDao;



    @Override
    public boolean registerBankAccount(BankAccountDto bankAccountDto, Long userId) {
            BankAccount bankAccount = new BankAccount(bankAccountDto.getIban(), bankAccountDto.getOwnerName(),
                    bankAccountDto.getSwift(), null);
           return bankAccountDao.registerBankAccount(bankAccount, userId);
        }


    @Override
    public List<BankAccount> getBankAccounts(String token) {
        return bankAccountDao.getBankAccounts(token);
    }



    @Override
    public boolean closeBankAccount(PaymentCloseDto paymentCloseDto, Long idBankAccount) {
        BankAccount bankAccount = bankAccountDao.getBankAccountById(paymentCloseDto.getIdBankAccount());
        if(bankAccount !=null){
            bankAccountDao.closeBankAccount(paymentCloseDto.getIdBankAccount());
            return true;
        }
        return false;
    }
}
