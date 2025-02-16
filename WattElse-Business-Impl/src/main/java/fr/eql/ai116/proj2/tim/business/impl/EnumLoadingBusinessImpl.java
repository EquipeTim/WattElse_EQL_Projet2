package fr.eql.ai116.proj2.tim.business.impl;

import fr.eql.ai116.proj2.tim.business.EnumLoadingBusiness;
import fr.eql.ai116.proj2.tim.dao.EnumLoadingDao;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(EnumLoadingBusiness.class)
@Stateless
public class EnumLoadingBusinessImpl implements EnumLoadingBusiness {

    @EJB
    EnumLoadingDao enumLoadingDao;



    @Override
    public void loadPlugsIntoDatabase() {
        enumLoadingDao.loadPlugsIntoDatabase();
    }

    @Override
    public void loadClosingReasonsIntoDatabase() {
        enumLoadingDao.loadAccountClosingReasonsIntoDatabase();
    }

    @Override
    public void loadCarBrandsIntoDatabase() {
        enumLoadingDao.loadCarBrandsIntoDatabase();
    }

    @Override
    public void loadCarModelsIntoDatabase() {enumLoadingDao.loadCarModelsIntoDatabase();
    }

    @Override
    public void loadCarWithdrawalReasons() {enumLoadingDao.loadCarWithdrawalReasons();}

    @Override
    public void loadEvaluationTypeIntoDatabase() {
        enumLoadingDao.loadEvaluationTypeIntoDatabase();
    }

    @Override
    public void loadPaymentRefusalTypeIntoDatabase() {
        enumLoadingDao.loadPaymentRefusalTypeIntoDatabase();
    }

    @Override
    public void loadPricingTypesIntoDatabase() {
        enumLoadingDao.loadPricingTypesIntoDatabase();}

    @Override
    public void loadReservationCancelTypesIntoDatabase() {
        enumLoadingDao.loadReservationCancelTypesIntoDatabase();
    }

    @Override
    public void loadStationClosingReasonsIntoDatabase() {
        enumLoadingDao.loadStationClosingReasonsIntoDatabase();
    }

    @Override
    public void loadStationUnavailabilityTypesIntoDatabase() {
        enumLoadingDao.loadStationUnavailabilityTypesIntoDatabase();
    }

    @Override
    public void loadWeekdaysIntoDatabase() {
        enumLoadingDao.loadWeekdaysIntoDatabase();
    }
}
