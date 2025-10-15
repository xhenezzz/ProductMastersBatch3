package Homework.six.hard;

import java.util.*;

public class SafeList<T> implements List<T> {

    private final List<T> list = new ArrayList<>();

    @Override
    public boolean add(T element) {
        if (element == null || list.contains(element)) {
            return false;
        }
        return list.add(element);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <E> E[] toArray(E[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T element : c) {
            if (element != null && !list.contains(element)) {
                list.add(element);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean modified = false;
        int currentIndex = index;
        for (T element : c) {
            if (element != null && !list.contains(element)) {
                list.add(currentIndex++, element);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= list.size() || element == null || list.contains(element)) {
            return null;
        }
        return list.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        if (element != null && !list.contains(element) && index >= 0 && index <= list.size()) {
            list.add(index, element);
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > list.size() || fromIndex > toIndex) {
            return new ArrayList<>();
        }
        return list.subList(fromIndex, toIndex);
    }
}
