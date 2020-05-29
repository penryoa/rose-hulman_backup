#include <stdio.h>
#include <stdlib.h>

void bub(int p, int cks[], int ord[]){
  int i,j,temp;

  for (i = 0; i < p - 1; i++){
    for (j = 0; j < p - 1 - i; j++){
      if (cks[j+1] < cks[j]){
        temp = cks[j];
        cks[j] = cks[j+1];
        cks[j+1] = temp;

        temp = ord[j];
        ord[j] = ord[j+1];
        ord[j+1] = temp;
      }
    }
  }
}


int main(){
  int num_p = 10, i, pancakes[num_p], ord[num_p];


  printf("Welcome to pancake analyzer PRO.  The essential tool for analyzing pancake eating.\n");

  for (i = 0; i < num_p; i++){
    printf("How many pancakes did person %d eat?\n",i+1);
    scanf("%d", &pancakes[i]);
    ord[i] = i+1;
    printf("\n");
  }

  bub(num_p, &pancakes, &ord);

  for (i = 0; i < num_p; i++){
    printf("Person %d ate  %d pancakes\n", ord[i], pancakes[i]);
  }
}
