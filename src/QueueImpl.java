import java.util.Iterator;
import java.util.NoSuchElementException;

interface Queue extends Container {

    // Appends the specified element to the end.
    void enqueue(Object element);

    // Removes the head.
    Object dequeue();

    // Returns the head.
    Object top();
}

public class QueueImpl implements Queue {

    private Object[] objects;
    private int size = 0;
    private Object top = null;

    public QueueImpl() {
        objects = new Object[0];
    }

    public QueueImpl(int n) {
        objects = new Object[0];
        System.out.print(n);
    }

    @Override
    public void clear() {objects = new Object[0];}

    @Override
    public int size() {
        return this.size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object>
    {
        int index = 0;

        @Override
        public boolean hasNext() {
            return (index < size);
        }

        @Override
        public Object next()
        {
            if(hasNext()) {
                Object o = objects[index++];
                return o;
            }   throw new NoSuchElementException();
        }
        @Override
        public void remove() {
            dequeue();
        }
    }

    @Override
    public void enqueue(Object element)
    {
        Object[] oldObjects = objects;
        int oldSize = objects.length;
        objects = new Object[++size];
        for (int i = 0; i < oldSize; i++) {
            objects[i] = oldObjects[i];
        }
        objects[oldSize] = element;
        top = objects[0];
    }

    @Override
    public Object dequeue()
    {
        if (size != 0) {
            Object[] oldObjects = objects;
            objects = new Object[--size];
            for (int i = 0; i < size; i++) {
                objects[i] = oldObjects[i + 1];
            }
            top = objects[0];
            return top;
        }
        throw new NoSuchElementException("Queue is empty");
    }

    @Override
    public Object top()     { return this.top; }

    @Override
    public String toString()
    {
        if (!iterator().hasNext()) {
            return "[]";
        }
        StringBuilder s = new StringBuilder("[");
        for (Object element : objects) {
            s.append(element);
            s.append(", ");
        }
        return s.replace(s.length() - 2, s.length(), "]").toString();
    }

    public static void main(String[] args)
    {
        QueueImpl queue = new QueueImpl(6);
        for (int i = 0; i < 10; i++) {
            queue.enqueue("" + i);
            System.out.print(queue + "          Top => " + queue.top() + " Size => " + queue.size() + "\n");
        }
        for (int i = 0; i < 5; i++) {
            queue.dequeue();
            System.out.print(queue + "          Top => " + queue.top() + " Size => " + queue.size() + "\n");
        }
        Iterator<Object> iter = queue.iterator();
        iter.remove();

        while(iter.hasNext())
        {
            System.out.print(iter.next());
            if(iter.hasNext())  System.out.print(" ");
        }
    }

}