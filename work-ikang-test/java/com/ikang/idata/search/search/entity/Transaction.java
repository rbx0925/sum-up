package com.ikang.idata.search.search.entity;

public class Transaction {
 
    private Trader trader;
    private int yearOfTransaction;
    private int transactionAmount;
 
    public Transaction() {
    }
 
    public Transaction(Trader trader, int yearOfTransaction, int transactionAmount) {
        this.trader = trader;
        this.yearOfTransaction = yearOfTransaction;
        this.transactionAmount = transactionAmount;
    }
 
    public Trader getTrader() {
        return trader;
    }
 
    public void setTrader(Trader trader) {
        this.trader = trader;
    }
 
    public int getYearOfTransaction() {
        return yearOfTransaction;
    }
 
    public void setYearOfTransaction(int yearOfTransaction) {
        this.yearOfTransaction = yearOfTransaction;
    }
 
    public int getTransactionAmount() {
        return transactionAmount;
    }
 
    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
 
    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", yearOfTransaction=" + yearOfTransaction +
                ", transactionAmount=" + transactionAmount +
                '}';
    }
}