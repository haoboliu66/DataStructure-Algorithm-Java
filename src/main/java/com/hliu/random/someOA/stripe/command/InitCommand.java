package com.hliu.random.someOA.stripe.command;

import java.util.List;

public class InitCommand {

  String user;
  int balance;
  List<String> bank;

  public InitCommand(String user, int balance, List<String> bank) {
    this.user = user;
    this.balance = balance;
    this.bank = bank;
  }

}
