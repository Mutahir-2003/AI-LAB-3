package Lab4Task1;
class RollNumberTable extends Thread {
    public void run() {
        // Printing Roll Number table
        System.out.println("Roll Number Table:");
        System.out.println("-------------------------------------------------");
        System.out.println("Roll Number | Student Name");
        System.out.println("-------------------------------------------------");
        System.out.println("2019-SE-092 | Abdullah Khan");
        System.out.println("2019-SE-093 | Ahemd");
        System.out.println("2019-SE-094 | Zia");
        System.out.println("2019-SE-095 | yasir");
        System.out.println("-------------------------------------------------");
    }
}

class DOBTable extends Thread {
    public void run() {
        // Printing Date of Birth table
        System.out.println("Date of Birth Table:");
        System.out.println("-------------------------------------------------");
        System.out.println("Roll Number | Date of Birth");
        System.out.println("-------------------------------------------------");
        System.out.println("2019-SE-092 | 05-April");
        System.out.println("2019-SE-093 | 12-May");
        System.out.println("2019-SE-094 | 23-June");
        System.out.println("2019-SE-095 | 15-December");
        System.out.println("-------------------------------------------------");
    }
}
