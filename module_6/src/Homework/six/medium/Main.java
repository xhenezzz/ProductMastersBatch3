import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordCounter {

  public static void main(String[] args) {
    String filename = "C:\\ProductMaster\\ProductMastersBatch3\\module_6\\src\\Homework\\six\\medium\\words.txt";

    Map<String, Integer> wordFrequency = new HashMap<>();

    Set<String> uniqueWords = new HashSet<>();

    try {
      Scanner scanner = new Scanner(new File(filename));

      while (scanner.hasNext()) {
        String word = scanner.next()
                .toLowerCase()
                .replaceAll("[^a-zA-Zа-яА-ЯёЁ]+", "");

        if (!word.isEmpty()) {
          uniqueWords.add(word);

          wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
      }

      scanner.close();

      System.out.println("Общее количество уникальных слов: " + uniqueWords.size() + "\n");
      System.out.println("Частота встречаемости каждого слова:");

      for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
        System.out.println("  '" + entry.getKey() + "': " + entry.getValue());
      }

    } catch (FileNotFoundException e) {
      System.err.println("Ошибка: Файл '" + filename + "' не найден.");
      e.printStackTrace();
    }
  }
}
