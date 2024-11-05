package Lab4Task1;

class RollNumberThread extends Thread {
    // Array of student roll numbers
    String[] rollNumbers = {
        "2019-SE-095", "2020-SE-102", "2021-SE-105", "2022-SE-110"
    };

    @Override
    public void run() {
        System.out.println("Roll Number Table:");
        // Print roll numbers
        for (String rollNumber : rollNumbers) {
            System.out.println(rollNumber);
            try {
                Thread.sleep(500);  // Simulate time taken for processing each item
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class DateOfBirthThread extends Thread {
    // Array of dates of birth
    String[] datesOfBirth = {
        "08-August", "15-July", "22-June", "10-May"
    };

    @Override
    public void run() {
        System.out.println("\nDate of Birth Table:");
        // Print dates of birth
        for (String dob : datesOfBirth) {
            System.out.println(dob);
            try {
                Thread.sleep(500);  // Simulate time taken for processing each item
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class RollNumberTable {
    public static void main(String[] args) {
        // Create instances of both threads
        RollNumberThread rollNumberThread = new RollNumberThread();
        DateOfBirthThread dobThread = new DateOfBirthThread();
        
        // Start the threads
        rollNumberThread.start();
        dobThread.start();
        
        try {
            // Optionally, wait for threads to finish before terminating the main method
            rollNumberThread.join();
            dobThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
