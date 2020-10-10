import java.util.NoSuchElementException;

public class SimpleQueue {
    public String[] mainQueue;
    public int queueSize;
    public int queueCapacity;
    public int head;

    public SimpleQueue(int initialCapacity){
        if(initialCapacity < 1){
            throw new IllegalArgumentException();
        }
        mainQueue = new String[initialCapacity];
        queueSize = 0;
        queueCapacity = initialCapacity;
        head = 0;
    }

    public boolean add(String s){
        checkString(s);
        checkClass(s);
        if(offer(s)==false){
            checkCapacity();
            return false;
        }
        return true;
    }

    public void clear(){
        mainQueue = new String[queueCapacity];
        queueSize = 0;
    }

    public boolean contains(String s){
        int indexOfString = indexOf(s);
            if(indexOfString != -1){
                return true;
            }
        return false;
    }

    public String element(){
        if(queueSize == 0){
            throw new NoSuchElementException();
        }
        return mainQueue[0];
    }

    public boolean offer(String s){
        checkString(s);
        if(queueSize == queueCapacity){
            return false;
        }
        else {
            mainQueue[queueSize] = s;
            queueSize++;
            return true;
        }
    }

    public String peek(){
        return mainQueue[0];
    }

    public String poll(){
        if(queueSize == 0){
            return null;
        }
        String removedString = mainQueue[0];
        String[] tempQueue = new String[queueSize];
        for(int i = 0; i < queueSize; i++){
            tempQueue[i] = mainQueue[i];
        }
        queueSize--;
        mainQueue = new String[queueCapacity];
        for(int i = 0; i < queueSize; i++){
            mainQueue[i] = tempQueue[i+1];
        }
        return  removedString;
    }

    public int remainingCapacity(){
        int remainingCap = queueCapacity-queueSize;
        return  (remainingCap);
    }

    public String remove(){
        if (queueSize == 0){
            throw new NoSuchElementException();
        }
        String removeString = poll();
        return removeString;
    }

    public boolean remove(String s){
        int indexOfString = indexOf(s);
        if(indexOfString != -1) {
            String[] tempQueue = new String[queueSize];
            for(int i = 0; i < queueSize; i++){
                tempQueue[i] = mainQueue[i];
            }
            queueSize--;
            mainQueue = new String[queueCapacity];
            for (int i = 0; i < indexOfString; i++){
                mainQueue[i] = tempQueue[i];
            }
            for(int i = indexOfString; i < queueSize; i++){
                mainQueue[i] = tempQueue[i+1];
            }
            return true;
        }
        return false;
    }

    public int size(){
        return queueSize;
    }

    public String toString(){
        String convert = "[";
            for (int i = 0; i < queueSize; i++) {
                String queueData = mainQueue[i];
                if (i == queueSize - 1) {
                    convert += queueData;
                } else {
                    convert += queueData + ", ";
                }
            }
        return convert + "]";
    }

    private int indexOf(String s) {
        int index = 0;
            for (int i = 0; i < queueSize; i++) {
                if(mainQueue[i].equals(s)){
                    return index;
                }
                index++;
            }
        return -1;
    }


    private void checkString(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
    }
    private void checkClass(String s){
        boolean checkString = s instanceof String;
        if(checkString = false){
            throw new ClassCastException();
        }
    }
    private void checkCapacity(){
        int capacity = remainingCapacity();
        if(capacity == 0){
            throw new IllegalStateException("Queue full");
        }
    }
}
