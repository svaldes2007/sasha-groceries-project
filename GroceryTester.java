import java.io.FileNotFoundException;

public class GroceryTester {
    public static void main (String [] args) throws FileNotFoundException {
        Node head = new Node("strawberries");
        GroceryList valdesFamily = new GroceryList(head);
        valdesFamily.add("cheese");
        valdesFamily.add("eggs");

        valdesFamily.getGroceryMap();

    
    }
}
