#include<iostream>
#include<stdlib.h>

using namespace std;

class node_list
{
public:
    int value;
    class node_list* next;
    class node_list* prev;
};

class my_list
{
public:
    int size;
    class node_list* next;
    class node_list* head;

    my_list(my_list* list)
    {
        list->next = NULL;
        list->head = NULL;
        list->size = 0;
    }

    class node_list* new_nodes(my_list* list,int value)
    {
        node_list* node =(node_list*)malloc(sizeof(node_list*));
        node->value = value;
        node->next = NULL;
        node->prev = NULL;

        return node;
    }
    void insert_nodes(my_list* list, int value)
    {
        if(list->size == 2147483647)
        {
            throw out_of_range("NO");            
        }

        node_list* node_for_insert = new_nodes(list, value);
            
        if (node_for_insert == NULL)
        {
            throw bad_alloc();
        }

        if (list->next != NULL)
        {
            list->next->prev = node_for_insert;
            node_for_insert->next = list->next;
        }
        list->next = node_for_insert;
        list->size++;
        
    }

    void print_nodes(my_list* list)
    {
        node_list* trav = list->next;

        while(trav!=NULL)
        {
            cout << trav->value << endl;
            trav = trav->next;
        }
    }

    void free_list(my_list* list)
    {
        node_list* trav = list->next;
        node_list* deleting = list->next;

        while(trav!=NULL)
        {
            deleting = trav->next;
            free(trav);
            trav = deleting;
        }
        list->size = 0;
    }

    void sort_list(my_list* list)
    {
        node_list* trav = list->next;
        node_list* for_check;
        int number_for_swap;

        while(trav!=NULL)
        {
            for_check = trav->next;
            
            while(for_check!=NULL)
            {
                if(for_check->value == trav->value)
                {
                    throw "There is 2 elements with equal value";
                }
                if (for_check->value < trav->value)
                {
                    number_for_swap = for_check->value;
                    for_check->value = trav->value;
                    trav->value = number_for_swap;
                }
                for_check = for_check->next;
            }
            trav = trav->next;
        }
    }

};

int main()
{
    my_list* list = (my_list*)malloc(sizeof(my_list));
    list->next = NULL;
    list->head = NULL;
    list->size = 0;
    try
    {
        list->insert_nodes(list,10);
        list->insert_nodes(list,79);
        list->insert_nodes(list,1);
        list->insert_nodes(list,56);
        list->insert_nodes(list,23);
        list->sort_list(list);
        list->print_nodes(list);
        list->free_list(list);
    }
    catch (bad_alloc& ex)
    {
        cout << "No memory" << endl;
    }

    catch(out_of_range& ex)
    {
        cout << "Out of range" << endl;
    }
    catch(const char* ex)
    {
        cout << "Error: " << ex << endl;
    }
}