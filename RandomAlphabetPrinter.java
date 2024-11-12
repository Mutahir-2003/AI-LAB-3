package synchronization;
import java.util.concurrent.TimeUnit;
public class RandomAlphabetPrinter {
    
    public static void main(String[] args) {
        // Create a thread to print alphabets
        Thread alphabetThread = new Thread(new Runnable() {
            public void run() {
                // Loop through the English alphabets A-Z
                for (char ch = 'A'; ch <= 'Z'; ch++) {
                    // Print the current alphabet
                    System.out.print(ch + " ");
                    
                    try {
                        // Random delay between 100 to 500 milliseconds (can be adjusted)
                        long randomDelay = (long) (Math.random() * 400 + 100);
                        
                        // Sleep for a random time to create fluctuation in the printing speed
                        TimeUnit.MILLISECONDS.sleep(randomDelay);
                    } catch (InterruptedException e) {
                        // Handle the exception if sleep is interrupted
                        e.printStackTrace();
                    }
                }
            }
        });
        
        // Start the thread to begin printing
        alphabetThread.start();
        
        // Optionally, we can join to wait for the thread to finish
        try {
            alphabetThread.join();  // This ensures the main thread waits for the alphabetThread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
