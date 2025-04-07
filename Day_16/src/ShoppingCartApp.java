import java.util.LinkedList;
import java.util.Scanner;

public class ShoppingCartApp {
    public static void main(String[] args) {
        LinkedList<String> cart = new LinkedList<>(); // Main shopping cart list
        LinkedList<String> undoStack = new LinkedList<>();      // Stack for undo operations

        Scanner scanner = new Scanner(System.in);               // Scanner for user input
        boolean running = true;                                 // Control loop variable
        while (running) {
            // Display menu options to the user
            System.out.println("\n=== Shopping Cart Menu ===");
            System.out.println("1. Add item to end (offer)");
            System.out.println("2. Add item to front (offerFirst)");
            System.out.println("3. Force add to end (add)");
            System.out.println("4. View first item (peek)");
            System.out.println("5. View first item (element)");
            System.out.println("6. Remove first item (poll)");
            System.out.println("7. Force remove first item (remove)");
            System.out.println("8. Undo last add (pop)");
            System.out.println("9. Redo last undo (push)");
            System.out.println("10. Remove last item (pollLast)");
            System.out.println("11. Show cart items");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());  // Read user input as int
            switch (choice) {
                case 1 -> {
                    // Add item safely to end using offer()
                    System.out.print("Enter item to add (offer): ");
                    String item = scanner.nextLine();
                    if (cart.offer(item)) {                     // Returns false if capacity limits were enforced
                        System.out.println("Item added to end.");
                        undoStack.push("UNDO:" + item);         // Save action for undo
                    } else {
                        System.out.println("Failed to add item.");
                    }
                }
                case 2 -> {
                    // Add item to front using offerFirst()
                    System.out.print("Enter item to add to front (offerFirst): ");
                    String item = scanner.nextLine();
                    cart.offerFirst(item);
                    System.out.println("Item added to front.");
                    undoStack.push("UNDO_FIRST:" + item);       // Save action for undo
                }
                case 3 -> {
                    // Forcefully add item to end using add()
                    System.out.print("Enter item to force add (add): ");
                    String item = scanner.nextLine();
                    try {
                        cart.add(item);   // technically is stricter
                        System.out.println("Item forcefully added.");
                        undoStack.push("UNDO:" + item);         // Save for undo
                    } catch (IllegalStateException e) {
                        System.out.println("Cart is full!");
                    }
                }
                case 4 -> {
                    // Safely peek at first item (null if empty)
                    String item = cart.peek();
                    System.out.println("First item (peek): " + (item != null ? item : "Cart is empty"));
                }
                case 5 -> {
                    // Forcefully peek at first item (throws exception if empty)
                    try {
                        System.out.println("First item (element): " + cart.element());
                    } catch (Exception e) {
                        System.out.println("Cart is empty!");
                    }
                }
                case 6 -> {
                    // Safely remove first item using poll()
                    String removed = cart.poll();
                    System.out.println("Removed item (poll): " + (removed != null ? removed : "Nothing to remove"));
                }
                case 7 -> {
                    // Forcefully remove first item using remove()
                    try {
                        String removed = cart.remove();         // Throws NoSuchElementException if empty
                        System.out.println("Force removed item: " + removed);
                    } catch (Exception e) {
                        System.out.println("Cart is empty!");
                    }
                }
                case 8 -> {
                    // Undo last addition by popping undoStack
                    try {
                        String undone = undoStack.pop(); // pop(): removes and returns the first element of this list.
                        if (undone.startsWith("UNDO:")) {
                            cart.pollLast();                   // Remove last item added to end
                            System.out.println("Undo last add.");
                        } else if (undone.startsWith("UNDO_FIRST:")) {
                            cart.pollFirst();                  // Remove first item added to front
                            System.out.println("Undo add to front.");
                        }
                    } catch (Exception e) {
                        System.out.println("Nothing to undo.");
                    }
                }
                case 9 -> {
                    // Redo add by pushing item to front (LIFO-style)
                    System.out.print("Enter item to redo (push): ");
                    String item = scanner.nextLine();
                    cart.push(item);                            // Equivalent to addFirst()
                    System.out.println("Item pushed to front.");
                }
                case 10 -> {
                    // Safely remove last item using pollLast()
                    String removed = cart.pollLast();
                    System.out.println("Removed last item: " + (removed != null ? removed : "Cart is empty"));
                }
                case 11 -> {
                    // Show all items in cart
                    System.out.println("Cart items: " + cart);
                }
                case 0 -> {
                    // Exit the loop and program
                    System.out.println("Thanks for shopping!");
                    running = false;
                }
                default -> System.out.println("Invalid option."); // Catch invalid inputs
            }
            }

        // Clean up scanner resource
        scanner.close();
        }

    }

