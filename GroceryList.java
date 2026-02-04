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
        if (head == null){
            head = incoming;
        } else {
            Node curr = head;
            while (curr.next != null){
                curr = curr.next;
            }
            curr.next = incoming;
        }
    }

    public void add( String data, int index){
        Node incoming = new Node(data);
        if (head == null){
            incoming.next = head;
        } else if (index == 0){
            head = incoming;
            incoming.next = head;
        } else if (index <= this.size() && index >= 0){
            Node curr = head;
            for (int i = 0; i < index-1; i++){
                if (curr.next != null){
                    curr = curr.next;
                }
            }
            Node after = curr.next;
            curr.next = incoming;
            curr.next.next = after;
        } 
        
    }

    public int size(){
        int count = 0;
        Node curr = head;
        while (curr != null){
            count++;
            curr = curr.getNext();
        }
        return count;
    }

    public void remove(int idx){
        if (head != null && idx <= this.size() && idx >= 0){
            if (idx == 0){
                head = head.next;
            } else {
                Node curr = head;
                for (int i = 0; i < idx-1; i++){
                    curr = curr.next;
                }
                //set previous element to next one -> skips middle one (leapfrog)
                curr.next = curr.next.next;
            }
        }
    }

    public int indexOf(String data){
        Node curr = head;
        int index = 0;

        while (curr != null){
            if (curr.getData().equals(data)){
                return index;
            }
            index++;
            curr = curr.next;
        }
        return -1;
    }

    public String toString(){
        // String toRet = "";
        // Node curr = head;
        // while (curr != null){
        //     toRet = toRet + curr.getData() + " ";
        //     curr = curr.getNext();
        // }
        // return toRet;
        return "";
    }

    public Map<String, Double> getGroceryMap()  throws FileNotFoundException {
        Map <String, Double> groceries = new HashMap<>();
        File f = new File("/Users/svaldes/Desktop/cs stuff container/sasha-groceries-project/grocery-items.txt");
        Scanner s = new Scanner(f);

        while (s.hasNextLine()){
            String [] n = s.nextLine().split(" ");
            System.out.print(n);
            //String item = n[0];
            //double price = Double.parseFloat(n[1]);
            groceries.put("Sasha", 0.0);
        }

        s.close();
        return groceries;
    }

    // double getCost()

    
}

