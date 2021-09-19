import java.util.Iterator;
import java.util.NoSuchElementException;

interface Array extends Container {

    // Add the specified element to the end.
    void add(Object element);

    // Sets the element at the specified position.
    void set(int index, Object element);

    // Returns the element at the specified position.
    Object get(int index);

    // Returns the index of the first occurrence of the specified element,
    // or -1 if this array does not contain the element.
    // (use 'equals' method to check an occurrence)
    int indexOf(Object element);

    // Removes the element at the specified position.
    void remove(int index);

}

public class ArrayImpl implements Array
{
    private Object[] arrayPal;
    private int size=0;

    public ArrayImpl(int n)
    {
        arrayPal = new Object[n];
        this.size=n;
    }
    public ArrayImpl() {arrayPal = new Object[0];}

    @Override
    public void clear() { arrayPal = new Object[0]; size=0;}

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public Iterator<Object> iterator()
    {
        return new ArrayImpl.IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object>
    {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < arrayPal.length;
        }

        @Override
        public Object next()
        {
            if(hasNext())
                return arrayPal[pos++];
            throw new NoSuchElementException();
        }
    }

    @Override
    public void add(Object element)
    {
        set(size, element);
    }

    @Override
    public void set(int index, Object element)
    {
        if (index > arrayPal.length - 1) {
            Object[] newArray = new Object[index+1];
            for (int i = 0; i < arrayPal.length; i++) {
                newArray[i] = arrayPal[i];
            }
            newArray[index] = element;

            arrayPal = newArray;
            size++;
        } else {
            if (arrayPal[index] == null) {
                size++;
            }
            arrayPal[index] = element;
        }
    }

    @Override
    public Object get(int index)
    {
        if(size!=0)
            return arrayPal[index];
        throw new NoSuchElementException("Array is empty");
    }

    @Override
    public int indexOf(Object element)
    {
        int i=0;
        while(!arrayPal[i].equals(element) && i<size) i++;
        if(arrayPal[i].equals(element)) return  i;
        else return -1;
    }

    @Override
    public void remove(int index)
    {
        if(index <= arrayPal.length - 1)
        {
            Object temp[] = new Object[size - 1];
            int j = 0;
            for (int i=0;i<size;i++) {
                if (i == index) {
                    i++;
                    continue;
                }
                temp[j] = arrayPal[i];
                i++;
                j++;
            }
            size--;
            arrayPal = temp;
        }
        else throw new NoSuchElementException("Array is empty");
    }

    @Override
    public String toString()
    {
        if (!iterator().hasNext()) {
            return "[]";
        }
        StringBuilder s = new StringBuilder("[");
        for (Object element : arrayPal) {
            s.append(element);
            s.append(", ");
        }
        return s.replace(s.length() - 2, s.length(), "]").toString();
    }

    public static void main(String[] args)
    {
        ArrayImpl arr1 = new ArrayImpl();
        arr1.add("A");
        arr1.add("B");
        arr1.add("C");
        System.out.print(arr1.size()+"\n");
        System.out.print(arr1.toString()+"\n");
        System.out.print(arr1.get(1)+"\n");
        System.out.print(arr1.indexOf("C")+"\n");
        arr1.remove(1);
        arr1.set(1,"B");
        Iterator<Object> iter = arr1.iterator();
        while(iter.hasNext())
        {
            System.out.print(iter.next());
            if(iter.hasNext())  System.out.print(" ");
        }
        arr1.clear();
    }
}
