package src.main.java.com.mahendra.test;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();
    private static Customer currentCustomer;

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
        System.out.println("Hello, " + currentCustomer.getName() + "!");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Logout");
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
            default:
                System.out.println("Invalid command!");
        }
    }

    private static void login() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        currentCustomer = bank.login(name);
        if (currentCustomer == null) {
            System.out.println("New customer created: " + name);
            currentCustomer = new Customer(name);
            bank.addCustomer(currentCustomer);
        }
        System.out.println("Your balance is $" + currentCustomer.getBalance());
    }

    private static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        bank.deposit(currentCustomer, amount);
        System.out.println("Your balance is $" + currentCustomer.getBalance());
    }

    private static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        if (bank.withdraw(currentCustomer, amount)) {
            System.out.println("Withdrawal successful. Your balance is $" + currentCustomer.getBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void transfer() {
        System.out.print("Enter recipient name: ");
        String recipientName = scanner.nextLine().trim();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        if (bank.transfer(currentCustomer, recipientName, amount)) {
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed.");
        }
    }

    private static void logout() {
        System.out.println("Goodbye, " + currentCustomer.getName() + "!");
        currentCustomer = null;
    }
}
