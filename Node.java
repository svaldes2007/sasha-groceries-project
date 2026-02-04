public class Node {
    //attributes
    protected String data;
    protected Node next;

    //constructors
    public Node (){
        data = null;
        next = null;
    }

    public Node (String data){
        this.data = data;
    }

    public Node (String data, Node next){
        this.data = data;
        this.next = next;
    }

    public String getData(){
        return this.data;
    }

    public Node getNext(){
        return this.next;
    }
}