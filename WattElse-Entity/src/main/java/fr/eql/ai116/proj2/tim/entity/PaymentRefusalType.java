package fr.eql.ai116.proj2.tim.entity;
// Raison de refus paiement
public enum PaymentRefusalType {
    INSUFFICIEN_FUNDS("Fonds insuffisants"),
    INVALID_PAYMENT_METHOD("Méthode de paiement invalide"),
    EXPIRED_CREDIT_CARD("Carte de crédit expirée"),
    PAYMENT_SYSTEM_ERROR("Erreur du système de paiement"),
    FRAUDULENT_TRANSACTION_DETECTED("Transaction frauduleuse détectée"),
    PAYMENT_METHOD_BLOCKED("Méthode de paiement bloquée"),
    ACCOUNT_SUSPENDED_OR_CLOSED("Compte suspendu ou fermé"),
    UNAUTHORIZED_ACCESS("Accès non autorisé"),
    CURRENCY_NOT_ACCEPTED("Devise non acceptée"),
    TECHNICAL_ISSUE_WITH_PAYMENT_GATEWAY("Problème technique avec la passerelle de paiement"),
    INCOMPLETE_PAYMENT_INFORMATION("Informations de paiement incomplètes"),
    PAYMENT_LIMIT_EXCEEDED("Limite de paiement dépassée"),
    INSUFFICIENT_IDENTIFICATION_OR_VERIFICATION("Identification ou vérification insuffisante"),
    SERVICE_PROVIDER_DISPUTE("Litige avec le fournisseur de services"),
    PAYMENT_NETWORK_FAILURE("Echec du réseau de paiement");

    private final String label;

    PaymentRefusalType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
