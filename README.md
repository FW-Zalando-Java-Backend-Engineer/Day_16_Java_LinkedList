# **📘 Day-16: Java Queue & LinkedList**  
Welcome to **Day-16** of our Java learning journey! Today, we built a **command-line Shopping Cart tool** using Java's `Queue` and `LinkedList` APIs. We explored **FIFO and LIFO operations**, mastered all relevant queue methods, and saw how **LinkedList** shines in building real-world tools.

---

## **📌 Lesson Structure**

### **1️⃣ Why LinkedList is the Perfect Fit**
- Implements **both** `Queue` and `Deque` interfaces
- Allows **O(1)** insertion and deletion from **both ends**
- Supports **stack and queue behavior** (LIFO & FIFO)
- Ideal for carts, undo-redo stacks, and schedulers

---

## 🧾 **Java Queue & LinkedList Method Comparison Table**

| **Method**       | **Action**                    | **Where**     | **Returns**            | **Throws Exception**        | **When to Use**                                |
|------------------|-------------------------------|---------------|-------------------------|------------------------------|------------------------------------------------|
| `offer(E e)`     | Add element safely            | Tail (end)    | `true`/`false`          | No                           | Safely add items to the end of queue/cart      |
| `add(E e)`       | Add element forcibly          | Tail (end)    | `true`                  | Yes, if full (IllegalState)  | When you're sure the queue can take more       |
| `offerFirst(E e)`| Add element to front safely   | Head (front)  | `true`/`false`          | No                           | For priority/urgent items                      |
| `peek()`         | View first element safely     | Head (front)  | Element / `null`        | No                           | See what’s next in cart, without removing      |
| `element()`      | View first element forcibly   | Head (front)  | Element                 | Yes, if empty                | When you expect the cart to not be empty       |
| `poll()`         | Remove first element safely   | Head (front)  | Element / `null`        | No                           | Normal dequeue from front                      |
| `remove()`       | Remove first element forcibly | Head (front)  | Element                 | Yes, if empty                | When you must remove and expect non-empty cart |
| `pollLast()`     | Remove last element safely    | Tail (end)    | Element / `null`        | No                           | Remove last item (like cancel last add)        |
| `push(E e)`      | Push to front (LIFO)          | Head (front)  | `void`                  | No                           | For stack/redo-like behavior                   |
| `pop()`          | Pop from front (LIFO)         | Head (front)  | Element                 | Yes, if empty                | Undo last front-pushed item                    |

---

## 🎯 Tips to Choose Methods

| **Goal**                 | **Recommended Methods**                    |
|--------------------------|--------------------------------------------|
| Safe Insert              | `offer()`, `offerFirst()`                 |
| Forceful Insert          | `add()`                                    |
| Peek Safely              | `peek()`                                   |
| Peek Forcefully          | `element()`                                |
| Safe Remove              | `poll()`, `pollLast()`                     |
| Force Remove             | `remove()`                                 |
| Stack / Undo-Redo        | `push()`, `pop()`                          |

---


## **📦 Features of Our ShoppingCart CLI**

### ✅ Uses Every Queue/LinkedList Method:
| Method        | Purpose                          |
|---------------|----------------------------------|
| `offer()`     | Safe insert to end (tail)        |
| `add()`       | Force insert to end (throws if full) |
| `offerFirst()`| Insert to front (head)           |
| `poll()`      | Safe remove from front           |
| `remove()`    | Force remove from front (throws if empty) |
| `peek()`      | View front safely (returns null if empty) |
| `element()`   | View front forcefully (throws if empty) |
| `pollLast()`  | Remove last item safely          |
| `push()`      | Stack-like push to front         |
| `pop()`       | Stack-like pop from front        |

---

## **🖥️ Full Code: ShoppingCartApp.java**

```java
import java.util.LinkedList;
import java.util.Scanner;

public class ShoppingCartApp {
    public static void main(String[] args) {
        LinkedList<String> cart = new LinkedList<>();
        LinkedList<String> undoStack = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
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
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter item to add (offer): ");
                    String item = scanner.nextLine();
                    if (cart.offer(item)) {
                        System.out.println("Item added to end.");
                        undoStack.push("UNDO:" + item);
                    } else {
                        System.out.println("Failed to add item.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter item to add to front (offerFirst): ");
                    String item = scanner.nextLine();
                    cart.offerFirst(item);
                    System.out.println("Item added to front.");
                    undoStack.push("UNDO_FIRST:" + item);
                }
                case 3 -> {
                    System.out.print("Enter item to force add (add): ");
                    String item = scanner.nextLine();
                    try {
                        cart.add(item);
                        System.out.println("Item forcefully added.");
                        undoStack.push("UNDO:" + item);
                    } catch (IllegalStateException e) {
                        System.out.println("Cart is full!");
                    }
                }
                case 4 -> {
                    String item = cart.peek();
                    System.out.println("First item (peek): " + (item != null ? item : "Cart is empty"));
                }
                case 5 -> {
                    try {
                        System.out.println("First item (element): " + cart.element());
                    } catch (Exception e) {
                        System.out.println("Cart is empty!");
                    }
                }
                case 6 -> {
                    String removed = cart.poll();
                    System.out.println("Removed item (poll): " + (removed != null ? removed : "Nothing to remove"));
                }
                case 7 -> {
                    try {
                        String removed = cart.remove();
                        System.out.println("Force removed item: " + removed);
                    } catch (Exception e) {
                        System.out.println("Cart is empty!");
                    }
                }
                case 8 -> {
                    try {
                        String undone = undoStack.pop();
                        if (undone.startsWith("UNDO:")) {
                            cart.pollLast();
                            System.out.println("Undo last add.");
                        } else if (undone.startsWith("UNDO_FIRST:")) {
                            cart.pollFirst();
                            System.out.println("Undo add to front.");
                        }
                    } catch (Exception e) {
                        System.out.println("Nothing to undo.");
                    }
                }
                case 9 -> {
                    System.out.print("Enter item to redo (push): ");
                    String item = scanner.nextLine();
                    cart.push(item);
                    System.out.println("Item pushed to front.");
                }
                case 10 -> {
                    String removed = cart.pollLast();
                    System.out.println("Removed last item: " + (removed != null ? removed : "Cart is empty"));
                }
                case 11 -> System.out.println("Cart items: " + cart);
                case 0 -> {
                    System.out.println("Thanks for shopping!");
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}
```

---

## **🧪 Concept Demonstrations**

### ✅ Adding & Viewing
```java
cart.offer("Milk");         // Add safely
cart.offerFirst("Urgent");  // Add to front
cart.peek();                // View first item safely
cart.element();             // View first with error if empty
```

### ✅ Removing Items
```java
cart.poll();        // Safe remove from front
cart.remove();      // Forceful remove
cart.pollLast();    // Safe remove from end
```

### ✅ Undo/Redo
```java
undoStack.push("UNDO:Milk"); // Track undo
cart.pop();                  // Undo: remove from front
cart.push("Milk");           // Redo: push to front
```

---

## **🎯 Hands-on Exercises**
✅ Implement max capacity limit and block `add()` when full  
✅ Replace `LinkedList` with `ArrayDeque` and compare behavior  
✅ Add history logging for all operations  
✅ Refactor into a real `ShoppingCart` class with unit tests

---

## **📚 Additional Resources**
- [Baeldung: Java Queue Interface](https://www.baeldung.com/java-queue)
- [Java Docs: LinkedList](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html)
- [GeeksForGeeks: Queue in Java](https://www.geeksforgeeks.org/queue-interface-java/)
- [Java Deque Tutorial](https://www.baeldung.com/java-deque)

---

## **🎥 Video Lesson Recording**
📺 [Video Lesson Recording](_Will be Added Later_)

---


## **🖥️ Live Coding**
📂 [Live Coding Example](https://github.com/FW-Zalando-Java-Backend-Engineer/Day-16_ShoppingCart_Queue_LinkedList/tree/main/Day_16)

---

🚀 **Awesome job today! You now know how to build real-world tools using Queue, Stack, and LinkedList like a true Java backend engineer! See you tomorrow for more coding magic!** 💡
