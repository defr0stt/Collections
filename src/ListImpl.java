import java.util.Iterator;
import java.util.NoSuchElementException;

interface List extends Container {

    // Inserts the specified element at the beginning.
    void addFirst(Object element);

    // Appends the specified element to the end.
    void addLast(Object element);

    // Removes the first element.
    void removeFirst();

    // Removes the last element.
    void removeLast();

    // Returns the first element.
    Object getFirst();

    // Returns the last element.
    Object getLast();

    // Returns the first occurrence of the specified element.
    // Returns null if no such element.
    // (use 'equals' method to check an occurrence)
    Object search(Object element);

    // Removes the first occurrence of the specified element.
    // Returns true if this list contained the specified element.
    // (use 'equals' method to check an occurrence)
    boolean remove(Object element);

}

public class ListImpl implements List
{
    static class Node
    {
        Object elem;
        Node next;
        public  Node(Object elem)
        {this.elem=elem;}
    }

    private Node first;
    private Node last;
    private int size=0;

    @Override
    public void clear()
    {
        first=null;
        last=null;
        size=0;
    }

    @Override
    public int size() {return size;}

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object>
    {
        Node current = first;
        @Override
        public boolean hasNext() {return current != null;}

        @Override
        public Object next()
        {
            if(size!=0) {
                Object data = current.elem;
                current = current.next;
                return data;
            } throw new NoSuchElementException("List is Empty");
        }

    }

    @Override
    public void addFirst(Object element) {
        if(first == null && last==null)
            first = last = new Node(element);
        else
        {
            Node temp = new Node(element);
            temp.next=first;
            first=temp;
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        if(first == null && last==null)
            first = last = new Node(element);
        else
        {
            Node temp = new Node(element);
            last.next=temp;
            last=temp;
        }
        size++;
    }

    @Override
    public void removeFirst()
    {
        if(size!=0)
        {
            Node temp = first.next;
            first=temp;
            size--;
            return;
        } throw new NoSuchElementException("List is Empty");
    }

    @Override
    public void removeLast()
    {
        if(size!=0)
        {
            Node temp = first;
            int t=1;
            while(t!=size-1)
            {
                temp=temp.next;
                t++;
            }
            temp.next=null;
            last=temp;
            size--;
            return;
        }   throw new NoSuchElementException("List is Empty");
    }

    @Override
    public Object getFirst()
    {
        if(size!=0) return first.elem;
        throw new NoSuchElementException("List is Empty");
    }

    @Override
    public Object getLast()     {
        if(size!=0) return last.elem;
        throw new NoSuchElementException("List is Empty");
    }

    @Override
    public Object search(Object element)
    {
        if(size!=0)
        {
            Node temp = first;
            while (!temp.elem.equals(element) && temp!=last.next) {
                temp = temp.next;
            }
            if (temp!=last.next && temp.elem.equals(element)) return temp.elem;
            return null;
        }
        else throw new NoSuchElementException("List is empty");
    }

    @Override
    public boolean remove(Object element)
    {
        if(size!=0) {
            Node temp1 = first;
            Node temp2 = first.next;
            if (temp1.equals(element)) {
                Node cpu = first;
                cpu = cpu.next;
                first = cpu;
                size--;
                return true;
            }
            while (temp2 != null && !temp2.elem.equals(element)) {
                temp2 = temp2.next;
                temp1 = temp1.next;
            }

            if (temp2 == null) {
                return false;
            } else if (temp2.elem.equals(element)) {
                size--;
                temp1.next = temp2.next;
                temp2.next = null;
                temp2.elem = null;
                temp2 = null;
                return true;
            }
            return false;
        }   throw new NoSuchElementException("List is Empty");
    }

    @Override
    public String toString()
    {
        Node temp = first;
        if (!iterator().hasNext()) {
            return "[]";
        }
        StringBuilder s = new StringBuilder("[");
        for (int i=0;i<size;i++) {
            s.append(temp.elem);
            s.append(", ");
            temp=temp.next;
        }
        return s.replace(s.length() - 2, s.length(), "]").toString();
    }

    public static void main(String[] args)
    {
        ListImpl a = new ListImpl();
        a.addFirst("A");
        a.addLast("B");
        a.addLast("C");

        System.out.print(a.toString()+"\n");
        System.out.print(a.size()+"\n");
        System.out.print(a.getFirst()+"\n");
        System.out.print(a.getLast()+"\n");
        System.out.print(a.search("B")+"\n");
        System.out.print(a.remove("fix")+"\n");
        Iterator<Object> iter = a.iterator();
        while(iter.hasNext())
        {
            System.out.print(iter.next());
            if(iter.hasNext())  System.out.print(" ");
        }
        a.removeLast();
        a.removeFirst();
        a.clear();
    }
}