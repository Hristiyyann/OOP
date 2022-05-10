#include "orders_and_database_functions.hpp"
#include <iostream>
#include <cmath> 
#include <fstream>

order::order(string name_of_piece, int serial_number, int pieces_for_order, int deadline_days)
{
    this->name_of_piece = name_of_piece;
    this->serial_number = serial_number;
    this->pieces_for_order = pieces_for_order;
    this->deadline_days = deadline_days;
}

string order::get_piece_name()
{
    return name_of_piece;
}

int order::get_piece_serialNumber()
{
    return serial_number;
}

int order::get_piece_pieces_count()
{
    return pieces_for_order;
}

int order::get_piece_deadline()
{
    return deadline_days;
}

system_for_orders::system_for_orders()
{
    max_pieces_for_day = 9; // Тук е 9, може да е всяко едно число, просто пробвам с него.
}

void system_for_orders::add_order(order order_to_add)
{
    static int pieces_until_now = 0; // Променливата ми е статик, за да не се забравя колко части е имало досега, 
    //тъй като функцията се вика много пъти и ако не е статик ще пренаписва стойността, вместо да я добави към вече съществуваща.

        if((pieces_until_now + order_to_add.get_piece_pieces_count()) <= (order_to_add.get_piece_deadline() * max_pieces_for_day)) // Това е проверката, с която проверявам дали дадената поръчка, която искам да добавя ще може да се изпълни в срока, който е даден.
        {
            pieces_until_now += order_to_add.get_piece_pieces_count();
            orders.push_back(order_to_add);
        }
        else
        {
            throw "The order with name can't be made in this deadline";
        }
}

void system_for_orders::print_orders()
{
    float all_pieces, days_for_proccessing_the_orders; 
    // Променливата all_pieces e float, за да може по-долу, когато разделя на максималния брой части за ден, 
    //да върне число със запетая, което да закръгля до горното цяло число. Пример - 4.11 -> 5;  5.01 -> 6; 3.00->3;
    // ако all_pieces е int то връща цяло число и след това закръгленото нагоре е същото число, за това float е по-добрия варинт.

    for(auto itr = orders.begin(); itr != orders.end(); itr++)
    {
        all_pieces += (*itr).get_piece_pieces_count();
        cout<<"Name of piece: "<<(*itr).get_piece_name() <<endl;
        cout<<"Serial number: "<<(*itr).get_piece_serialNumber() <<endl;
        cout<<"Pieces' count: "<<(*itr).get_piece_pieces_count() << endl;
        cout<<"Deadline days: "<<(*itr).get_piece_deadline() <<endl;
        cout<<endl; 
    }

    days_for_proccessing_the_orders = ceil(all_pieces / max_pieces_for_day); 
    // тук вече закръглям крайния брой дни. Примерно дори да е 5.01 дена, частите са били изпълнени за 5 цели дни и 0.1 част от 6-ия ден. 
    //За това те общо са направени за 6 дена.
    cout << "All pieces are: "<< all_pieces << endl;
    cout << "The pieces were made for: " << days_for_proccessing_the_orders << " days" << endl;
    cout << "The pieces were made " << orders.back().get_piece_deadline() - days_for_proccessing_the_orders<< " days before the last deadline"<< endl;
    cout << endl;
}

void system_for_orders::save_to_file(ofstream& file_for_write)
{
    if(file_for_write.is_open())
    {
        cout << "Start writing in the file..." << endl;
        
        for(auto it = orders.begin(); it != orders.end(); it++)
        {
            file_for_write << (*it).get_piece_name() << ' ' <<(*it).get_piece_serialNumber() << ' ' << (*it).get_piece_pieces_count() << ' ' << (*it).get_piece_deadline()<<endl;
        }
    }
    else 
    {
        throw "Unable to write in the file";
    }

    cout << "Successfully writing to file!"<<endl;
    cout << endl;
}
void system_for_orders::load_from_file(ifstream& file_for_read)
{
    if(file_for_read.is_open())
    {
        cout << "The file was successfully loaded!"<<endl;
        cout << endl;

        string load_name_of_piece;
        int load_serial_number;
        int load_pieces_for_order;
        int load_deadline_days; 

        while(file_for_read >> load_name_of_piece >> load_serial_number >> load_pieces_for_order >> load_dea dline_days)
        {
            adding_to_freed_vector(load_name_of_piece, load_serial_number, load_pieces_for_order, load_deadline_days); // подавам на функцията всички стойности от един ред
        }
    }
    else
    {
        throw "Unable to load the file";
    }
}

void system_for_orders::adding_to_freed_vector(string name, int sernum, int pieces, int deadline)
{
    order order_to_add_after_load = order(name, sernum, pieces, deadline);
    orders.push_back(order_to_add_after_load);
}

void system_for_orders::free_vector_orders()
{
    orders.clear();
    cout << "The vector is successfully freed!" << endl;
    cout << endl;
}

void system_for_orders::print_vector_orders_after_load_function()
{
    cout << "Loaded values are:"<< endl;
    cout << endl;
    for(auto itr = orders.begin(); itr != orders.end(); itr++)
    {
        cout << (*itr).get_piece_name() << ' ' <<(*itr).get_piece_serialNumber() << ' ' << (*itr).get_piece_pieces_count() << ' ' << (*itr).get_piece_deadline()<<endl;
    }
}
