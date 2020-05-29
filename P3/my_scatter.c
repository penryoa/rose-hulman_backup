#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>

void my_scatter(int* sendbuf, int sendcount, int* recvbuf, int recvcount, int root){
  int total_procs;
  int rank;
  MPI_Comm_size(MPI_COMM_WORLD,&total_procs);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);

  if (rank == root){
    // copy data to recvbuf and send out
    for (int i = 0; i < total_procs; i++){
      if (i != root){
        for (int j = 0; j < sendcount; j++){
          recvbuf[j] = sendbuf[i*sendcount + j];
        }
        MPI_Send(recvbuf, sendcount, MPI_INT, i, 0, MPI_COMM_WORLD);
      }
    }
    // copy the data for the root last
    for (int j = 0; j < recvcount; j++){
      recvbuf[j] = sendbuf[root*sendcount + j];
    }

  } else {
    // receive from root
    MPI_Status stat;
    MPI_Recv(recvbuf, recvcount, MPI_INT, root, MPI_ANY_TAG, MPI_COMM_WORLD, &stat);
  }

}
