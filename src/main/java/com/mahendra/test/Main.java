package src.main.java.com.mahendra.test;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static BankServiceV2 bankServiceV2 = new BankServiceV2();
    private static CustomerV2 currentCustomer;

    public static void main(String[] args) {
        while (true) {
            if (currentCustomer == null) {
                displayLoginMenu();
            } else {
                displayMainMenu();
            }
            String command = scanner.nextLine().trim();
            executeCommand(command);
        }
    }

    private static void displayLoginMenu() {
        System.out.println("Welcome to ATM!");
        System.out.println("1. Login");
        System.out.println("0. Exit");
    }

    private static void displayMainMenu() {
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Logout");
        System.out.println("5. Transactions");
        System.out.println("0. Exit");
    }

    private static void executeCommand(String command) {
        switch (command) {
            case "0":
                System.exit(0);
            case "1":
                if (currentCustomer == null) {
                    login();
                } else {
                    deposit();
                }
                break;
            case "2":
                withdraw();
                break;
            case "3":
                transfer();
                break;
            case "4":
                logout();
                break;
            case "5":
                getTransactions();
                break;
            default:
                System.out.println("Invalid command!");
        }
    }

    private static void login() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        currentCustomer = bankServiceV2.login(name);
        if (currentCustomer == null) {
            System.out.println("New customer created: " + name);
            currentCustomer = new CustomerV2(name, 0.0, new HashMap<>(), new HashMap<>());
            bankServiceV2.addCustomer(currentCustomer);
        }
        System.out.println("Your balance is $" + currentCustomer.getBalance());
        System.out.println("owes from" + currentCustomer.owesFrom);
        System.out.println("owes to" + currentCustomer.owesTo);
    }

    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            bankServiceV2.deposit(currentCustomer, amount);
            System.out.println("Your balance is $" + currentCustomer.getBalance());
            System.out.println("owes from" + currentCustomer.owesFrom);
            System.out.println("owes to" + currentCustomer.owesTo);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine();
        }
    }

    private static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            if (bankServiceV2.withdraw(currentCustomer, amount)) {
                System.out.println("Withdrawal successful. Your balance is $" + currentCustomer.getBalance());
            } else {
                System.out.println("Insufficient funds.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine();
        }
    }

    private static void transfer() {
        System.out.print("Enter recipient name: ");
        String recipientName = scanner.nextLine().trim();
        if (recipientName.isEmpty()) {
            System.out.println("Recipient name cannot be empty.");
            return;
        }

        if (!recipientName.matches("[a-zA-Z0-9 ]+")) {
            System.out.println("Recipient name cannot contain special characters.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine();
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (currentCustomer.getName().equalsIgnoreCase(recipientName)) {
            System.out.println("Sender and recipient cannot be the same person.");
            return;
        }

        if (bankServiceV2.transfer(currentCustomer, recipientName, amount)) {
            System.out.println("Transfer successful.");
            System.out.println("Your balance is $" + currentCustomer.getBalance());
        } else {
            System.out.println("Transfer failed.");
        }
        System.out.println("owes from" + currentCustomer.owesFrom);
        System.out.println("owes to" + currentCustomer.owesTo);
    }

    private static void logout() {
        System.out.println("Goodbye, " + currentCustomer.getName() + "!");
        currentCustomer = null;
    }

    private static void getTransactions() {
        bankServiceV2.getTransactions(currentCustomer.getName());
    }
}

