#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <limits.h>
#include <float.h>

#include "goldbach_helpers.c"

int goldbach_partition_calls=0;

//returns 1 if n is prime, 0 otherwise
int isprime(int n){
  if (n<2){
    return 0;
  }
  int i=2;
  while (i*i<=n){
    if (n%i==0){
      return 0;
    }
    i++;
  }
  return 1;
}



//Attempts to calculate a Golbach partition of n.
//Returns a Goldbach partition that includes the smallest possible prime.
//i.e., both 3/7 and 5/5 are partitions of 10.  3/7 is returned because 3 is the smallest of 3,5,7.
//Returns 1 on success, in this case a and b hold the partition
//Returns 2 if n<=2, or if n is odd
//Returns 3 if you have disproved Golbach's conjecture.
int goldbach_partition(int n, int* a, int* b){
  goldbach_partition_calls++;
  int exit_code=2;
  if (n<=2 || (n % 2 ==1)){
    return exit_code;
  }

  exit_code=3;

  int i;
  for (i=2;i<=n/2;i++){
    if (isprime(i) && isprime(n-i)){
      *a=i; *b=n-i;
      exit_code=1;
      return exit_code;
    }
  }

  return exit_code;

}


int min(int a, int b){
  return a<b ? a:b;
}


int main(int argc, char** argv){

  MPI_Init(&argc,&argv);

  int rank;
  int size;
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);
  MPI_Comm_size(MPI_COMM_WORLD,&size);

  options opt;
  parse_options(argc,argv,&opt);
  if (rank==0){
    print_options(&opt);
    // MY CODE:
    int num_chunks = 0;
    int s_rank = 0;
    int num_range[2];
    while (num_chunks < opt.num_to_partition){
      num_range[0] = num_chunks;
      num_range[1] = min(opt.num_to_partition,num_chunks+opt.chunksize);
      // printf("Sending %d to Rank %d\n", num_chunks, s_rank+1);
      MPI_Send(&num_range,2,MPI_INT,s_rank+1, 0, MPI_COMM_WORLD);
      s_rank = (s_rank+1)%(size-1);
      num_chunks+=opt.chunksize;
    }
    // num_chunks=-1;
    for (int i = 1; i < size; i++){
      // printf("Goodbye Rank %d\n", i);
      // num_range[0] = -1;
      // num_range[1] = -1;
      MPI_Send(NULL,0,MPI_INT,i,1,MPI_COMM_WORLD); // tag = 1; quit
    }
  }

  //The command line options have now been parsed properly.
  //They are held in the members of opt.
  //The options you care about are:
  //opt.print_results  -- 0 if you should not print out the partitions you find, 1 if you should
  //opt.to_partition   -- the list of numbers that you should find partitions for
  //opt.num_to_partition -- the length of the list above
  //opt.chunksize -- the control for the "chunksize" of a work assignment.  If the chunksize is n, then processors get assigned to partition n consecutive numbers from the to_partition list as part of 1 assignement.
  else {
    int num_range[2];
    MPI_Status stat;
    while(num_range[0]>-1){
      // printf("Rank %d receiving start...\n", rank);
      MPI_Recv(&num_range,2,MPI_INT,0,MPI_ANY_TAG, MPI_COMM_WORLD,&stat); // i believe
      // printf("Rank %d starts with %d\n", rank, start);
      if (stat.MPI_TAG == 1){
        // printf("Rank %d is done\n",rank);
        break;
      } else {
        for (int i = num_range[0]; i < num_range[1];i++){
          // for (int i = (rank-1)*opt.chunksize; i < min(opt.num_to_partition,rank*opt.chunksize); i++){
            int a,b;
            int result = goldbach_partition(opt.to_partition[i],&a,&b);
            if (result==1 && opt.print_results==1){
              // printf("RANK %d ------ %d: %d/%d\n", rank, opt.to_partition[i],a,b);
              printf("%d: %d/%d\n", opt.to_partition[i],a,b);
            }
            else if (result==3){
              printf("omfg\n");
            }
          }
        }
      }

    // printf("Rank %d complete\n", rank);
  }

  MPI_Finalize();


}
