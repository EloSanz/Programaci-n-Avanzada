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

void _qSort(int vec[], int minIdx, int maxIdx)
{
    if(minIdx > maxIdx)
        return;
    int izq = minIdx, der = maxIdx;
    int p = vec[(izq + der) / 2];
    /*
    int aux = p;
    p = vec[der];
    vec[der] = aux;
    */
    do
    {
        while (vec[izq] < p)
            izq++;
        while (vec[der] > p)
            der--;
        if(izq <= der)
        {
            swap(&vec[der], &vec[izq], sizeof(int));
            izq++;
            der--;
        }
    } while (izq <= der);

    if(izq <= maxIdx) 
        _qSort(vec, izq, maxIdx);
    if(der >= minIdx) 
        _qSort(vec, minIdx, der);
}

void qSort(int vec[], int ce)
{
    _qSort(vec, 0, ce-1);
}

int partition(int vec[], int minIdx, int maxIdx)
{
    int pivot = vec[maxIdx]; // el ultimo es el pivote
    int i = minIdx -1;

    for(int j = minIdx; j <= maxIdx -1; j++)
    {
        if(vec[j] < pivot)
        {
            i++;
            swap(&vec[i], &vec[j], sizeof(int));
        }
    }
    i++;
    swap(&vec[i], &vec[maxIdx], sizeof(int));
    return i;
}

void qSortP(int vec[], int minIdx, int maxIdx)
{
    if(minIdx >= maxIdx)
        return;
    int posP= partition(vec, minIdx, maxIdx);

    qSortP(vec, minIdx, posP -1 );
    qSortP(vec, posP + 1, maxIdx);
}

void mostrarInts(int vec[], int ce)
{
    for(int i = 0; i < ce; i++)
    {
        printf("%d ", i[vec]);
    }
}

int main()
{
    int vec[] = {1,2,3,4,5,6,7,8,9,10,11,12,13}, ce = sizeof(vec) / sizeof(int);    
    mostrarInts(vec, ce);
    
    qSort(vec, ce);
    
    puts("");
    mostrarInts(vec, ce);
}