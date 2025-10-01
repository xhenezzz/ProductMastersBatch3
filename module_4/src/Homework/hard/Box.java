package Homework.hard;

public class Box<T>{
    private T item;

    public Box(T item) {
        this.item = item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void showType() {
        if (item != null) {
            System.out.println("Тип объекта: " + item.getClass().getName());
        } else {
            System.out.println("Box пустой.");
        }
    }
}
