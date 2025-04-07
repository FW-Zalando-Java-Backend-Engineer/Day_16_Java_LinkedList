import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        // Create LinkedList of Strings
        LinkedList<String> cities = new LinkedList<>();

        // Add elements
        cities.add("Buenos Aires");
        cities.add("Istanbul");

        // Add at First index
        cities.addFirst("Delhi");
        // Add at specific index
        cities.add(1,"Shanghai");
        // Add to the end
        cities.addLast("Prague");

       cities.add(0,"Mumbai");


        // Remove elements
        cities.remove(0); // removes "Mumbai"
        cities.removeFirst();  // removes "Delhi"
        cities.remove("Prague"); // removes "Prague"

        // Access elements
     //   System.out.println("First city: " + cities.getFirst());
     //   System.out.println("Last city: " + cities.getLast());

        // Loop through
//        for (String city : cities) {
//            System.out.println(city);
//        }

        // System.out.println(cities);

        // Queue Behavior
        LinkedList<String> tasks = new LinkedList<>();
        LinkedList<String> doneTasks = new LinkedList<>();

        tasks.offer("Task 1");  // enqueue
        tasks.offer("Task 2");
        tasks.offer("Task 3");
        tasks.offer("Task 4");

        doneTasks.offer(tasks.poll());



        System.out.println("Tasks to do: "+tasks);
        System.out.println("Done : "+doneTasks);









    }


}
