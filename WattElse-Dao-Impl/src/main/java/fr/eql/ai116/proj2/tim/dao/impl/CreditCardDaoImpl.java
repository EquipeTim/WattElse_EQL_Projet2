package fr.eql.ai116.proj2.tim.dao.impl;

import fr.eql.ai116.proj2.tim.dao.CreditCardDao;
import fr.eql.ai116.proj2.tim.dao.impl.connection.WattElseDataSource;
import fr.eql.ai116.proj2.tim.entity.CreditCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class CreditCardDaoImpl implements CreditCardDao {
    private final DataSource dataSource = new WattElseDataSource();
    private static final Logger logger = LogManager.getLogger();


    private static final String REQ_CLOSE_CARD = "UPDATE credit_card SET withdrawal_date_card = ? WHERE id_user = ?";


    @Override
    public void addCreditCard(CreditCard creditCard, Long userId) {

    }

    @Override
    public void closeCreditCard(CreditCard creditCard) {

    }

    @Override
    public void modifyCreditCard(CreditCard creditCard) {

    }

    @Override
    public List<CreditCard> getCreditCards(long userId) {
        return Collections.emptyList();
    }
}
