package ca.bungo.templates.PaymentMethods;

import ca.bungo.templates.PaymentMethod;

import java.util.Random;

public class AccountBalance extends PaymentMethod {

    public String accountHolder;

    public AccountBalance(int balance, String accountHolder){
        super(balance);
        this.accountHolder = accountHolder;
    }

    @Override
    public boolean isPurchaseValid(int information) {
        return information == 1; //In this case a value of -1 will mean the CreditCard purchase is valid
    }

    @Override
    public boolean chargeAccount(int cost) {
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
    public String getInvoice() {
        return "Purchase Successful:\n" +  "Account Holder Name: " + this.accountHolder + "\n" +
        "Remaining Account Balance: " + this.currentBalance + "\n";
    }
}
