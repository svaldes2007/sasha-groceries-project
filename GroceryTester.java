import java.io.FileNotFoundException;

public class GroceryTester {
    public static void main (String [] args) throws FileNotFoundException {
        //test one of node constructors
        Node head = new Node("strawberries");

        //test GroceryList constructors
        GroceryList pomboFamily = new GroceryList(head);
        GroceryList valdesFamily = new GroceryList();

        //test add and remove methods
        pomboFamily.add("cheese");
        pomboFamily.add("eggs");
        pomboFamily.add("chicken", 1);

        valdesFamily.add("ground beef");
        valdesFamily.add("bread");
        valdesFamily.add("olive oil");
        valdesFamily.add("mustard", 2);
        valdesFamily.remove(1);

        // test toString 
        System.out.println("Pombo Family gorcery list:");
        System.out.println(pomboFamily.toString());
        System.out.println("Valdes Family gorcery list:");
        System.out.println(valdesFamily.toString());
        
        //test size
        System.out.println("Pombo list size: " + pomboFamily.size());

        //test cost
        System.out.println("Valdes cost: " + valdesFamily.getCost());

        //test mostExpensive 
        System.out.println();
        System.out.println("Valdes's most expensive item: " + valdesFamily.removeMostExpensive());
        System.out.println();
        System.out.println("Valdes Family gorcery list:");
        System.out.println(valdesFamily.toString());
        System.out.println("New Valdes cost: " + valdesFamily.getCost());
    
    }
}
