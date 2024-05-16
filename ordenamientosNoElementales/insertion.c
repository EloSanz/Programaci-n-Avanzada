#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef int(*tCmp)(const void*, const void*);

int cmpInt(const void* a, const void* b)
{
    return (*(int*)a) - (*(int*)b);
}

void swap(void* a, void* b, unsigned tam)
{
    char* temp = malloc(tam);
    memcpy(temp, a, tam);
    memcpy(a, b, tam);
    memcpy(b, temp, tam);
    free(temp);
}
void insertion(int vec[], int ce)
{
    for(int i = 1; i < ce; i++)
    {
        int act = vec[i];
        int j = i - 1;
        while (j >= 0 && vec[j] > act)
        {
            vec[j + 1] = vec[j];
            j--;
        }
        vec[j + 1] = act;
    }
}
void insercionGenerica(void* vec, int ce, unsigned tam, tCmp cmp)
{
    void* aux = malloc(tam);
    for(void* i = vec + tam; i < vec + ce * tam ; i+=tam)
    {
        memcpy(aux, i, tam);
        void* j = i - tam;
        while (j >= vec && cmp(aux, j) < 0)
        {
            memcpy(j + tam, j, tam);
            j-=tam;
        }
        memcpy(j + tam, aux, tam);
    }   
    free(aux);
}
void mostrarVec(int* v, int ce)
{
    for(int i = 0; i < ce; i++)
        printf("%d ", *(v + i) );
}

int main()
{
    int vec[] = {2,1,5,4,7,6,8,3,1,12,12,12,21,10,100,0,-1}, ce = sizeof(vec)/sizeof(int);
    insercionGenerica(vec, ce, sizeof(int), cmpInt);
    mostrarVec(vec, ce);


    return 0;

}