#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <errno.h>
#include <stdlib.h>

#define MAX_DATA 512
#define DEFAULT_MAX_ROWS 100

struct Address {
    int id;
    int set;
    char name[MAX_DATA];
    char email[MAX_DATA];
};

struct Database {
    int max_rows;
    struct Address rows[];
};

struct Connection {
    FILE *file;
    struct Database *db;
};

void Database_close(struct Connection *conn) {
    if (conn) {
        if (conn -> file) {
            fclose(conn -> file);
        }
        if (conn -> db) {
            free(conn -> db);
        }
        free(conn);
    }
}

void die(struct Connection *conn, char *message) {
    if (errno) {
        perror(message);
    } else {
        printf("ERROR : %s \n", message);
    }
    Database_close(conn);
    exit(1);
}

void Address_print(struct Address *addr) {
    printf("id : %d, name : %s, email : %s\n", addr -> id, addr -> name, addr -> email);
}

void Database_load(struct Connection *conn) {
    int rc = fread(conn -> db, sizeof(struct Database), 1, conn -> file);
    if (rc != 1) {
        die(conn, "Load Database Fail!");
    }
}

struct Connection *Database_open(const char *filename, char mode, int max_rows) {
    struct Connection *conn = malloc(sizeof(struct Connection));
    if (!conn) {
        die(conn, "New Connection Fail!");
    }
    conn -> db = malloc(sizeof(struct Database) + max_rows * sizeof(struct Address));
    if (!conn -> db) {
        die(conn, "New Database Fail!");
    }
    conn -> db -> max_rows = max_rows;
    if (mode == 'c') {
        conn -> file = fopen(filename, "w");
    } else {
        conn -> file = fopen(filename, "r+");
        if (conn -> file) {
            Database_load(conn);
        }
    }
    if (!conn -> file) {
        die(conn, "Load File Fail!");
    }
    return conn;
}

void Database_write(struct Connection *conn) {
    rewind(conn -> file);
    int rc = fwrite(conn -> db, sizeof(struct Database), 1, conn -> file);
    if (rc != 1) {
        die(conn, "Write Database Fail!");
    }
    rc = fflush(conn -> file);
    if (rc == -1) {
        die(conn, "Flush File Fail!");
    }
}

void Database_create(struct Connection *conn) {
    int max_rows = conn -> db -> max_rows;
    for (int i = 0; i < max_rows; i++) {
        struct Address addr = {.id = i, .set = 0};
        conn -> db -> rows[i] = addr;
    }
}

void Database_set(struct Connection *conn, int id, char *name, char *email) {
    struct Address *addr = &conn -> db -> rows[id];
    if (addr -> set) {
        die(conn, "This ID Exist!");
    }
    addr -> set = 1;
    char *res = strncpy(addr -> name, name, MAX_DATA);
    if (!res) {
        die(conn, "Copy Name Fail!");
    }
    res = strncpy(addr -> email, email, MAX_DATA);
    if (!res) {
        die(conn, "Copy Email Fail!");
    }
}

void Database_get(struct Connection *conn, int id) {
    struct Address *addr = &conn -> db -> rows[id];
    if (addr -> set) {
        Address_print(addr);
    } else {
        die(conn, "This ID Not Exist!");
    }
}

void Database_delete(struct Connection *conn, int id) {
    struct Address addr = {.id = id, .set = 0};
    conn -> db -> rows[id] = addr;
}

void Database_list(struct Connection *conn) {
    struct Database *db = conn -> db;
    int max_rows = db -> max_rows;
    for (int i = 0; i < max_rows; i++) {
        struct Address *cur = &db -> rows[i];
        if (cur -> set) {
            Address_print(cur);
        }
    }
}

int main(int argc, char *argv[]) {
    if (argc < 3) {
        printf("USAGE: ex17 <dbfile> <mode> [mode params]\n");
        exit(1);
    }
    char *filename = argv[1];
    char mode = argv[2][0];
    int id = 0;
    if (argc > 3) {
        id = atoi(argv[4]);
    }
    int max_rows = 0;
    if (argc == 4) {
        max_rows = atoi(argv[4]);
    }
    if (!max_rows) {
        max_rows = DEFAULT_MAX_ROWS;
    }
    struct Connection *conn = Database_open(filename, mode, max_rows);
    switch (mode) {
    case 'c': {
        Database_create(conn);
        Database_write(conn);
        printf("Database Create Success! path : %s\n", filename);
        break;
    }
    case 'g': {
        if (argc != 4) {
            die(conn, "Need ID to get!");
        }
        if (id >= conn -> db -> max_rows) {
            die(conn, "ID Error!");
        }
        Database_get(conn, id);
        break;
    }
    case 's': {
        if (argc != 6) {
            die(conn, "Need ID, Name, Email to set!");
        }
        if (id >= conn -> db -> max_rows) {
            die(conn, "ID Error!");
        }
        Database_set(conn, id, argv[4], argv[5]);
        Database_write(conn);
        printf("Database Set Success!\n");
        break;
    }
    case 'd': {
        if (argc != 4) {
            die(conn, "Need ID to delete");
        }
        if (id >= conn -> db -> max_rows) {
            die(conn, "ID Error!");
        }
        Database_delete(conn, id);
        Database_write(conn);
        printf("Database Delete %d Success!\n", id);
        break;
    }
    case 'l': {
        Database_list(conn);
        break;
    }
    default:
        die(conn, "Invalid Mode, only supports c=create, s=set, g=get, d=delete, l=list");
        break;
    }
    Database_close(conn);
    return 0;
}
