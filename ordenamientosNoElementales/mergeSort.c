
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <windows.h>
void swap(void* a, void* b, unsigned tam)
{
    void* temp = malloc(tam);
    memcpy(temp, a, tam);
    memcpy(a, b, tam);
    memcpy(b, temp, tam);
    free(temp);
}


void mostrarInts(int vec[], int ce)
{
    for(int i = 0; i < ce; i++)
    {
        printf("%d ", vec[i]);
    }
}

void merge(int izq[], int der[], int vec[], int ce)
{
    int l, r, i, 
    lSize = ce / 2,
    rSize = ce - lSize;
    l = r = i = 0;

    while (l < lSize && r < rSize)
    {
        if(izq[l] < der[r])
            vec[i++] = izq[l++];
        else
            vec[i++] = der[r++];
    }
    while (l < lSize)
        vec[i++] = izq[l++];
    while (r < rSize)
        vec[i++] = der[r++];
}


void mergeSort(int vec[], int ce)
{
    if(ce <= 1)
        return ;
    int medio = ce / 2;

    int* izq = malloc(medio* sizeof(int));
    int* der = malloc( (ce - medio) * sizeof(int));

    if(!izq || !der)
    {
        printf("Sin memoria\n");
        return ;
    }

    for(int i = 0; i < ce; i++)
    {
        if(i < medio)
            izq[i] = vec[i];
        else
            der[i - medio] = vec[i];
    }

    mergeSort(izq, medio);
    mergeSort(der, ce - medio);
    merge(izq, der, vec, ce);
}



int * generarVector(int n)
{
    int* v = malloc(sizeof(int) * n);
    if(!v)
    {
        printf("No hay memoria amigo para un poco\n");
        return NULL;
    }

    srand(time(NULL));

    for(int i = 0; i < n; i++)
    {
        v[i] = rand() %n +1;
    }

    return v;
}

int cmpInt(const void*a, const void* b)
{
    return *((int*)a) - *((int*) b);
}
int main()
{
    LARGE_INTEGER frequency;        // ticks per second
    LARGE_INTEGER t1, t2;           // ticks
    double elapsedTime;

    int n  = 10;
    int * vec = generarVector(n);
//0.002500 merge
//Tardo: 0.001900 qsort
//0.0017

    QueryPerformanceFrequency(&frequency);
    // start timer
    QueryPerformanceCounter(&t1);
    //qsort(vec, n, sizeof(int), cmpInt); //Tardo: 118.571200
    mergeSort(vec, n);
    QueryPerformanceCounter(&t2);

    // compute and print the elapsed time in millisec
    elapsedTime = (t2.QuadPart - t1.QuadPart) * 1000.0 / frequency.QuadPart;
    printf("Tardo: %lf ", elapsedTime);
}
