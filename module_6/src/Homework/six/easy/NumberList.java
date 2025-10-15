
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberList {
    private List<Integer> numbers;

    public NumberList(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int findMin() {
        return Collections.min(numbers);
    }

    public int findMax() {
        return Collections.max(numbers);
    }

    public List<Integer> sortAscending() {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        return sorted;
    }

    public List<Integer> sortDescending() {
        List<Integer> sorted = new ArrayList<>(numbers);
        sorted.sort(Collections.reverseOrder());
        return sorted;
    }

    public boolean containsElement(int value) {
        return numbers.contains(value);
    }

    public List<Integer> filterGreaterThan(int value) {
        List<Integer> filtered = new ArrayList<>();
        for (int num : numbers) {
            if (num > value) {
                filtered.add(num);
            }
        }
        return filtered;
    }

    public int sumAll() {
        int sum = 0;
        for (int num : numbers) sum += num;
        return sum;
    }

    public static void main(String[] args) {
        NumberList list = new NumberList(Arrays.asList(3, 7, 1, 9, 4));

        System.out.println("Min: " + list.findMin());
        System.out.println("Max: " + list.findMax());
        System.out.println("Sort ↑: " + list.sortAscending());
        System.out.println("Sort ↓: " + list.sortDescending());
        System.out.println("Contains 7: " + list.containsElement(7));
        System.out.println("Filter > 4: " + list.filterGreaterThan(4));
        System.out.println("Sum: " + list.sumAll());
    }
}