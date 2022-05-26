import java.util.*;

interface Iterator {
    public boolean has_next();

    public Object get_next();
}

class Sequence {
    private final int maxLimit = 80;
    private SeqIterator _iter = null;
    int[] iArr;
    int size;

    Sequence(int s) {
        size = s;
        iArr = new int[s];
    }

    public void addTo(int elem) {
        for (int i = 0; i < size; i++) {
            if (iArr[i] == 0) {
                iArr[i] = elem;
                break;
            }
        }
    }

    public Iterator get_Iterator() {
        Iterator it = new SeqIterator();
        return (it);
    }

    private class SeqIterator implements Iterator {
        int indx;

        public SeqIterator() {
            indx = -1;
        }

        public boolean has_next() {
            if (((indx + 1) < iArr.length) && (iArr[indx + 1] != 0)) {
                return true;
            } else {
                return false;
            }
        }

        public String get_next() {
            indx += 1;
            int NextElem = iArr[indx];
            return (NextElem + "");
        }
    }
}

class FClass {
    public static void main(String[] args) {
        Sequence sObj = new Sequence(5);
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            sObj.addTo(sc.nextInt());
        }
        Iterator i = sObj.get_Iterator();
        while (i.has_next())
            System.out.print(i.get_next() + ", ");
    }
}