package Payment;

public class BancontactPayment extends Payment{
	private static double m_cost_bancontact = 0.1;

    public boolean pay(ExternalPayments externalPayments){
        super.pay(externalPayments);
        m_amount += m_cost_bancontact;
        if (m_externalPayments.bancontactPayment(payment)) {
            m_state = PaymentStates.SUCCEED;
        } else {
            m_state = PaymentStates.FAILED;
        }
        return true;
    }

}