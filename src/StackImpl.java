import java.util.Iterator;
import java.util.NoSuchElementException;

interface Stack extends Container {

    // Pushes the specified element onto the top.
    void push(Object element);

    // Removes and returns the top element.
    Object pop();

    // Returns the top element.
    Object top();

}

public class StackImpl implements Stack
{
    private Object arr[];
    private int top=-1;

    @Override
    public void clear()
    {
        int l = arr.length;
        while(l>0)
        {
            arr[l - 1] = null;
            l -= 1;
        }
        String temp[]=new String[0];
        arr=temp;
        top=-1;
    }

    @Override
    public int size()   { return arr.length; }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object>
    {
        private  int pos=arr.length-1;
        @Override
        public boolean hasNext() {return pos>=0;}

        @Override
        public Object next() {
            if (hasNext()) {
                return arr[pos--];
            }   throw new NoSuchElementException("Stack is Empty");
        }
    }

    @Override
    public void push(Object element)
    {
        if(arr==null)
        {
            arr = new Object[1];
            arr[0]=element;
            top++;
            return;
        }
        Object copy[] = new Object[arr.length+1];
        int i=0;
        for(Object a: arr)
        {
            copy[i]=a;
            i++;
        }
        copy[arr.length]=element;
        arr=copy;
        top++;
    }

    @Override
    public Object pop()
    {
        Object temp = arr[top];
        Object copy [] = new Object[arr.length-1];
        int i=0;
        for(Object a: arr)
        {
            copy[i]=a;
            i++;
            if(i== (arr.length-1))  break;
        }
        top--;
        arr=copy;
        return temp;
    }

    @Override
    public Object top()     { return arr[top]; }

    @Override
    public String toString()
    {
        if (!iterator().hasNext()) {
            return "[]";
        }
        StringBuilder s = new StringBuilder("[");
        for (Object element : arr) {
            s.append(element);
            s.append(", ");
        }
        return s.replace(s.length() - 2, s.length(), "]").toString();
    }

    public static void main(String[] args)
    {
        StackImpl b = new StackImpl();
        b.push("A");
        b.push("B");
        b.push("C");

        System.out.print(b.toString()+"\n");
        System.out.print(b.pop()+"\n");
        System.out.print(b.size()+"\n");
        System.out.print(b.top()+"\n");
        Iterator<Object> iter = b.iterator();
        while(iter.hasNext())
        {
            System.out.print(iter.next());
            if(iter.hasNext())  System.out.print(" ");
        }
        b.clear();
    }
}