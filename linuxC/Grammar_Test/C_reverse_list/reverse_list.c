/*************************************************************************
    > File Name: reverse_list.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: Mon 20 Feb 2017 10:33:09 AM CST
 ************************************************************************/

#include <stdio.h>

typedef struct node_s{

int item;

struct node_s* next;

}node_t;


node_t* reverse_list(node_t* head)
{

	node_t* n=head;

	head=NULL;
	while(n){

		node_t* m=n; 
		n=n->next; 
		m->next=head; 
		head=m;
	}

/*
	if (head == NULL || head->next == NULL)
	{
		return head;
	}

	//head
	//current|current->next  ---> pnext|pnext->next ---> prev|prev->next

	node_t* current = head->next;
	node_t* pnext = current->next;
	node_t* prev = NULL;

	while(pnext)
	{
		prev = pnext->next;
		pnext->next = current;
		current = pnext;
		pnext = prev;
	}
	head->next = current;
*/	

	return head;
}


void print_list(node_t* head)
{
	while(head){
		printf("%d ",head->item);
		head = head->next;
	};
}


int main(){
	node_t alphabetA_;
	node_t alphabetB_;
	node_t alphabetC_;

	node_t *alphabetA = &alphabetA_;
	node_t *alphabetB = &alphabetB_;
	node_t *alphabetC = &alphabetC_;

	alphabetA->item = 1;
	alphabetB->item = 2;
	alphabetC->item = 3;


	alphabetA->next = alphabetB;
	alphabetB->next = alphabetC;
	alphabetC->next = NULL;

	
	print_list(alphabetA);

	print_list(reverse_list(alphabetA));

    return 0;
}
