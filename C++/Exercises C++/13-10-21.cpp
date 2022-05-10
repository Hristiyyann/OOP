#include<iostream>
#include<stdlib.h>

using namespace std;

class node_tree
{
public:
    int data;
    class node_tree* left;
    class node_tree* right;

    node_tree* node_make(int value)
    {   
        node_tree* new_node = (node_tree*)malloc(sizeof(node_tree));
        new_node->data = value;
        new_node->left = NULL;
        new_node->right = NULL;

        return new_node;
    }

    void add_node(node_tree* root, int value)
    {
        if(root->data == value)
        {
            return;
        }

        if(root->data > value)
        {
            if(root->left == NULL)
            {
                root->left = root->node_make(value);
            }
            else
            {
                add_node(root->left, value);
            }
        }
        else
        {
            if(root->right == NULL)
            {
                 root->right = root->node_make(value);
            }
            else
            {
                add_node(root->right, value);
            }
        }
    }

    void print_tree(node_tree* root)
    {
        if (root == NULL)
        {
            return ;
        }
        cout << root->data << endl;
        print_tree(root->left);
        print_tree(root->right);
    }
};




int main()
{
    node_tree* tree = (node_tree*)malloc(sizeof(node_tree));
    tree = tree->node_make(30);
    tree->add_node(tree, 10);
    tree->add_node(tree, 6);
    tree->add_node(tree, 1);
    tree->add_node(tree, 46);
    tree->add_node(tree, 56);
    tree->add_node(tree, 31);
    tree->print_tree(tree);
}