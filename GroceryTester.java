import java.io.FileNotFoundException;

public class GroceryTester {
    public static void main (String [] args) throws FileNotFoundException {
        Node head = new Node("strawberries");
        GroceryList valdesFamily = new GroceryList(head);
        valdesFamily.add("cheese");
        valdesFamily.add("eggs");
        System.out.println(valdesFamily);
        valdesFamily.getGroceryMap();
        //System.out.println(valdesFamily);

    
    }
}
