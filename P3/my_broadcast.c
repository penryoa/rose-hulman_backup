#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int pow2(int n){
  return (int)(pow(2,n) + 0.5);
}

void my_broadcast(int* buffer, int count){
  int total_procs;
  int rank;
  MPI_Comm_size(MPI_COMM_WORLD,&total_procs);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);

  int j = 0;
  // if (rank!=0) {j = floor(log2(rank));}
  int max_j = floor(log2(total_procs));

  // printf("rank: %d\tj=%d\tmaxj=%d\n", rank, j, max_j);

  if (rank != 0){
    j = floor(log2(rank));
    // printf("----- %d - Rank %d receive from Rank %d\n", j, rank, rank - pow2(j));
    MPI_Recv(buffer, count, MPI_INT, rank - pow(2,j), MPI_ANY_TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
    j++;
  }

  for (j; j < max_j; j++){
    // printf("----- %d - Rank %d send to Rank %d\n", j, rank, rank + pow2(j));
    MPI_Send(buffer, count, MPI_INT, rank + pow(2,j), 0, MPI_COMM_WORLD);
  }
}
