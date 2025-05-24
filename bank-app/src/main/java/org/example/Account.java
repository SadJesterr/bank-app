package org.example;

public class Account {
  private Integer id;
  private String ownerName;
  private Integer account;

  public Integer getAccount() {
    return account;
  }

  public Integer getId() {
    return id;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setAccount(Integer account) {
    this.account = account;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public String getInformation () {
    return String.format("id - %s, owner name - %s, account - %d", getId(), getOwnerName(), getAccount());
  }

  public String toJson() {
    return String.format(
        "{\"id\":%d,\"owner\":\"%s\",\"balance\":%d}",
        this.getId(),
        this.getOwnerName(),
        this.getAccount()
    );
  }
}
