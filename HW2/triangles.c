#include <stdio.h>
#include <stdlib.h>

int main(){
  int i,j,num_rows=-1;

  while (num_rows < 1){
    printf("Enter a positive integer number of rows:\n");
    scanf("%d", &num_rows);
    printf("\n");
  }


  for (i = 0; i < num_rows; i++){
    for (j=0;j<i;j++){
      printf(" ");
    }
    for (j=num_rows;j>i;j--){
      printf("*");
    }
    printf("\n");
  }

}
