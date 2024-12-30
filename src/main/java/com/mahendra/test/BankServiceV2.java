package src.main.java.com.mahendra.test;



import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankServiceV2 {
    List<Transaction> transactions = new ArrayList<>();
    private Map<String, CustomerV2> customers = new HashMap<>();

    public CustomerV2 login(String name) {
        return customers.get(name);
    }

    public void addCustomer(CustomerV2 customer) {
        customers.put(customer.getName(), customer);
    }

    public void deposit(CustomerV2 customer, double depositAmount) {
        transactions.add(new Transaction(TransactionType.DEPOSIT, customer.getName(), null, depositAmount, Instant.now()));
        customer.setBalance(customer.getBalance() + depositAmount);
    }

    public boolean withdraw(CustomerV2 customer, double amount) {
        if (customer.getBalance() >= amount) {
            transactions.add(new Transaction(TransactionType.WITHDRAW, null, customer.getName(), amount, Instant.now()));
            customer.setBalance(customer.getBalance() - amount);
            return true;
        }
        return false;
    }

    public boolean transfer(CustomerV2 sender, String recipientName, double amount) {
        CustomerV2 recipient = customers.get(recipientName);
        if (recipient != null && sender.getName().equalsIgnoreCase(recipientName)){
            return false;
        }

        if (sender.getOwesTo().containsKey(recipientName)) {
            sender.getOwesTo().put(recipientName, sender.getOwesTo().get(recipientName) - amount);
            recipient.getOwesFrom().put(sender.getName(), recipient.getOwesFrom().get(sender.getName()) - amount);
            if (sender.getBalance() > 0) {
                sender.setBalance(sender.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
                return true;
            }
           return true;
        }

        if (sender.getOwesFrom().containsKey(recipientName)) {
            sender.getOwesFrom().put(recipientName, sender.getOwesFrom().get(recipientName) - amount);
            recipient.getOwesTo().put(sender.getName(), recipient.getOwesTo().get(sender.getName()) - amount);
            return true;
        }

        if (sender.getBalance() > amount) {
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            transactions.add(new Transaction(TransactionType.TRANSFER, sender.getName(), recipientName, amount, Instant.now()));
            return true;
        } else {
            double diffAmount = amount - sender.getBalance();
            recipient.setBalance(recipient.getBalance() + sender.getBalance());
            sender.setBalance(0);
            sender.getOwesTo().put(recipientName, diffAmount);
            recipient.getOwesFrom().put(sender.getName(), diffAmount);
            transactions.add(new Transaction(TransactionType.TRANSFER, sender.getName(), recipientName, amount, Instant.now()));
            return true;
        }
    }


    public void getTransactions(String name) {
        for (Transaction transaction: transactions) {
            if (name.equalsIgnoreCase(transaction.getSender())) {
                System.out.println(transaction);
            }
        }
    }
}
