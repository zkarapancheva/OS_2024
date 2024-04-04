package Threads;

import java.util.ArrayList;
import java.util.List;

public class SyncLibrary {
    List<String> books = new ArrayList<>();
    int capacity;

    public SyncLibrary(int capacity){
        this.capacity = capacity;
    }

    public synchronized void returnBook(String book) throws InterruptedException {
        while(books.size() == capacity)
            wait();

        books.add(book);
        notifyAll();

    }

    public synchronized String borrowBook() throws InterruptedException {
        //first book
        while (books.size() == 0)
            wait();

        String b = books.remove(0);
        notifyAll();
        return b;
    }
}

