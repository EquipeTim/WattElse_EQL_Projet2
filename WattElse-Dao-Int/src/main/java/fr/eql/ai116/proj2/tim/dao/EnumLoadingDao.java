package fr.eql.ai116.proj2.tim.dao;

public interface EnumLoadingDao {
    void loadPlugsIntoDatabase();
    void loadAccountClosingReasonsIntoDatabase();
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
