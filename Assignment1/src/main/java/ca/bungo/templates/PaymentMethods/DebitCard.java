package ca.bungo.templates.PaymentMethods;

import ca.bungo.templates.PaymentMethod;

import java.util.Random;

public class DebitCard extends PaymentMethod {

    public String accountHolder;
    public String expireDate;
    public int csv;
    public String cardNumber;

    public DebitCard(int balance, String accountHolder, String expireDate){
        super(balance);
        this.accountHolder = accountHolder;
        this.expireDate = expireDate;
        //This section is just to generate a random DebitCard number and CSV
        this.csv = (new Random()).nextInt(999);
        StringBuilder cardNumberBuilder = new StringBuilder();
        for(int i = 0; i < 20; i++)
            cardNumberBuilder.append((new Random()).nextInt(10));
        this.cardNumber = cardNumberBuilder.toString().replaceAll("(.{4})", "$1 ").trim();
    }

    @Override
    protected boolean isPurchaseValid(int information) {
        return information == 2; //In this case a value of 2 will mean the DebitCard purchase is valid
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
