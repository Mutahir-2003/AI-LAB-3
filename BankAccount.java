package lab6;

class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    // Synchronized method to handle withdrawal
    public synchronized boolean withdraw(int amount) {
        if (amount <= balance) {
            System.out.println(Thread.currentThread().getName() + " is trying to withdraw: " + amount);
            try {
                // Simulating time taken for withdrawal
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " successfully withdrew: " + amount);
            System.out.println("Remaining balance: " + balance);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + " attempted to withdraw " + amount + " but insufficient funds.");
            return false;
        }
    }

    public int getBalance() {
        return balance;
    }


public class BankSystem {
    public static void main(String[] args) {
        // Initial balance of the joint bank account
        BankAccount account = new BankAccount(50000);

        // User A wants to withdraw 45,000
        Runnable userA = () -> {
            account.withdraw(45000);
        };

        // User B wants to withdraw 20,000
        Runnable userB = () -> {
            account.withdraw(20000);
        };

        // Create two threads for user A and user B
        Thread threadA = new Thread(userA, "User A");
        Thread threadB = new Thread(userB, "User B");

        // Start the threads
        threadA.start();
        threadB.start();

        // Wait for both threads to complete
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final balance after both withdrawals
        System.out.println("Final balance: " + account.getBalance());
    }
}
}
