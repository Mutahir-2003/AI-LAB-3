package lab6;

class Printer {
    private int totalPages;

    // Constructor to initialize the printer tray with pages
    public Printer(int totalPages) {
        this.totalPages = totalPages;
    }

    // Synchronized method to print pages
    public synchronized void printJob(int pages) throws InterruptedException {
        while (totalPages < pages) {
            System.out.println("Not enough pages. Waiting for more pages to be added...");
            wait(); // Wait until there are enough pages to print the job
        }

        // If there are enough pages, proceed with the printing
        totalPages -= pages;
        System.out.println("Printing " + pages + " pages...");
        System.out.println("Remaining pages in tray: " + totalPages);
    }

    // Synchronized method to add pages to the printer tray
    public synchronized void addPages(int pages) {
        totalPages += pages;
        System.out.println(pages + " pages added to the printer tray.");
        System.out.println("Current pages in tray: " + totalPages);
        notify(); // Notify the waiting thread (PrintJobThread)
    }

    public int getTotalPages() {
        return totalPages;
    }
}

class RemainingPagesThread extends Thread {
    private Printer printer;
    private volatile boolean running = true;  // Flag to stop the thread gracefully

    public RemainingPagesThread(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        // Continuously add pages to the printer tray (simulating page refilling)
        try {
            while (running) {
                Thread.sleep(3000); // Simulate the time to add pages
                int pagesToAdd = (int) (Math.random() * 10) + 1; // Random number of pages to add (1-10)
                printer.addPages(pagesToAdd);
            }
        } catch (InterruptedException e) {
            System.out.println("RemainingPagesThread interrupted. Exiting gracefully...");
        }
    }

    // Method to stop the thread
    public void stopThread() {
        running = false;
        this.interrupt();  // Interrupt sleep if it is in progress
    }
}

class PrintJobThread extends Thread {
    private Printer printer;
    private int pagesToPrint;

    public PrintJobThread(Printer printer, int pagesToPrint) {
        this.printer = printer;
        this.pagesToPrint = pagesToPrint;
    }

    @Override
    public void run() {
        // Attempt to print the pages
        try {
            printer.printJob(pagesToPrint);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class PrinterJobSimulation {
    public static void main(String[] args) {
        // Create a printer with an initial amount of pages (e.g., 10 pages)
        Printer printer = new Printer(10);

        // Create and start the RemainingPagesThread (for adding pages)
        RemainingPagesThread remainingPagesThread = new RemainingPagesThread(printer);
        remainingPagesThread.start();

        // Create and start PrintJobThreads with different print jobs (e.g., printing 15 pages)
        PrintJobThread printJobThread1 = new PrintJobThread(printer, 15);
        printJobThread1.start();

        PrintJobThread printJobThread2 = new PrintJobThread(printer, 5);
        printJobThread2.start();

        // Main thread waits for all threads to finish
        try {
            printJobThread1.join();
            printJobThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Once the print jobs are done, stop the RemainingPagesThread gracefully
        remainingPagesThread.stopThread();
    }
}
