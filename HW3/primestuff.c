#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int isprime(int n){
  int i;
  if (n < 2)
    return 0;
  for (i = 2; i <= n/2; i++){
    if (n%i == 0){
      return 0;
    }
  }
  return 1;
}

int isMersenne(int n){
  int p = 2;
  if (isprime(n)){
    while (n >= 2*p - 1){
      if (n == 2*p - 1){
        return 1;
      }
      p*=2;
    }
  }
  return 0;
}

void prime_info(int* ns,int l, int* num_prime, int* primes,
  int* num_mersenne, int* mersennes){
  int i;
  *num_prime=0;
  *num_mersenne=0;
  for (i = 0; i < l; i++){
    if (isprime(ns[i])){
      *(primes + *num_prime) = ns[i];
      *num_prime = *num_prime+1;
      if (isMersenne(ns[i])){
        *(mersennes + *num_mersenne) = ns[i];
        *num_mersenne = *num_mersenne+1;
      }
    }
  }
}

void reportinfo(int l, int* ns, int* np, int* p, int* nm, int* m){
  int i;
  printf("Consider the array [");
  for (i=0; i < l-1; i++){
    printf("%d,", ns[i]);
  }
  printf("%d]\n", ns[l-1]);

  prime_info(ns, l, np, p, nm, m);

  printf("There are %d prime elements, they are [", *np);
  for (i=0; i < *np; i++){
    if (i == *np-1)
      printf("%d", p[i]);
    else
      printf("%d,", p[i]);
  }
  printf("]\n");

  printf("There are %d Mersenne prime elements, they are [", *nm);
  for (i=0; i < *nm; i++){
    if (i== *nm - 1)
      printf("%d", m[i]);
    else
      printf("%d,", m[i]);
  }
  printf("]\n");

  free(ns);
  free(m);
  free(p);
}

int main(int argc, char** argv){
  int i, j, l, np, nm;
  int* ns;
  int* p;
  int* m;
  if (argc >= 2){
    if (strcmp(argv[1], "--list")==0){
      l = argc - 2;
      ns=(int*)malloc(l*sizeof(int));
      p=(int*)malloc(l*sizeof(int));
      m=(int*)malloc(l*sizeof(int));

      for (i = 0; i < l; i++){
        *(ns+i) = atoi(*(argv+i+2));
      }
      reportinfo(l, ns, &np, p, &nm, m);
    }

    else if (strcmp(argv[1], "--range")==0){
      if (argc < 4)
        printf("See usage information\n");
      else {
        l = atoi(argv[3]) - atoi(argv[2]) + 1;
        if (l < 0) {l = 0;}
        ns=(int*)malloc(l*sizeof(int));
        p=(int*)malloc(l*sizeof(int));
        m=(int*)malloc(l*sizeof(int));
        j = 0;
        for (i = atoi(argv[2]); i <= atoi(argv[3]); i++){
          *(ns+j) = i;
          j++;
        }
        reportinfo(l, ns, &np, p, &nm, m);
      }
    } else{
      printf("See usage information\n");
    }
  } else {
    printf("See usage information\n");
  }
}
