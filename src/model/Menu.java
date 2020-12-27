package model;

import com.company.Account;
import validation.Validations;

import java.util.Scanner;

public final class Menu {

    public static int menuList(Customer customer) {
        System.out.println("1. Become a customer");
        System.out.println("2. Utility payments");
        System.out.println("3. Exit");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    return 1;
                case 2:
                    System.out.println("Choose one of your cards");
                    int j;
                    do {
                        j = scanner.nextInt();
                    } while (j >= customer.getAccounts().size());
                    Account account = customer.getAccounts().get(j);
                    Validations.validateCard(account);
                    return 2 + j;
                case 3:
                    System.out.println("You chose to do nothing");
                    return -1;
                default:
                    System.out.println("Choose something between 1-3");
            }
        }
    }

}
