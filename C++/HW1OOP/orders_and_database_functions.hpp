#ifndef ORDERS_AND_DATABASE_FUNCTIONS_HPP
#define ORDERS_AND_DATABASE_FUNCTIONS_HPP
#include <string>
#include <vector>

using namespace std;

class order
{
private:
    string name_of_piece;
    int serial_number;
    int pieces_for_order;
    int deadline_days; 
public:
    order(string name_of_piece, int serial_number, int pieces_for_order, int deadline_days);  
    string get_piece_name();
    int get_piece_serialNumber();
    int get_piece_pieces_count();
    int get_piece_deadline();   
};

class system_for_orders
{
private:
    vector<class order> orders;
    int max_pieces_for_day; 
public:
    system_for_orders();

    void add_order(order order_to_add);
    
    void print_orders();

    void save_to_file(ofstream& file_for_write);

    void load_from_file(ifstream& file_for_read);

    void free_vector_orders();

    // целта на функцията е да получи като аргументи стойности, с които след това да направи инстанция на класа order и да го добави към вектора, който преди това сме освободили.
    void adding_to_freed_vector(string name, int sernum, int pieces, int deadline); 

    // целта на тази функция е да покаже, че успешно сме въвели стойностите от файла във вектора
    void print_vector_orders_after_load_function(); 

};


#endif