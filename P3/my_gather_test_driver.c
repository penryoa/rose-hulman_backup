#include<stdio.h>
#include<stdlib.h>
#include<mpi.h>
#include<string.h>
#include<time.h>

void my_gather(int* recvbuf, int recvcount, int* sendbuf,int sendcount, int root);
void make_int_array_str(int* arr, int len, char** str,int maxprintlen);

typedef struct{
  int seed;
  int* data;
  int data_len;
  int data_forced;
  int seed_forced;
  int root;
}opts;

void parse_opts(int argc, char** argv, opts* o){


  o->seed=-1;
  o->data_len=0;
  o->data=0;
  o->seed_forced=0;
  o->root=0;
  o->data_forced=0;
  int i;
  int dummyi;
  int data_buffer_size=0;
  for (i=1;i<argc;i++){
    if (sscanf(argv[i],"--seed=%d",&dummyi)==1){
      o->seed=dummyi;
      if (o->data_forced){
	fprintf(stderr,"Error, you may not specify both the seed and the data\n");
	exit(-1);
      }
      o->seed_forced=1;
    }
    else if (sscanf(argv[i],"--len=%d",&dummyi)==1){
      o->data_len=dummyi;
      if ( o->data_forced){
	fprintf(stderr,"Error, data len may not be specified if the data array is specified.\n");
	exit(-1);
      }
    }
    else if (sscanf(argv[i],"--root=%d",&dummyi)==1){
	o->root=dummyi;
      }
    else if (strncmp(argv[i],"--data",6)==0){
      o->data_forced=1;
      if (o->seed_forced){
	fprintf(stderr,"Error, you may not specify both the seed and the data\n");
	exit(1);
      }
      o->data=malloc(10*sizeof(int));
      data_buffer_size=10;
      int j;
      for (j=i+1;j<argc;j++){
	if (o->data_len == data_buffer_size){
	  o->data=realloc(o->data,2*data_buffer_size*sizeof(int));
	  data_buffer_size*=2;
	}
	o->data[o->data_len]=atoi(argv[j]);
	o->data_len+=1;
      }
      break;
    }
  }
  
  
}


void init_data(opts* o,int** tosend, int* lenperproc, int** torecv){

  int rank;
  int size;
  MPI_Comm_size(MPI_COMM_WORLD,&size);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);

  *torecv=NULL;
  if (o->data_forced){
    *lenperproc=o->data_len/size;
    *tosend=malloc(*lenperproc*sizeof(int));
    memcpy(*tosend,o->data+*lenperproc*rank,*lenperproc*sizeof(int));
    
  }

  else{
    srand(o->seed);
    int data_len;
    if (o->data_len==0){
      data_len=rand()%100;
    }
    else{
      data_len=o->data_len;
    }
    *lenperproc=data_len/size;
    srand(o->seed+rank);
    *tosend=malloc(*lenperproc*sizeof(int));
    for (int i=0;i<*lenperproc;i++){
      (*tosend)[i]=rand()%500;
    }
    
      
  }

  if (rank==o->root){
    *torecv=malloc(sizeof(int)*(*lenperproc)*size);
  }
}
 

int num_sends=0;
int** send_records=NULL;
int send_records_len=0;


int num_recvs=0;
int** recv_records=NULL;
int recv_records_len=0;

int __wrap_main(int argc,char** argv){

  MPI_Init(&argc,&argv);

  int rank;
  int size;
  MPI_Comm_size(MPI_COMM_WORLD,&size);
  MPI_Comm_rank(MPI_COMM_WORLD,&rank);
  
  int* send_buff;
  int* recv_buff;
  int lenperproc;


  
  opts o;
  parse_opts(argc,argv,&o);

  init_data(&o,&send_buff,&lenperproc,&recv_buff);

  int sendcount=lenperproc;
  int recvcount=rank==o.root?lenperproc:0;


  char* sendbufstr,*recvbufstr;

  make_int_array_str(send_buff,sendcount,&sendbufstr,20);
  make_int_array_str(recv_buff,recvcount*size,&recvbufstr,20);

  if (rank==o.root){
    printf("Engaging in my_gather\nroot:%d\n",o.root);
  }

  printf("Rank %d calls my_gather with:\nsend_buf:%s\nsend_count:%d\nrecv_buf:%s\nrecv_count:%d\nroot:%d\n\n",rank,sendbufstr,sendcount,recvbufstr,recvcount,o.root);
  
  my_gather(recv_buff,recvcount,send_buff,sendcount,o.root);

  
  char* result_str;
  if (rank==o.root){
    make_int_array_str(recv_buff,lenperproc*size,&result_str,40);
    printf("Rank %d ends up with:\nrecv_buff: %s\n\n",rank,result_str);
  }

  char filename[1000];
  sprintf(filename,"whatidid.rank.%d.txt",rank);
  FILE* fp=fopen(filename,"w");
  fprintf(fp,"total sends: %d\n",num_sends);
  fprintf(fp,"No.\tTo\tData\n");
  
  for (int i=0;i<num_sends;i++){
    make_int_array_str(send_records[i]+2,send_records[i][1],&result_str,40);
    fprintf(fp,"%d\t%d\t%s\n",i,send_records[i][0],result_str);
  }

  fprintf(fp,"total recvs: %d\n",num_recvs);
  for (int i=0;i<num_recvs;i++){
    make_int_array_str(recv_records[i]+2,recv_records[i][1],&result_str,lenperproc*size);
    fprintf(fp,"%d\t%d\t%s\n",i,recv_records[i][0],result_str);
  }

  
  if (rank==o.root){
    make_int_array_str(recv_buff,lenperproc*size,&result_str,lenperproc*size);
    fprintf(fp,"Final Data: %s\n", result_str);
  }
  fclose(fp);

  MPI_Finalize();
  exit(MPI_SUCCESS);
  
}


