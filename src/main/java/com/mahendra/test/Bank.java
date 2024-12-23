package src.main.java.com.mahendra.test;
import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Customer> customers = new HashMap<>();

    public Customer login(String name) {
        return customers.get(name);
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getName(), customer);
    }

    public void deposit(Customer customer, double amount) {
        customer.deposit(amount);
    }

    public boolean withdraw(Customer customer, double amount) {
        return customer.withdraw(amount);
    }

    public boolean transfer(Customer sender, String recipientName, double amount) {
        Customer recipient = customers.get(recipientName);
        if (recipient != null && sender.withdraw(amount)) {
            recipient.deposit(amount);
            return true;
        }
        return false;
    }
}