package Homework.seven;

import java.util.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TwitterConsoleApp {
  private final TwitterService service;
  private User currentUser;
  private final Scanner scanner;

  public TwitterConsoleApp() {
    this.service = new TwitterService();
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    System.out.print("Введите ваше имя: ");
    String username = scanner.nextLine().trim();
    if (username.isEmpty()) {
      username = "Guest";
    }
    this.currentUser = new User(username);
    System.out.printf("Добро пожаловать, %s!\n", currentUser.getUsername());
    System.out.println("Добавлены стартовые посты.");

    mainLoop();
  }

  private void mainLoop() {
    int choice = -1;
    while (choice != 7) {
      printMenu();
      try {
        System.out.print("Выберите действие: ");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1:
            createPost();
            break;
          case 2:
            likePost();
            break;
          case 3:
            repostPost();
            break;
          case 4:
            showAllPosts();
            break;
          case 5:
            showPopularPosts();
            break;
          case 6:
            showMyPosts();
            break;
          case 7:
            System.out.println("Выход…");
            break;
          default:
            System.out.println("Неверный выбор. Попробуйте снова.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Ошибка: Введите число.");
        scanner.nextLine();
        choice = -1;
      } catch (Exception e) {
        System.out.println("Произошла ошибка: " + e.getMessage());
      }
    }
  }

  private void printMenu() {
    System.out.println("\n=== Twitter Console ===");
    System.out.println("1. Написать пост");
    System.out.println("2. Лайкнуть пост");
    System.out.println("3. Сделать репост");
    System.out.println("4. Показать все посты");
    System.out.println("5. Показать популярные посты");
    System.out.println("6. Показать мои посты");
    System.out.println("7. Выход");
  }

  private void createPost() {
    System.out.print("Введите текст поста (макс. 280 символов): ");
    String content = scanner.nextLine();
    service.createPost(currentUser, content);
    System.out.println("Пост добавлен!");
  }

  private void likePost() {
    System.out.print("Введите ID поста, который хотите лайкнуть: ");
    try {
      long id = scanner.nextLong();
      scanner.nextLine();
      if (service.likePost(id)) {
        System.out.println("Пост ID=" + id + " лайкнут!");
      } else {
        System.out.println("Ошибка: Пост с ID=" + id + " не найден.");
      }
    } catch (InputMismatchException e) {
      System.out.println("Неверный ID. Введите число.");
      scanner.nextLine();
    }
  }

  private void repostPost() {
    System.out.print("Введите ID поста, который хотите репостнуть: ");
    try {
      long id = scanner.nextLong();
      scanner.nextLine();
      if (service.repostPost(id, currentUser)) {
        System.out.println("Пост ID=" + id + " репостнут! Добавлен новый пост.");
      } else {
        System.out.println("Ошибка: Пост с ID=" + id + " не найден.");
      }
    } catch (InputMismatchException e) {
      System.out.println("Неверный ID. Введите число.");
      scanner.nextLine();
    }
  }

  private void showAllPosts() {
    System.out.println("\nВсе посты (от новых к старым):");
    List<Post> allPosts = service.getAllPosts();
    if (allPosts.isEmpty()) {
      System.out.println("Нет постов для отображения.");
      return;
    }
    allPosts.forEach(System.out::println);
  }

  private void showPopularPosts() {
    System.out.print("Введите количество популярных постов для отображения: ");
    try {
      int limit = scanner.nextInt();
      scanner.nextLine();

      System.out.printf("\nТоп %d популярных постов (по убыванию лайков):\n", limit);
      List<Post> popularPosts = service.getPopularPosts(limit);
      if (popularPosts.isEmpty()) {
        System.out.println("Нет постов для отображения.");
        return;
      }
      popularPosts.forEach(System.out::println);
    } catch (InputMismatchException e) {
      System.out.println("Неверное количество. Введите число.");
      scanner.nextLine();
    }
  }

  private void showMyPosts() {
    System.out.printf("\nПосты пользователя %s:\n", currentUser.getUsername());
    List<Post> userPosts = service.getUserPosts(currentUser);
    if (userPosts.isEmpty()) {
      System.out.println("У вас пока нет постов.");
      return;
    }
    userPosts.forEach(System.out::println);
  }

  public static void main(String[] args) {
    new TwitterConsoleApp().start();
  }
}
