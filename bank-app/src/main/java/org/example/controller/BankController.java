package org.example.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.Account;
import org.example.Transaction;
import org.example.TransactionLog;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BankController {
  private final TransactionLog transactionLog;

  public BankController(TransactionLog transactionLog) {
    this.transactionLog = transactionLog;
  }

  public record TransferRequest(
      @JsonProperty("from") int fromId,
      @JsonProperty("to") int toId,
      @JsonProperty("amount") int amount
  ) {}

  @Transactional
  @PostMapping("/transfer")
  public ResponseEntity<String> transferMoney(
      @RequestHeader("Idempotency-Key") String idempotencyKey,
      @RequestBody TransferRequest request) {
    if (transactionLog.isDuplicate(idempotencyKey)) {
      return ResponseEntity.status(409).body("Transaction already processed");
    }

    Transaction transaction = new Transaction();

    Account from = new Account();
    from.setId(request.fromId);
    from.setAccount(1000);

    Account to = new Account();
    to.setId(request.toId);
    to.setAccount(500);

    transaction.transfer(from, to, request.amount);
    return ResponseEntity.ok("Transfer successful");
  }

}