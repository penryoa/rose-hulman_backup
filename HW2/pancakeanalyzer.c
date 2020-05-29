#include <stdio.h>
#include <stdlib.h>

int main(){
  int i;
  int num_p = 10;
  int pancakes[num_p];
  int max_i = 0;
  int min_i = 0;

  printf("Welcome to pancake analyzer. Your number one tool for analyzing pancake eating.\n");

  for (i = 0; i < num_p; i++){
    printf("How many pancakes did person %d eat?\n",i+1);
    scanf("%d", &pancakes[i]);
    printf("\n");
  }

  for (i = 1; i < num_p; i++){
    if (pancakes[i] < pancakes[min_i]) {
      min_i = i;
    }

    if (pancakes[i] > pancakes[max_i]) {
      max_i = i;
    }
  }

  printf("Person %d ate the fewest pancakes, with %d\n", min_i+1, pancakes[min_i]);
  printf("Person %d ate the most pancakes, with %d\n", max_i+1, pancakes[max_i]);
}
