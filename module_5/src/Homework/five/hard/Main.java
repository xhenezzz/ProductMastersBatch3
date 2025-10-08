package Homework.five.hard;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Integer> numbers = new ArrayList<>();

    System.out.print("Введите количество чисел: ");
    int n = scanner.nextInt();

    System.out.println("Введите " + n + " чисел:");
    for (int i = 0; i < n; i++) {
      numbers.add(scanner.nextInt());
    }

    System.out.println("Исходный список: " + numbers);

    removeDuplicates(numbers);

    System.out.println("После удаления дубликатов: " + numbers);
  }

  public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
      ArrayList<Integer> result = new ArrayList<>();

      for (Integer num : list) {
        if (!result.contains(num)) {
          result.add(num);
        }
      }

      list.clear();
      list.addAll(result);
    }
  }
}
