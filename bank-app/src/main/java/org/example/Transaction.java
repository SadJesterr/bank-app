package org.example;

import org.springframework.context.annotation.Bean;

import java.util.UUID;

public class Transaction {
  private final UUID transactionId = UUID.randomUUID(); // Уникальный ID
  private final TransactionLog transactionLog = new TransactionLog();

  public void transfer(Account from, Account to, Integer amount) {
    try {
      validateTransaction(from, to, amount);

      from.setAccount(from.getAccount() - amount);
      to.setAccount(to.getAccount() + amount);

      transactionLog.logTransaction(
          transactionId,
          from,
          to,
          amount,
          "SUCCESS",
          null
      );
    } catch (IllegalArgumentException | IllegalStateException e) {
      transactionLog.logTransaction(
          transactionId,
          from,
          to,
          amount,
          "FAILED",
          e.getMessage()
      );
      System.err.println("Transaction failed: " + e.getMessage());
      throw e;
    }
  }

  private void validateTransaction(Account from, Account to, int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount must be positive");
    }
    if (from.getAccount() < amount) {
      throw new IllegalStateException(
          String.format("Insufficient funds in account %d. Balance: %d, Required: %d",
              from.getId(),
              from.getAccount(),
              amount)
      );
    }
  }
}
