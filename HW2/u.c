#include <stdio.h>
#include <stdlib.h>

int main(){
  int n = -1;

  while (n<0){
    printf("Enter n, n>=0:\n");
    scanf("%d", &n);
  }

  printf("u(%d)=%d\n", n, u(n));
}

int u(int n){
  if (n == 0) return 3;
  else return 3*u(n-1) + 4;
}
