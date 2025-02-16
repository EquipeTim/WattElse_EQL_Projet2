package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.*;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class StartUpServlet extends HttpServlet {

    @EJB
    private ComponentsBusiness componentsBusiness;

    @EJB
    UserBusiness userBusiness;

    @EJB
    EnumLoadingBusiness enumLoadingBusiness;

    @EJB
    TransactionBusiness transactionBusiness;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("StartupServlet initialized - Application started!");
        enumLoadingBusiness.loadPlugsIntoDatabase();
        enumLoadingBusiness.loadClosingReasonsIntoDatabase();
        enumLoadingBusiness.loadCarBrandsIntoDatabase();
        enumLoadingBusiness.loadCarModelsIntoDatabase();
        enumLoadingBusiness.loadCarWithdrawalReasons();
        enumLoadingBusiness.loadEvaluationTypeIntoDatabase();
        enumLoadingBusiness.loadPaymentRefusalTypeIntoDatabase();
        enumLoadingBusiness.loadPricingTypesIntoDatabase();
        enumLoadingBusiness.loadReservationCancelTypesIntoDatabase();
        enumLoadingBusiness.loadStationClosingReasonsIntoDatabase();
        enumLoadingBusiness.loadStationUnavailabilityTypesIntoDatabase();
        enumLoadingBusiness.loadWeekdaysIntoDatabase();
    }
}
