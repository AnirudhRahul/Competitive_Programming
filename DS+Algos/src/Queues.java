import java.util.LinkedList;

/**
 * Created by user on 8/26/2019.
 */
public class Queues {
    LinkedList<Integer> list = new LinkedList<>();

    public void push(int in){
        list.addLast(in);
    }

    public int pop(){
        return list.removeFirst();
    }

    public int peek(){
        return list.getFirst();
    }

    public int size(){
        return list.size();
    }


}
