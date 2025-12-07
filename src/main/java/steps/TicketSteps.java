package steps;

import pages.TicketPage;
import pages.PaymentPage;

public class TicketSteps {
    private final TicketPage ticketPage = new TicketPage();
    private final PaymentPage paymentPage = new PaymentPage();

    public void purchaseTicketWithCard(String cardNumber, String cardHolder, String month, String year, String cvc) {

    }

    public boolean checkPaymentSuccess() {
        return paymentPage.isPaymentSuccessful();
    }

    public String getPaymentConfirmation() {
        return paymentPage.getSuccessMessageText();
    }

    public void clickBuyTicketButton() {
    }
}