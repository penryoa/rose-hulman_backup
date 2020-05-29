#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(){
  char ans[2];
  char ans1;
  int guess;
  int upper = 101;
  int lower = 0;
  // int temp;

  printf("Welcome to the wonderful world of number guessing!\nThink of an integer number between 1 and 100.\n");

  while (1) {
    guess = (upper + lower)/2;
    if (guess >= upper || guess <= lower){
      printf("Liar! You cheat!\n");
      break;
    }

    printf("Is your number %d? (Y/N)\n",guess);
    scanf("%s", ans);
    ans1 = ans[0];

    if (ans1=='y' || ans1=='Y'){
      printf("Great, bye!\n");
      break;
    }

    else if (ans1=='n' || ans1=='N'){
      printf("Too bad.\n");

      while (1) {
        printf("Is my guess too high (H) or too low (L)?\n");
        scanf("%s", ans);
        ans1 = ans[0];

        if (ans1=='h' || ans1=='H'){
          printf("Ok, my guess was too high.\n");
          // temp = guess;
          upper = guess;
          // guess = (upper + lower)/2;
          // printf("\tUpper: %d\tLower: %d\tOld guess: %d\t New guess: %d\n", upper, lower, temp, guess);
          // if (temp <= guess){
          //   printf("Liar! You cheat!\n");
          //   return 1;
          // }
          // guess = guess - (upper + lower)/2;
          break;
        } else if (ans1=='l' || ans1=='L'){
          printf("Ok, my guess was too low.\n");
          // temp = guess;
          lower = guess;
          // guess = ceil((upper + lower)/2);
          // printf("\tUpper: %d\tLower: %d\tOld guess: %d\t New guess: %d\n", upper, lower, temp, guess);
          // if (temp >= guess){
          //   printf("Liar! You cheat!\n");
          //   return 1;
          // }
          // guess = guess + (upper + lower)/2;
          break;
        } else {
          printf("You didn't enter a proper response. Please respond H,h,L,or l\n");
        }
      }
    }

    else {
      printf("You didn't enter a proper response. Please respond Y,y,N,or n\n");
    }
  }

}
