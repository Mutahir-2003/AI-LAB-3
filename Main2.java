package Lab4Task1;
public class Main2 {
    public static void main(String[] args) {
        // Create instances of RollNumberTable and DOBTable
        RollNumberTable rollNumberTable = new RollNumberTable();
        DOBTable dobTable = new DOBTable();
        
        // Start both threads simultaneously
        rollNumberTable.start();
        dobTable.start();
    }
}


