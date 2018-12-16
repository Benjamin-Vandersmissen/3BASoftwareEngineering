package Payment;

public class MastercardPayment extends Payment{
    private static double m_cost_mastercard = 0.2;

    public boolean pay(ExternalPayments externalPayments){
        super.pay(externalPayments);
        m_amount += m_cost_mastercard;
        if (externalPayments.mastercardPayments(this)) {
            m_state = PaymentStates.SUCCEED;
        } else {
            m_state = PaymentStates.FAILED;
        }
        return true;
    }

}