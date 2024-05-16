#include <stdio.h>
#define MIN(X,Y) (X<Y)? (X):(Y)

int instalandoApps(int cantApps, int tamNueva, int apps[])
{
    int r = cantApps;//supongo que debo eliminar todas
    int j = 0, tamD = 0;

    for(int i = 0; i < cantApps; i++)
    {
        while (j < cantApps && tamD < tamNueva)
        {
            tamD += apps[j];
            j++;
        }
        if(tamD >= tamNueva)
            r = MIN(r, j-i);
        
        tamD-= apps[i];
    }
    return r;
}

int main()
{
    int apps[] = {5,6,7,8,9,18}, ce = sizeof(apps)/sizeof(int);
    printf("%d ", instalandoApps2(ce, 19, apps));

    return 0;
}