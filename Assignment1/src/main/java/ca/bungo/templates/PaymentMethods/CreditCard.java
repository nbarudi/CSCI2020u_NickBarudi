package ca.bungo.templates.PaymentMethods;

import ca.bungo.templates.PaymentMethod;

import java.util.Random;

public class CreditCard extends PaymentMethod {

    private String accountHolder;
    private String expireDate;
    private int csv;
    private String cardNumber;

    public CreditCard(int balance, String accountHolder, String expireDate){
        super(balance);
        this.accountHolder = accountHolder;
        this.expireDate = expireDate;
        //This section is just to generate a random CreditCard number and CSV
        this.csv = (new Random()).nextInt(999);
        StringBuilder cardNumberBuilder = new StringBuilder();
        for(int i = 0; i < 20; i++)
            cardNumberBuilder.append((new Random()).nextInt(10));
        this.cardNumber = cardNumberBuilder.toString().replaceAll("(.{4})", "$1 ").trim();
    }

    @Override
    protected boolean isPurchaseValid(int information) {
        return information == 0; //In this case a value of 0 will mean the CreditCard purchase is valid
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
        return "Purchase Successful:\n" + "Card Holder Name: " + this.accountHolder + "\n" +
        "Card Number: " + this.cardNumber + "\n" +
        "Card Expiry Date: " + this.expireDate + "\n" +
        "Card CSV: " + this.csv + "\n" +
        "Remaining Account Balance: " + this.currentBalance + "\n";
    }
}
