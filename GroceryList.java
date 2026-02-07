import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.LinkedHashMap;
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

    public void add(String data){
        Node incoming = new Node(data);
        //if the list is empty, set head to the new node
        if (head == null){
            head = incoming;
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

    public void add(String data, int index){
        Node incoming = new Node(data);

        //if the list is empty, set head to the new node
        if (head == null){
            head = incoming;
        } else if (index == 0){
            //if adding at zero, set the incoming data to the head
            incoming.next = head;
            head = incoming; 
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
    
    public String toString(){
        String toRet = "";
        //if grocery list exists
        try {
            //call getGroceryList
            Map <String, Double> groceryList = this.getGroceryMap();

            //if the list is empty, print this message
            if (groceryList.size() == 0){
                return "Nothing on your list.";
            }
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
        //using LinkedHashMap to preserve order
        //initialize map to hold groceries
        Map <String, Double> groceries = new LinkedHashMap<>();

        //access data
        File f = new File("/Users/svaldes/Desktop/cs stuff container/sasha-groceries-project/grocery-items.txt");
        Scanner s = new Scanner(f);

        //itterate through file and make a local map var
        Map<String, Double> fileMap = new LinkedHashMap<>();
        while (s.hasNextLine()) {
            String[] tempList = s.nextLine().split("\"");
            String item = tempList[1];
            double price = Double.parseDouble(tempList[2]);
            fileMap.put(item, price);
        }
        s.close();

        //itterate thru gorceryList
        Node curr = head;
        while (curr != null) {
            if (fileMap.containsKey(curr.getData())) {
                groceries.put(curr.getData(), fileMap.get(curr.getData()));
            }
            curr = curr.next;
        }

        //return grocereies map
        return groceries;
    }

    public double getCost(){
        //stores total cost of trip
        double totalCost = 0;

        try {
            //call getGroceryList
            Map <String, Double> groceryList = this.getGroceryMap();

            //ittorate through and add prices to totalCost
            for (Map.Entry<String, Double> entry : groceryList.entrySet()) {
                double price = entry.getValue();
                totalCost += price;
            }

        } catch (FileNotFoundException e) {
            // handle error
            System.out.println("Error: grocery file not found.");
            return -1;
        }

        return totalCost;
    }

    //pethod clears the list
    public void clear() {
        head = null;
    }

    //gets most expensive item
    public String mostExpensive() throws FileNotFoundException {
        //use getGroceryMap to get map of all groceries
        Map <String, Double> groceries = getGroceryMap();
        //string to store name of most expensie item
        String expensive = "";
        double maxPrice = 0;

        //ittorate through map
        for (Map.Entry<String, Double> entry : groceries.entrySet()) {
            //if an items price is higher than maxPrice, update maxPrice and set expensive to the name of the item
            if (entry.getValue() > maxPrice) {
                maxPrice = entry.getValue();
                expensive = entry.getKey();
            }
        }
        //return string with the name of the item
        return expensive;
    }
        
}

