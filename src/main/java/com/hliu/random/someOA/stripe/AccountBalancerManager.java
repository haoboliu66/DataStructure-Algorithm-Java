package com.hliu.random.someOA.stripe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AccountBalancerManager {

  private static final String INIT = "INIT";
  private static final String GET = "GET";
  private static final String POST = "POST";

  private static final TreeMap<Integer, Command> commandMap = new TreeMap<>();

//  private static Command parseCommand(String[] str) {
//    if (str == null || str.length < 3) {
//      throw new RuntimeException("invalid command");
//    }
//    String type = str[0];
//    if (INIT.equals(type)) {
//      return new InitCommand();
//    }
//    if (POST.equals(type)) {
//      return new PostCommand(Integer.parseInt(str[1]), str[2], str[3], Integer.parseInt(str[4]));
//    }
//    if (GET.equals(type)) {
//      return new GetCommand(Integer.parseInt(str[1]), str[2], str[3]);
//    }
//
//  }

  public static String get_command_results(List<String> commands) {
    for (String c : commands) {

      String[] str = c.split(",");

//      commandMap.put()
    }

    return "";
  }



  private static final Map<String, Integer> balance = new HashMap<>();

  abstract static class Command {

  }

  private static class User {

    String name;
  }

  private static class InitCommand extends Command {

    String user;
    int balance;
    List<String> bank;

    public InitCommand(String user, int balance, List<String> bank) {
      this.user = user;
      this.balance = balance;
      this.bank = bank;
    }
  }

  private static class GetCommand extends Command {

    int timestamp;
    String user;
    String bank;

    public GetCommand(int timestamp, String user, String bank) {
      this.timestamp = timestamp;
      this.user = user;
      this.bank = bank;
    }
  }

  private static class PostCommand extends Command {

    int timestamp;
    String sender;
    String receiver;
    String bank;
    int amount;

    public PostCommand(int timestamp, String sender, String receiver, String bank, int amount) {
      this.timestamp = timestamp;
      this.sender = sender;
      this.receiver = receiver;
      this.bank = bank;
      this.amount = amount;
    }
  }

}
