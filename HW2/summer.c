#include <stdio.h>
#include <stdlib.h>

int main(){
  int idx;
  float sum, temp;
  int limit = 3;

  for (idx = 1; idx <= limit; idx++){
    printf("Enter number %d:\n", idx);
    scanf("%f", &temp);
    sum += temp;
  }

  printf("%f\n", sum);
}
