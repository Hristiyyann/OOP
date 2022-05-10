#include <iostream>
#include <fstream>
#include "orders_and_database_functions.hpp"

using namespace std;

int main()
{
    system_for_orders my_system;  // максималния брой елементи за ден ми е инициализиран в конструктора
    try
    {
        order new_order = order("Kalnik", 782, 10, 2);
        
        my_system.add_order(new_order);
        new_order = order("Bronq", 352, 9, 7);
        my_system.add_order(new_order);
        new_order = order("Prozorec", 234, 3, 6);
        my_system.add_order(new_order);
        new_order = order("Bagajnik", 352, 23, 8);
        my_system.add_order(new_order);
        new_order = order("Vrata", 352, 7, 9);
        my_system.add_order(new_order);

    }
    catch(const char*& error_for_add) 
    {
        cout << error_for_add << endl;
    }
    
    my_system.print_orders();

    try
    {
        ofstream file_for_writing("orders.txt");
        my_system.save_to_file(file_for_writing);
        file_for_writing.close();
    }
    catch(const char*& write_file)
    {
        cout << write_file << endl;
    }

    my_system.free_vector_orders(); // Освобождавам вектора от всички стойностти, за да мога да го заредя отново, след като долу отворя и чета от файла.

    try
    {
        ifstream file_for_reading("orders.txt");
        my_system.load_from_file(file_for_reading);
        file_for_reading.close();
    }
    catch(const char*& read_file)
    {
        cout << read_file << endl;
    }

    my_system.print_vector_orders_after_load_function(); // Принтирам стойностите, които сме прочели от файла и сме вкарали във вектора.
    
    return 0;
}