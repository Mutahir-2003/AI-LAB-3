package Lab4Task1;
class Main2 extends Thread { 
    public void run() { 
        System.out.println("task one"); 
    } 
    
    public static void main(String args[]) { 
        Main2 t1 = new Main2();  // Create instance of Main2
        Main2 t2 = new Main2();  // Create instance of Main2
        Main2 t3 = new Main2();  // Create instance of Main2
        
        t1.start();  // Start the first thread
        t2.start();  // Start the second thread
        t3.start();  // Start the third thread
    } 
}

