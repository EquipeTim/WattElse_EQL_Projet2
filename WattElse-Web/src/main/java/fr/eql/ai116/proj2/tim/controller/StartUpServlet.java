package fr.eql.ai116.proj2.tim.controller;

import fr.eql.ai116.proj2.tim.business.CarBusiness;
import fr.eql.ai116.proj2.tim.business.ComponentsBusiness;
import fr.eql.ai116.proj2.tim.business.UserBusiness;

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
    CarBusiness carBusiness;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("StartupServlet initialized - Application started!");
        componentsBusiness.loadPlugsIntoDatabase();
        userBusiness.loadClosingReasonsIntoDatabase();
        carBusiness.loadCarBrandsIntoDatabase();
        carBusiness.loadCarModelsIntoDatabase();
    }
}
