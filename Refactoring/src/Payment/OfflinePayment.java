package Payment;

public class OfflinePayment extends Payment{

    public boolean pay(ExternalPayments externalPayments){
        super.pay(externalPayments);
        return true;
    }
    public void setOfflinePayed() {
        payment.m_state = PaymentState.SUCCEED;
    }
}