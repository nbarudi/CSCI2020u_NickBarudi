package ca.bungo;

import ca.bungo.templates.PaymentMethod;
import ca.bungo.templates.PaymentMethods.AccountBalance;
import ca.bungo.templates.PaymentMethods.CreditCard;
import ca.bungo.templates.PaymentMethods.DebitCard;
import ca.bungo.templates.PaymentMethods.GiftCard;

public class TemplateExample {

    //Output Colour Formatting
    static String ANSI_RED = "\u001B[31m";
    static String ANSI_BLUE = "\u001B[34m";
    static String ANSI_GREEN = "\u001B[32m";
    static String ANSI_YELLOW = "\u001B[33m";
    static String ANSI_WHITE = "\u001B[37m";

    public static void main(String... args){
        int productCost = 100; //This product will cost $100;
        PaymentMethod method1 = new AccountBalance(150, "Billy Bob"); //Our first method will have a balance of $150 and belongs to Billy Bob
        PaymentMethod method2 = new CreditCard(300, "Jessica Jensen", "10/25"); //Our second method will have a balance of $300 and belongs to Jessica Jensen
        PaymentMethod method3 = new DebitCard(5000, "Henry Heart", "7/27"); //Our third method will have a balance of $5000 and belongs to Henry Heart
        PaymentMethod method4 = new GiftCard(25, "6/23"); //Our forth method will have a balance of $25

        /*
        * Because these methods all come from the template of Payment Method I can access variables and functions that exist in PaymentMethod but no
        * ones that are unique to each individual class.
        *
        * For example: I would not be able to access accountHolder information in each of the payment methods due to the AccountHolder variable
        * not being apart of the PaymentMethod class.
        *
        * But since the 'PurcahseProduct' function exists in the Payment Method class I can execute the function for each payment method
        * without declaring it in the unique classes.
        * */
        System.out.println("The outputs have been formatted to allow for better view of individual class results:");
        System.out.println(ANSI_RED + "RED - Representing Account Balance");
        System.out.println(ANSI_BLUE + "BLUE - Representing Credit Card");
        System.out.println(ANSI_GREEN + "GREEN - Representing Debit Card");
        System.out.println(ANSI_YELLOW + "YELLOW - Representing Gift Card\n\n");
        for(int i = 0; i < 4; i++){
            //This will go through each payment method and create a result for each 'purchase information'
            //(just a 0-3 value which is unique to each payment method)
            System.out.printf(ANSI_WHITE + "Account Balance Result (Purchase Info: %s):\nResult:\n" + ANSI_RED + "%s\n", i, method1.purchaseProduct(i, productCost));
            System.out.printf(ANSI_WHITE + "Credit Card Result (Purchase Info: %s):\nResult:\n" + ANSI_BLUE + "%s\n", i, method2.purchaseProduct(i, productCost));
            System.out.printf(ANSI_WHITE + "Debit Card Result (Purchase Info: %s):\nResult:\n" + ANSI_GREEN + "%s\n", i, method3.purchaseProduct(i, productCost));
            System.out.printf(ANSI_WHITE + "Gift Card Result (Purchase Info: %s):\nResult:\n" + ANSI_YELLOW + "%s\n", i, method4.purchaseProduct(i, productCost));
            //In all 4 instances I run the same function call but each returns different values
            //This is why the Template design method is useful because it allows you to quickly run different
            //Code types all within 1 function.
        }
    }

}
