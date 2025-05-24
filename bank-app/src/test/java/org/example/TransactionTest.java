package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

  @Test
  void transactionFromTo_shouldTransferMoney_whenSufficientFunds() {
    // Arrange
    Transaction transaction = new Transaction();
    Account accountFrom = new Account();
    accountFrom.setAccount(1000);
    Account accountTo = new Account();
    accountTo.setAccount(500);
    int amount = 300;

    // Act
    transaction.transfer(accountFrom, accountTo, amount);

    // Assert
    assertEquals(700, accountFrom.getAccount());
    assertEquals(800, accountTo.getAccount());
  }

  @Test
  void transactionFromTo_shouldHandleZeroAmount() {
    // Arrange
    Transaction transaction = new Transaction();
    Account accountFrom = new Account();
    accountFrom.setAccount(1000);
    Account accountTo = new Account();
    accountTo.setAccount(500);
    int amount = 0;

    // Act
    transaction.transfer(accountFrom, accountTo, amount);

    // Assert
    assertEquals(1000, accountFrom.getAccount());
    assertEquals(500, accountTo.getAccount());
  }

  @Test
  void transactionFromTo_shouldThrowNotEnoughMoney() {
    Transaction transaction = new Transaction();
    Account accountFrom = new Account();
    accountFrom.setAccount(200);
    Account accountTo = new Account();
    accountTo.setAccount(500);

    assertThrows(IllegalStateException.class,
        () -> transaction.transfer(accountFrom, accountTo, 300),
        "Transaction failed: Not enough money in the source account");
  }

  @Test
  void transactionFromTo_shouldThrowNegativeAmount() {
    Transaction transaction = new Transaction();
    Account accountFrom = new Account();
    accountFrom.setAccount(1000);
    Account accountTo = new Account();
    accountTo.setAccount(500);

    assertThrows(IllegalArgumentException.class,
        () -> transaction.transfer(accountFrom, accountTo, -100),
        "Transaction failed: Amount must be positive");
  }
}