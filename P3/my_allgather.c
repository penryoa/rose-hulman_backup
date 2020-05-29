#include <mpi.h>
#include <stdlib.h>
#include <stdio.h>

void my_allgather(int* sendbuf, int sendcount, int* recvbuf){
  int np;
  int rank;
  MPI_Comm_size(MPI_COMM_WORLD,&np);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);

  // send to the right,  recv from the left
  int send_to = (rank+1)%np;
  int recv_from = (np+rank-1)%np;

  // copy its own data into the recvbuf
  for (int j = 0; j < sendcount; j++){
    recvbuf[rank*sendcount + j] = sendbuf[j];
  }

  for (int i = 0; i < np-1; i++){
    int send_start = (np+rank-i)%np; // the data sent starts at rank-i
    int recv_start = (np+recv_from-i)%np; // the data received starts at the previous recv - 1

    // to avoid deadlock, we interleave the sends and recvs
    if (rank%2==0){
      // printf("------ %da ---- Rank %d recv %d data from rank %d\n", i, rank, recv_start, recv_from);
      // printf("------ %db ---- Rank %d send %d data to rank %d\n", i, rank, send_start, send_to);
      MPI_Recv(recvbuf+(recv_start*sendcount), sendcount, MPI_INT, recv_from, MPI_ANY_TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
      MPI_Send(recvbuf+(send_start*sendcount), sendcount, MPI_INT, send_to, 0, MPI_COMM_WORLD);
    } else {
      // printf("------ %da ---- Rank %d send %d data to rank %d\n", i, rank, send_start, send_to);
      // printf("------ %db ---- Rank %d recv %d data from rank %d\n", i, rank, recv_start, recv_from);
      MPI_Send(recvbuf+(send_start*sendcount), sendcount, MPI_INT, send_to, 0, MPI_COMM_WORLD);
      MPI_Recv(recvbuf+(recv_start*sendcount), sendcount, MPI_INT, recv_from, MPI_ANY_TAG, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
    }
  }
}
