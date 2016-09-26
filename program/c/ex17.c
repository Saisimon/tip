/*************************************************************************
    > File Name: ex17.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: 2016年09月23日 星期五 10时29分28秒
 ************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define MAX_DATA 512
#define MAX_ROWS 100

//定长结构体，便于存储和读取
struct Address
{
  int id;
  int set;
  char name[MAX_DATA];
  char email[MAX_DATA];
};

struct Database
{
  struct Address rows[MAX_ROWS];
};

//链接 Database 结构体和文件
struct Connection
{
  FILE *file;
  struct Database *db;
};

//打印错误信息
void
die (const char *message)
{
  if (errno)
    {
	  //打印错误
      perror (message);
    }
  else
    {
      printf ("ERROR:%s\n", message);
    }
  exit (1);
}

//打印 Address
void
Address_print (struct Address *addr)
{
  printf ("%d %s %s \n", addr->id, addr->name, addr->email);
}

//加载 db
//fread()
//buffer 接受数据的地址
//size 为一个单元的大小
//count 为单元个数
//stream 为文件流
//返回实际读取的单元个数
void
Database_load (struct Connection *conn)
{
  int rc = fread (conn->db, sizeof (struct Database), 1, conn->file);
  if (rc != 1)
    die ("Failed to load database");
}

//打开 db
struct Connection *
Database_open (const char *filename, char mode)
{
	//分配 conn
  struct Connection *conn = malloc (sizeof (struct Connection));
  if (!conn)
    die ("Memory error");

	//分配 coon 中的 db
  conn->db = malloc (sizeof (struct Database));
  if (!conn->db)
    die ("Memory error");
	
	//如果 mode 为 c 就写; 否则就 读，并且加载打开
  if (mode == 'c')
    {
      conn->file = fopen (filename, "w");
    }
  else
    {

      conn->file = fopen (filename, "r+");

      if (conn->file)
		{
			Database_load (conn);
		}
    }

  if (!conn->file)
    die ("Failed to open the file");

  return conn;
}

//关掉 db
void
Database_close (struct Connection *conn)
{
	//如果有链接，先关文件conn->file 再释放 conn->db 
  if (conn)
    {
      if (conn->file)
		fclose (conn->file);
      if (conn->db)
		free (conn->db);
      free (conn);
    }
}

void
Database_write (struct Connection *conn)
{
	//将文件指针重新指向文件偷
  rewind (conn->file);
	//将 conn->db 写入文件 conn->file
  int rc = fwrite (conn->db, sizeof (struct Database), 1, conn->file);
  if (rc != 1)
    die ("Failed to write database.");

  //刷新
  rc = fflush (conn->file);
  if (rc == -1)
    die ("Cannot flush database.");
}

//在 conn 中创建 Address
//实际上是给 conn->db 初始化
void
Database_create (struct Connection *conn)
{

  int i = 0;
  for (i = 0; i < MAX_ROWS; i++)
    {
      // make a prototype to initialize it
      struct Address addr = {
		.id = i,
		.set = 0
      };
      // then just assign it
      conn->db->rows[i] = addr;
    }
}

//
void
Database_set (struct Connection *conn, 
			  int id, 
			  const char *name,
	          const char *email)
{
  struct Address *addr = &conn->db->rows[id];
  //更简单的写法
  // struct Address *addr = conn->db->rows+i;
  if (addr->set)
    die ("Already set, delete it first");

  addr->set = 1;
  // WARNING: bug, read the "How To Break It" and fix this
  char *res = strncpy (addr->name, name, MAX_DATA);
  // demonstrate the strncpy bug
  if (!res)
    die ("Name copy failed");

  res = strncpy (addr->email, email, MAX_DATA);
  if (!res)
    die ("Email copy failed");
}

void
Database_get (struct Connection *conn, int id)
{

  struct Address *addr = &conn->db->rows[id];

  if (addr->set)
    {

      Address_print (addr);
    }
  else
    {
      die ("ID is not set");
    }
}

/*
void
Database_find (struct Connection *conn, char* s)
{
	
}
*/

void
Database_delete (struct Connection *conn, int id)
{

  struct Address addr = {
    .id = id,
	.set = 0
  };
  conn->db->rows[id] = addr;
}

void
Database_list (struct Connection *conn)
{

  int i = 0;
  struct Database *db = conn->db;

  for (i = 0; i < MAX_ROWS; i++)
    {

      struct Address *cur = &db->rows[i];

      if (cur->set)
	{

	  Address_print (cur);
	}
    }
}

int
main (int argc, char *argv[])
{

  if (argc < 3)
    die ("USAGE: ex17 <dbfile> <action> [action params]");

  char *filename = argv[1];
  char action = argv[2][0];
  struct Connection *conn = Database_open (filename, action);
  int id = 0;

  if (argc > 3)
    id = atoi (argv[3]);
  if (id >= MAX_ROWS)
    die ("There's not that many records.");

  switch (action)
    {

    case 'c':
      Database_create (conn);
      Database_write (conn);
      break;

    case 'g':
      if (argc != 4)
	die ("Need an id to get");

      Database_get (conn, id);
      break;

    case 's':
      if (argc != 6)
	die ("Need id, name, email to set");

      Database_set (conn, id, argv[4], argv[5]);
      Database_write (conn);
      break;

    case 'd':
      if (argc != 4)
	die ("Need id to delete");

      Database_delete (conn, id);
      Database_write (conn);
      break;

    case 'l':
      Database_list (conn);
      break;
    default:
      die ("Invalid action, only: c=create, g=get, s=set, d=del, l=list");
    }

  Database_close (conn);

  return 0;
}
