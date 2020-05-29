#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<math.h>
#include<mpi.h>
#include<unistd.h>

int main(int argc, char** argv){
  MPI_Init(&argc,&argv);

  int num_procs,rank;
  MPI_Comm_size(MPI_COMM_WORLD,&num_procs);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);

  int b,e,mb;
  if (argc < 3 || num_procs%2!=0){
    printf("use correct args (num bytes, num experiments) and even num procs\n");
    return(0);
  }

  mb = atoi(argv[1]);
  e = atoi(argv[2]);
  b = mb * 1024 * 1024;

  if (rank==0){
    printf("COMM_SPEED TESTS\n---------------------\n");
    printf("Data Size: %d MB\nNumber of Experiments: %d\n",mb,e);
  }


  int* buffer = malloc(b); // so a buffer of size b bytes
  double starttime,endtime;
  MPI_Status stat;

  char name[1000];
  gethostname(name,1000);
  printf("Rank %d on machine %s\n", rank, name);

  if (rank%2==0){
    // (1) collect all the speeds
    float my_speed; // (just summed the speeds in my_speed)
    for (int i = 0; i < e; i++){
      starttime = MPI_Wtime();
      MPI_Send(buffer,b,MPI_BYTE,rank+1,0,MPI_COMM_WORLD);
      MPI_Recv(buffer,b, MPI_BYTE, rank+1, MPI_ANY_TAG, MPI_COMM_WORLD,&stat);
      endtime = MPI_Wtime();
      my_speed += mb/(((float)endtime - (float)starttime)/2.0);
    }

    // (2) average the speeds
    my_speed = my_speed/e;

    // (3) rank 0 gets all of the average speeds
    if (rank == 0){
      // receive scores, average them
      float comm_speeds[num_procs/2];
      comm_speeds[0] = my_speed;
      // first get all of the transmission rates
      for (int i = 2; i < num_procs; i+=2){
        float i_speed;
        MPI_Recv(&i_speed, 1, MPI_FLOAT,i,MPI_ANY_TAG,MPI_COMM_WORLD,&stat);
        comm_speeds[i/2] = i_speed;
      }
      // then print them out
      for (int i = 0; i < num_procs/2; i++){
        printf("%d <--> %d Transmission Rate: %lf MB/S\n", 2*i,2*i+1,comm_speeds[i]);
      }
    } else {
      // send rate to rank 0
      MPI_Send(&my_speed,1,MPI_FLOAT,0,0,MPI_COMM_WORLD);
    }

  } else {
    // odd procs receive from rank-1, then send it back
    for (int i = 0; i < e; i++){
      MPI_Recv(buffer,b,MPI_BYTE,rank-1, MPI_ANY_TAG, MPI_COMM_WORLD,&stat);
      MPI_Send(buffer,b,MPI_BYTE,rank-1,0,MPI_COMM_WORLD);
    }
  }

  free(buffer);
  MPI_Finalize();
  return(0);
}
