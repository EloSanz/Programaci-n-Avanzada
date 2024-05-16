import java.util.Arrays;

public class Heap<T extends Comparable<T>> {
    private T[] heapArray;
    private int elementCount;

    @SuppressWarnings("unchecked")
    public Heap() {
        this.heapArray = (T[]) new Comparable[10];
        this.elementCount = 0;
    }

    public void add(T element) {
        if (this.isEmpty()) {
            this.elementCount++;
            this.heapArray[1] = element;
        } else {
            this.elementCount++;
            if (elementCount >= this.heapArray.length) {
                this.heapArray = Arrays.copyOf(this.heapArray, elementCount * 2);
            }//duplicado el tamaño agrego elemento
            this.heapArray[elementCount] = element;
            int childPos = elementCount;
            int parentPos = childPos / 2;
            while (parentPos > 0 && this.heapArray[parentPos].compareTo(element) > 0) {
                T aux = this.heapArray[childPos];
                this.heapArray[childPos] = this.heapArray[parentPos];
                this.heapArray[parentPos] = aux;
                childPos = parentPos;
                parentPos = childPos / 2;
            }
        }
    }

    public Object remove() {
        if (!this.isEmpty()) {
            T elem = this.heapArray[1];
            this.heapArray[1] = this.heapArray[elementCount];
            this.elementCount--;
            int parentPos = 1;
            boolean isHeap = false;

            while (!isHeap) {//mientras No cumpla la condición de montículo
                int leftChildPos = (2 * parentPos);
                int rightChildPos = (2 * parentPos) + 1;
                T leftChild;
                T rightChild;

                if (rightChildPos <= this.elementCount) {//si hay hijo derecho
                    rightChild = this.heapArray[rightChildPos];
                    leftChild = this.heapArray[leftChildPos];
                    if (leftChild.compareTo(rightChild) < 0 && this.heapArray[parentPos].compareTo(leftChild) > 0) {
                        T aux = this.heapArray[parentPos];//si el izq es menor y el padre es mayor cambio
                        this.heapArray[parentPos] = this.heapArray[leftChildPos];
                        this.heapArray[leftChildPos] = aux;
                        parentPos = leftChildPos;
                    } else if (this.heapArray[parentPos].compareTo(rightChild) > 0) {//el hijo derecho es mayor, veo si padre es mayor que hijoDer
                        T aux = this.heapArray[parentPos];//los cambio
                        this.heapArray[parentPos] = this.heapArray[rightChildPos];
                        this.heapArray[rightChildPos] = aux;
                        parentPos = rightChildPos;
                    } else {//si ni el hIzq como el derecho son menores, entonces se cumple el heap.
                        isHeap = true;
                    }
                } else if (leftChildPos <= this.elementCount) {//pregunto si hay izquierdo
                    leftChild = this.heapArray[leftChildPos];
                    if (this.heapArray[parentPos].compareTo(leftChild) > 0) {
                        T aux = this.heapArray[parentPos];
                        this.heapArray[parentPos] = this.heapArray[leftChildPos];
                        this.heapArray[leftChildPos] = aux;
                        parentPos = leftChildPos;
                    } else {//La raiz es menor que sus hijos.
                        isHeap = true;
                    }
                }//si no es porque habían [2] elementos, y ahora hay sólo una raíz [1] elemento.
                else {
                    isHeap = true;
                }
            }
            return elem; //devuelvo el heap[1]
        } else {// está vacío
            return null;
        }
    }

    public T peek() {
        if (!this.isEmpty()) {
            return this.heapArray[1];
        } else {
            return null;
        }
    }

    public void clear() {
        this.elementCount = 0;
    }

    public boolean isEmpty() {
        return !(this.elementCount > 0);
    }

    public Comparable<T>[] toArray() {
        return Arrays.copyOfRange(heapArray, 1, elementCount + 1);
    }
}