package src.main.java.com.mahendra.test;

import java.util.HashMap;

public class CustomerV2 {
    private String name;
    private double balance;
    HashMap<String, Double> owesTo;
    HashMap<String, Double> owesFrom;

    public CustomerV2(String name, double balance, HashMap<String, Double> owesTo, HashMap<String, Double> owesFrom) {
        this.name = name;
        this.balance = balance;
        this.owesTo = owesTo;
        this.owesFrom = owesFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public HashMap<String, Double> getOwesTo() {
        return owesTo;
    }

    public void setOwesTo(HashMap<String, Double> owesTo) {
        this.owesTo = owesTo;
    }

    public HashMap<String, Double> getOwesFrom() {
        return owesFrom;
    }

    public void setOwesFrom(HashMap<String, Double> owesFrom) {
        this.owesFrom = owesFrom;
    }
}
