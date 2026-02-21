public class Printer {
    Printer() {}

    public void printStudentCreated(StudentRecord rec, int count) {
        System.out.println("OK: created student " + rec.id);
        System.out.println("Saved. Total students: " + count);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}
