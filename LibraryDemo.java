package Threads;

import java.util.ArrayList;
import java.util.List;

public class LibraryDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Member> members = new ArrayList<>();
        SemaphoreLibrary library = new SemaphoreLibrary(10);
        for(int i = 0; i<5; i++)
            members.add(new Member("M"+i,library));
        for(Member member:members)
            member.start();

        for (Member member:members)
            member.join();

        System.out.println("Successfully !");
    }
}

class Member extends Thread{

    String name;
    SemaphoreLibrary library;

    public Member(String name, SemaphoreLibrary library){
        this.name = name;
        this.library = library;
    }

    @Override
    public void run() {
        for(int i = 0; i<3; i++){
            System.out.println("Member "+i+"returns the book");
            try {
                library.returnBook("Book "+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i<3; i++){
            System.out.println("Member "+i +"borrows a book");
            try {
                library.borrowBook();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
