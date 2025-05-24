package org.example;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TransactionLog {
  private static final Logger log = LoggerFactory.getLogger(TransactionLog.class);

  private final Set<String> processedKeys = ConcurrentHashMap.newKeySet();


  private static final DateTimeFormatter TIMESTAMP_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

  // Логируем детали транзакции
  public void logTransaction(
      UUID transactionId,
      Account from,
      Account to,
      int amount,
      String status,
      String errorMessage
  ) {
    String message = String.format(
        "Transaction %s | From: %s (id=%d) | To: %s (id=%d) | Amount: %d | Status: %s | Error: %s",
        transactionId,
        from.getOwnerName(),
        from.getId(),
        to.getOwnerName(),
        to.getId(),
        amount,
        status,
        errorMessage != null ? errorMessage : "none"
    );

    if (status.equals("SUCCESS")) {
      log.info(message);  // Успешные транзакции в INFO
    } else {
      log.error(message); // Ошибки в ERROR
    }
  }

  public boolean isDuplicate(String key) {
    return !processedKeys.add(key); // Возвращает true, если ключ уже был
  }
}