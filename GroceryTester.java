import java.io.FileNotFoundException;

public class GroceryTester {
    public static void main (String [] args) throws FileNotFoundException {
        //test one of node constructors
        Node head = new Node("strawberries");

        //test GroceryList constructors
        GroceryList valdesFamily = new GroceryList(head);
        GroceryList pomboFamily = new GroceryList();

        //test add and remove methods
        valdesFamily.add("cheese");
        valdesFamily.add("eggs");
        valdesFamily.add("chicken", 1);

        pomboFamily.add("ground beef");
        pomboFamily.add("bread");
        pomboFamily.add("olive oil");
        pomboFamily.add("mustard", 2);
        pomboFamily.remove(1);

        // test toString 
        System.out.println("Valdes Family gorcery list:");
        System.out.println(valdesFamily.toString());
        System.out.println("Pombo Family gorcery list:");
        System.out.println(pomboFamily.toString());
        
        //test size
        System.out.println("Valdes size: " + valdesFamily.size());

        //test cost
        System.out.println("Pombo cost: " + pomboFamily.getCost());

        //test clear
        System.out.println();
        valdesFamily.clear();
        System.out.println("Valdes Family gorcery list:");
        System.out.println(valdesFamily.toString());

        //test mostExpensive 
        System.out.println();
        System.out.println("Pombo's most expensve item: " + pomboFamily.mostExpensive());
        
    
    }
}
