package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.TransactionBusiness;
import fr.eql.ai116.proj2.tim.dao.TransactionDao;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(TransactionBusiness.class)
@Stateless
public class TransactionBusinessImpl implements TransactionBusiness {

    @EJB
    private TransactionDao transactionDao;



}
