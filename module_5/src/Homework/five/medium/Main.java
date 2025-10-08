package Homework.five.medium;

public class Main {

  public static void main(String[] args) {
    ArrayList<DayOfWeek> days = new ArrayList<>();

    for (DayOfWeek day : DayOfWeek.values()) {
      days.add(day);
    }

    System.out.println("Все дни недели:");
    for (DayOfWeek day : days) {
      System.out.println(day);
    }

    System.out.println("\nПроверка выходных:");
    for (DayOfWeek day : days) {
      System.out.println(day + " — выходной? " + isWeekend(day));
    }
  }
  public static boolean isWeekend(DayOfWeek day) {

    return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;

  }
}
