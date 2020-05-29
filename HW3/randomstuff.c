#include <stdio.h>
#include <stdlib.h>

int countEven(int* arr, int size){
  int i,count=0;
  for (i = 0; i < size; i++){
    if (arr[i]%2==0){
      count++;
    }
  }
  return count;
}

double* maximum(double* a, int size){
  int i;
  double* max = &a[0];
  for (i = 1; i < size; i++){
    if (a[i]>*max){
      max = &a[i];
    }
  }
  return max;
}

int myStrLen(char* s){
  int l = 0;
  while (s[l] != '\0'){
    l++;
  }
  return l;
}

void revString(char* s){
  int i, len = myStrLen(s);
  char s_copy[len];
  for (i = 0; i < len; i++){
    s_copy[i] = s[i];
  }
  for (i = 0; i < len; i++){
    s[i] = s_copy[len-i-1];
  }
}

void delEven(int* arr, int size){
  int i;
  for (i = 0; i < size; i++){
    if (arr[i]%2==0){
      arr[i] = -1;
    }
  }
}


int main(int argc, char** argv){
  int i;
  int arr[10] = {0,1,2,3,4,50,6,7,8,9};
  delEven(arr, 10);
  printf("output: ");
  for (i = 0; i < 10; i++){
    printf("%d ", arr[i]);
  }
  printf("\n");
  // char str[10] = "testing";
  // revString(str);
  // printf("output: %s\n", str);
  // printf("should be %d. output: %d\n", 7, myStrLen(str));
  // printf("should be %d. output: %d\n", &arr[5], maximum(arr,10));
}
