package Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreLibrary {
    List<String> books = new ArrayList<>();
    int capacity;
    Semaphore coordinator = new Semaphore(1);
    Semaphore returnBookSemaphore = new Semaphore(10);
    Semaphore borrowBookSemaphore = new Semaphore(10);

    public SemaphoreLibrary(int capacity){
        this.capacity = capacity;
    }

    public  void returnBook(String book) throws InterruptedException {

        returnBookSemaphore.acquire();//1 2 3 4 5 6 7  8 9 10

        coordinator.acquire();
        while (books.size() == capacity) {
            coordinator.release();
            Thread.sleep(2000);
            coordinator.acquire();
        }
        books.add(book);
        coordinator.release();
        borrowBookSemaphore.release();


    }



    public  String borrowBook() throws InterruptedException {
        //first book
        String b = "";
        borrowBookSemaphore.acquire();

        coordinator.acquire();
        while (books.size() == 0) {
            coordinator.release();
            Thread.sleep(2000);
            coordinator.acquire();
        }
        b = books.remove(0);
        coordinator.release();
        returnBookSemaphore.release();
        return b;
    }
}

