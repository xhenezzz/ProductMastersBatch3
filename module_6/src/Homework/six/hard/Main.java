package Homework.six.hard;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
      SafeList<String> list = new SafeList<>();

      list.add("apple");
      list.add("banana");
      list.add(null);
      list.add("apple");

      System.out.println(list.get(0));
      System.out.println(list.get(5));
      System.out.println(list.size());
  }

  public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
    return null;
  }
}
