package src.main.java.com.mahendra.test;

import java.time.Instant;

public class Transaction {
    private TransactionType type;
    private String sender;
    private String receiver;
    private double amount;
    private Instant timestamp;

    public Transaction(TransactionType type, String sender, String receiver, double amount, Instant timestamp) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
