package fr.eql.ai116.proj2.tim.business;

public interface EnumLoadingBusiness {
    void loadPlugsIntoDatabase();
    void loadClosingReasonsIntoDatabase();
    void loadCarBrandsIntoDatabase();
    void loadCarModelsIntoDatabase();
    void loadCarWithdrawalReasons();
    void loadEvaluationTypeIntoDatabase();
    void loadPaymentRefusalTypeIntoDatabase();
    void loadPricingTypesIntoDatabase();
    void loadReservationCancelTypesIntoDatabase();
    void loadStationClosingReasonsIntoDatabase();
    void loadStationUnavailabilityTypesIntoDatabase();
    void loadWeekdaysIntoDatabase();

}
