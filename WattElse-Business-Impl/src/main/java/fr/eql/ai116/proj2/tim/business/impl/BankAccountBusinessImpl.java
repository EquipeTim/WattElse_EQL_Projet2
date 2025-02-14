package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.BankAccountBusiness;
import fr.eql.ai116.proj2.tim.dao.BankAccountDao;
import fr.eql.ai116.proj2.tim.entity.BankAccount;
import fr.eql.ai116.proj2.tim.entity.dto.BankAccountDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(BankAccountBusiness.class)
@Stateless
public class BankAccountBusinessImpl implements BankAccountBusiness {

    @EJB
    private BankAccountDao bankAccountDao;

    @Override
    public void addBankAccount(BankAccountDto bankAccountDto) {
        BankAccount account = new BankAccount(bankAccountDto.getIban(), bankAccountDto.getOwnerName(),
                bankAccountDto.getSwift(), null);
        bankAccountDao.addBankAccount(account,bankAccountDto.getUserId());
    }
}
