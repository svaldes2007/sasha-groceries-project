import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GroceryList extends Node{
    // attributes 
    private Node head;

    // constructors 

    public GroceryList(){
        head = null;
    }

    public GroceryList(Node head){
        this.head = head;
    }

    //methods 

    public void add( String data){
        Node incoming = new Node(data);
        //if the list is empty, set the new node’s next to the current head so it points to the existing list
        if (head == null){
            incoming.next = head;
        } else {
            //otherwise, ittorate through to the end of the list
            Node curr = head;
            while (curr.next != null){
                curr = curr.next;
            }
            //add inclong node to the end of the list
            curr.next = incoming;
        }
    }

    public void add( String data, int index){
        Node incoming = new Node(data);

        //if the list is empty, set the new node’s next to the current head so it points to the existing list
        if (head == null){
            incoming.next = head;
        } else if (index == 0){
            //if adding at zero, set the incoming data to the head
            head = incoming;
            incoming.next = head;
        } else if (index <= this.size() && index >= 0){
            //if vaild index, ittorate through to find the node before the index you want to add a node at
            Node curr = head;
            for (int i = 0; i < index-1; i++){
                if (curr.next != null){
                    curr = curr.next;
                }
            }
            // find the node which you want to incert 
            Node after = curr.next;
            //incert the incoming node after the one you want to be before it
            curr.next = incoming;
            //set the node after the one you incerted to be after (so you dont replace, you shift)
            curr.next.next = after;
        } 
        
    }

    public int size(){
        int count = 0;
        Node curr = head;
        //ittorate through and increment count
        while (curr != null){
            count++;
            curr = curr.getNext();
        }
        //return count
        return count;
    }

    public void remove(int idx){
        //check if index is valid
        if (head != null && idx <= this.size() && idx >= 0){
            //if removing first element, make the second element the head
            if (idx == 0){
                head = head.next; 
            } else {
                Node curr = head;
                for (int i = 0; i < idx-1; i++){
                    //otherwise, ittorate thru until you find the node before the one you want to remove
                    curr = curr.next;
                }
                //set previous element to next one -> skips middle one (leapfrog)
                curr.next = curr.next.next;
            }
        }
    }

    //helper method to check if an item is in the grocery list
    public boolean inList(String data){
        Node curr = head;

        while (curr != null){
            //if theres a match, return true
            if (curr.data.equals(data)){
                return true;
            }
            //ittorate through list of gorcery items
            curr = curr.next;
            //if not, return false
            return false;
        }
        //return false if list is empty
        return false;
    }
    
    public String toString(){
        String toRet = "";
        //if grocery list exists
        try {
            //call getGroceryList
            Map <String, Double> groceryList = this.getGroceryMap();

            //ittorate through + add items to toRet
            for (Map.Entry<String, Double> entry : groceryList.entrySet()) {
                String item = entry.getKey();
                double price = entry.getValue();

                toRet = toRet + item + " " + price + "\n";
            }

        } catch (FileNotFoundException e) {
            // handle error
            return "Error: grocery file not found.";
        }
        
        //return toRet
        return toRet;
    }

    public Map<String, Double> getGroceryMap() throws FileNotFoundException {
        //initialize map
        Map <String, Double> groceries = new HashMap<>();

        //access data
        File f = new File("/Users/svaldes/Desktop/cs stuff container/sasha-groceries-project/grocery-items.txt");
        Scanner s = new Scanner(f);

        //itterate thru file
        while (s.hasNextLine()){
            String [] tempList = s.nextLine().split("\"");
            //the first item in tempList is null becuase we're using " to seperate items but each item starts with a ", so im skipping it
            String item = tempList[1];
            double price = Double.parseDouble(tempList[2]);
            //if the item is in the grocery list, add it to the map
            if (this.inList(item)){
                groceries.put(item, price);
            }
        }
        s.close();
        return groceries;
    }

    // double getCost()

    
}

