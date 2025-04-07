import java.util.ArrayList;
import java.util.LinkedList;

public class ListInsertionPerformanceTest {
    public static void main(String[] args) {

        final int iterations = 50_000; // final means value is constant

        /*
         TIP: They all compile to the exact same integer.
         The underscores are ignored by the compiler, but super helpful for humans — like commas in regular numbers.
         These are all valid and equal:
                int a = 50000;
                int b = 50_000;
                int c = 5_0_0_0_0;
        */

        LinkedList<Integer> linkedList = new LinkedList<>();

        ArrayList<Integer> arrayList = new ArrayList<>();

        // Measure time for ArrayList
        long arrayStart = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(0, i);  // insert at the beginning
        }
        long arrayEnd = System.nanoTime();


        // Measure time for LinkedList
        long linkedStart = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(0, i);  // insert at the beginning
        }
        long linkedEnd = System.nanoTime();


        // Output results
        System.out.println("ArrayList add(0, value): " + (arrayEnd - arrayStart) / 1_000_000.0 + " ms");
        System.out.println("LinkedList add(0, value): " + (linkedEnd - linkedStart) / 1_000_000.0 + " ms");


    }
}
