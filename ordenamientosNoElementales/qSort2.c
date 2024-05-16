#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void swap(void* a, void* b, unsigned tam)
{
    void* temp = malloc(tam);
    memcpy(temp, a, tam);
    memcpy(a, b, tam);
    memcpy(b, temp, tam);
    free(temp);
}
int partition(int arr[], int low, int high)
{
    int pivot = arr[high]; // seleccionamos el pivote como el último elemento
    int i = low - 1; // índice del elemento más pequeño

    for (int j = low; j <= high - 1; j++) {
        if (arr[j] <= pivot) {        // Si el elemento actual es menor o igual al pivote
            i++; // incrementamos el índice del elemento más pequeño
            swap(&arr[i], &arr[j], sizeof(int)); // intercambiamos arr[i] y arr[j]
        }
    }
    swap(&arr[i + 1], &arr[high], sizeof(int)); // colocamos el pivote en su posición correcta
    return i + 1; // devolvemos el índice del pivote
}
void quicksort(int arr[], int low, int high) {
    if (low < high) {
        int posPivote = partition(arr, low, high); // encontramos el pivote
        quicksort(arr, low, posPivote - 1); // ordenamos la sublista antes del pivote
        quicksort(arr, posPivote + 1, high); // ordenamos la sublista después del pivote
    }
}



void qSortPPT(int vec[], int ini, int fin) {
    if (ini >= fin)
        return;

    int izq = ini, der = fin;
    int p = vec[(ini + fin) / 2];
    
    do {
        while (vec[izq] < p)
            izq++;
        while (vec[der] > p)
            der--;

        if (izq <= der) {
            swap(&vec[izq], &vec[der], sizeof(int));
            izq++;
            der--;
        }
    } while (izq <= der);

    if (ini < der)
        qSortPPT(vec, ini, der-1);
    if (izq < fin)
        qSortPPT(vec, izq+1, fin);
}
int main()
{
    int vec[] = {1,2,3,4,5,6,7,8,9};
    int ce = sizeof(vec)/sizeof(int);

    qSortPPT(vec, 0, ce-1);

    for(int i = 0; i < ce; i++)
        printf("%d ", vec[i]);
}