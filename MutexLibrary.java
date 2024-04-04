package Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexLibrary {

    List<String> books = new ArrayList<>();
    int capacity;
    static Lock lock = new ReentrantLock();

    public MutexLibrary(int capacity){
        this.capacity = capacity;
    }

    public  void returnBook(String book) throws InterruptedException {
        while(true) {
            lock.lock();
            if (books.size() < capacity) {
                books.add(book);
                lock.unlock();
                break;
            }
            lock.unlock();
        }

    }

    public  String borrowBook() throws InterruptedException {
        //first book
        String b = "";
        while(true) {
            lock.lock();
            if (books.size() > 0) {
                b = books.remove(0);
                lock.unlock();
                break;
            }
            lock.unlock();
        }
        return b;
    }
}

