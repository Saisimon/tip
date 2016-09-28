/*
  Author : Saisimon
  读取一行输入作为列标号，该列标号成对出现，最后一负数结尾，表面需要打印的输入行列的范围
  例如 0 3 5 12 -1 表示输入一句话后从第0列到第3列，从第5列到第12列的内容将被打印
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/* 最大列数 */
#define MAX_COLS 20
/* 每个输入行的最大长度 */
#define MAX_INPUT 1000

int read_column_numbers(int columns[], int max);
void readrrange (char *output, const char *input, int n_columns, const int columns[]);

int main(void) {
    /* 进行处理的列标号 */
    int n_columns;
    /* 需要处理的列数 */
    int columns[MAX_COLS];
    /* 容纳输入行的数组 */
    char input[MAX_INPUT];
    /* 容纳输出行的数组 */
    char output[MAX_INPUT];

    /* 读取该串列标号 */
    n_columns = read_column_numbers(columns, MAX_INPUT);

    /* 读取、处理和打印剩余的输入行 */
    while(gets(input) != NULL) {
        printf("Origina Input : %s\n", input);
        readrrange(output, input, n_columns, columns);
        printf("Rearranged Line : %s\n", output);
    }
    return EXIT_SUCCESS;
}

/* 读取列标号，超过最大值则跳过 */
int read_column_numbers(int columns[], int max) {
    int num = 0;
    int ch;
    /* 如果读取的数小于零则停止 */
    while (num < max && scanf("%d", &columns[num]) == 1 && columns[num] >= 0) {
        num++;
    }
    /* 确定读取的标号为偶数个 */
    if (num % 2 != 0) {
        puts("Last column number is not paired.");
        exit(EXIT_FAILURE);
    }
    /* 丢弃最后的字符 */
    while((ch = getchar()) != EOF && ch != '\n');
    return num;
}

/* 处理输入行，将指定列的字符连接起来 */
void readrrange(char *output, const char *input, int n_columns, const int columns[]) {
    /* columns数组下标 */
    int col;
    /* 输出列计数器 */
    int output_col = 0;
    /* 输入行的长度 */
    int len;

    len = strlen(input);

    /* 处理每列标号 */
    for (col = 0; col < n_columns; col += 2) {
        int nchars = columns[col + 1] - columns[col] + 1;
        /* 如果输入行结束或者输出行超过最大输出长度，则结束任务 */
        if (columns[col] >= len || output_col == MAX_INPUT - 1) {
            break;
        }
        /* 如果输出行空间不足，则只复制可以容纳的部分 */
        if (output_col + nchars >= MAX_INPUT - 1) {
            nchars = MAX_INPUT - output_col - 1;
        }
        /* 复制操作 */
        strncpy(output + output_col, input + columns[col], nchars);
        output_col += nchars;
    }
    output[output_col] = '\n';
}
