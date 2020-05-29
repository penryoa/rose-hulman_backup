#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<math.h>
#include<mpi.h>

int main(int argc, char** argv){
  MPI_Init(&argc,&argv);

  int num_procs,rank,val;
  MPI_Comm_size(MPI_COMM_WORLD,&num_procs);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);

  if (rank==0){
    printf("Welcome to the message broadcaster.  Enter an integer to broadcast to all worker nodes.  Enter a negative integer to quit.\n");
    while (1){
      printf("Enter an integer: \n");
      scanf("%d", &val);

      for (int i=1;i<num_procs;i++){
        MPI_Send(&val,1,MPI_INT,i,0,MPI_COMM_WORLD);
      }

      if (val < 0){
        break;
      }
    }
  } else {
    while (1){
      MPI_Status stat;
      MPI_Recv(&val, 1, MPI_INT, 0, MPI_ANY_TAG, MPI_COMM_WORLD,&stat);
      printf("Rank: %d Received Data: %d\n", rank, val);
      if (val < 0){
        break;
      }
    }
  }

  MPI_Finalize();
  return(0);
}
