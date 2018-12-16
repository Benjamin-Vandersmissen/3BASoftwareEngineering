package Payment;

public class VisaPayment extends Payment{

    private static double m_cost_visa = 0.25;

    public boolean pay(ExternalPayments externalPayments){
        super.pay(externalPayments);
        m_amount += m_cost_visa;
        if (externalPayments.visaPayments(this)) {
            m_state = PaymentStates.SUCCEED;
        } else {
            m_state = PaymentStates.FAILED;
        }
        return true;
    }

}