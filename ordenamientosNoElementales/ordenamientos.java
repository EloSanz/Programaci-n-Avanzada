package ordenamientosNoElementales;

public class ordenamientos {

    public class Merge{
        private static void mergear(int[] izq, int[] der, int [] vec)
        {
            int i, d, t;
            i = d = t = 0;
            while (i < izq.length && d < der.length) {
                if(izq[i] < der[d])
                        vec[t++] = izq[i++];
                    else
                        vec[t++] = der[d++];
            }
    
            while (i < izq.length) vec[t++] = izq[i++];
            while (d < der.length) vec[t++] = der[d++];
    
        }   
        public static void mergeSort(int [] vec)
        {
            if(vec.length <= 1) return ;//condicion de corte
            int medio = vec.length / 2;
            int [] izq = new int[medio]; 
            int [] der = new int[vec.length - medio]; 
    
            for(int i = 0; i < vec.length; i++){
                if(i < medio)
                    izq[i] = vec[i];
                else
                    der[i - medio] = vec[i];
            }
            mergeSort(izq);
            mergeSort(der);
            mergear(izq, der, vec);
        }
    }
    
    public class QuickSort {
    private static void _qSort(int[] vec, int ini, int fin){
        if(ini > fin)
            return;
        int izq = ini, der = fin;
        int p =vec[(izq+der) / 2];
        do
        {
            while (vec[izq] < p)
                izq++;
            while (vec[der] > p)
                der--;
            if(izq <= der)
            {
                //swap(&);
                izq++;
                der--;
            }
        } while (izq <= der);
        if(izq <= fin) 
            _qSort(vec, izq, fin);
        if(der >= ini) 
            _qSort(vec, ini, der);
    }
    public static void qSort(int[] vec){
        _qSort(vec, 0, vec.length);
    }
        
    }
    public static void main(String [] args)
    {
        int[] vec = {1,2,3,6,5,8,9,4,7,32,11,10};
        Merge.mergeSort(vec);
        for(int i = 0; i < vec.length; i++)
        System.err.print(vec[i] +" ");
    }

}
