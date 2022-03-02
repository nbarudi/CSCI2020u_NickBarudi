package ca.bungo.templates;

public abstract class PaymentMethod {

    protected int currentBalance;

    /**
     * This function will verify if the purchase information is correct.
     * (In this example case this will just be a number between 0-3 and must match the expected value in the purchase method).
     * @param information - An integer representing the purchase information.
     * @return True if purchase is Valid, false otherwise.
     * */
    protected abstract boolean isPurchaseValid(int information);

    /**
     * This function will charge the payment method the cost of the product.
     * If the cost is above the account limit it will return false.
     * (In an actual implementation of this you would most likely have a class containing all the purchase information)
     * @param cost - Cost of the product
     * @return True if account has funds to purchase, False otherwise.
     * */
    protected abstract boolean chargeAccount(int cost);

    /**
     * This function will return the invoice information about the purchase that was made
     * (For this test it will just output the purchase method and new balance of the account)
     * @return String showing invoice information
     * */
    protected abstract String getInvoice();

    /**
     * This constructor will create a new account with the balance provided
     * @param accountBalance - The balance of the newly created account
     * */
    public PaymentMethod(int accountBalance){
        this.currentBalance = accountBalance;
    }

    /**
     * This is my template function. The idea behind it is that in any PaymentMethod class
     * I can just call purchaseProduct(); and it will handle the logic differently based on which method is being used.
     * @param validPurchase - A test number to show an invalid vs valid purchase.
     * @param cost - Cost of the product being purchased.
     * @return Any resulting messages after execution.
     * */
    public final String purchaseProduct(int validPurchase, int cost){
        if(!isPurchaseValid(validPurchase))
            return "Invalid Purchase.. Ending.";
        else if(!chargeAccount(cost))
            return "Purchase failed! Account balance too low!";
        else
            return getInvoice();
    }


}
