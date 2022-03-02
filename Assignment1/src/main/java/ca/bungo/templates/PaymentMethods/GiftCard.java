package ca.bungo.templates.PaymentMethods;

import ca.bungo.templates.PaymentMethod;

import java.util.Random;

public class GiftCard extends PaymentMethod {

    public String expireDate;
    public String cardNumber;

    public GiftCard(int balance, String expireDate){
        super(balance);
        this.expireDate = expireDate;
        //This section is just to generate a random CreditCard number and CSV
        StringBuilder cardNumberBuilder = new StringBuilder();
        for(int i = 0; i < 20; i++)
            cardNumberBuilder.append((new Random()).nextInt(10));
        this.cardNumber = cardNumberBuilder.toString().replaceAll("(.{4})", "$1 ").trim();
    }

    @Override
    protected boolean isPurchaseValid(int information) {
        return information == 3; //In this case a value of -1 will mean the CreditCard purchase is valid
    }

    @Override
    protected boolean chargeAccount(int cost) {
        /* If the account has enough money
         * remove the required balance from the account and return true
         * otherwise return false
         * */
        if(cost < this.currentBalance)
            this.currentBalance -= cost;
        else
            return false;
        return true;
    }

    @Override
    protected String getInvoice() {
        return "Purchase Successful:\n" + "Card Number: " + this.cardNumber + "\n" +
        "Card Expiry Date: " + this.expireDate + "\n" +
        "Remaining GiftCard Balance: " + this.currentBalance + "\n";
    }
}
