#include "functions.hpp"
#include "exception.hpp"
#include <map>
#include <vector>
#include <iostream>
#include <sstream>
#include <fstream>

using namespace std;


char* GC::all_memory = new char[1024];
map<vector<string>, vector<int>> GC::first_adressess_and_from_position;

void GC::initialization()
{
    for(int i = 0; i < 1024; i++)
    {
        all_memory[i] = 48;
    }
}

void GC::freing()
{
    delete[] all_memory;
}


string GC::make_to_string(void* region_for_making)
{
    ostringstream string_region;
    string_region << (void*)region_for_making;
    return string_region.str();
}

vector<string> GC::make_addresses_vector(string first_address, string last_address)
{
    vector<string> addresses_for_add;
    addresses_for_add.push_back(first_address);
    addresses_for_add.push_back(last_address);
    return addresses_for_add;
}

vector<int> GC::make_positions_vector(int first_position, int last_position)
{
    vector<int> positions_for_add;
    positions_for_add.push_back(first_position);
    positions_for_add.push_back(last_position);
    return positions_for_add;
}

void* GC::allocate(size_t size)
{
    int first_free_address = -1, last_free_address = -1;
    int free_addressess_in_a_row = 0;
    int i;
    
    for(i = 0; i < 1024; i++)
    {
        if(all_memory[i] == 48)
        {
            if(free_addressess_in_a_row == 0)
            {
                first_free_address = i;
            }
            free_addressess_in_a_row++;
        }
        else
        {
            free_addressess_in_a_row = 0;
            first_free_address = -1;
        }
        if(free_addressess_in_a_row == (int)size)
        {
            last_free_address = i;
            break;
        }
    }

    if(first_free_address == -1)
    {
        throw allocate_exception();
    }

    for(i = first_free_address; i < first_free_address + (int)size; i++)
    {
        all_memory[i] = 49;
    }

    void* new_allocation = new (all_memory+first_free_address) void*[size];

    string first_address = make_to_string((void*)new_allocation);
    string last_address = make_to_string((void*)(all_memory+last_free_address));

    vector<int> first_and_last_positions = make_positions_vector(first_free_address, last_free_address);
    vector<string> addresses = make_addresses_vector(first_address, last_address);

    first_adressess_and_from_position.insert({addresses,first_and_last_positions});
    return new_allocation;
}

void GC::free(void* region)
{
    string first_address_to_string = make_to_string(region);
    int finded_first_address = 0;

    for(auto j:first_adressess_and_from_position)
    {
        if(j.first[0] == first_address_to_string)
        {
            finded_first_address = 1;

            for(int i = j.second[0]; i<=j.second[1]; i++)
            {
                all_memory[i] = 48;
            }
            first_adressess_and_from_position.erase(j.first);
            break; 
        }
    }
    
    if(finded_first_address == 0)
    {
        throw first_address_exception();
    }
}

void GC::grow(void* region, size_t size)
{
    string first_address_to_string = make_to_string(region);
    int size_for_growing = 0, total_current_size = 0, last_position = -1, finded_first_address = 0;

    for(auto& j:first_adressess_and_from_position)
    {
        if(j.first[0] == first_address_to_string)
        {
            if((int)size < j.second[1]- j.second[0] + 1)
            {
                throw grow_size_exception();
            }

            finded_first_address = 1;

            size_for_growing = (int)size  - (j.second[1]-j.second[0]) - 1;
            for(int i = j.second[1] + 1; i <= 1024; i++)
            {
                if(all_memory[i] == 48)
                {
                    total_current_size++;
                }
                else
                {   
                    throw grow_allocation_exception();
                }
                if(total_current_size == size_for_growing)
                {
                    last_position = i;
                    break;
                }
            }

            for(int i = j.second[1]; i <= last_position; i++)
            {
                all_memory[i] = 49;
            }

            string new_first_address = j.first[0];
            string new_last_address = make_to_string(all_memory+last_position);
            int new_from_position = j.second[0];
            int new_last_position = last_position;
        
            vector<string> old_addresses = make_addresses_vector(j.first[0], j.first[1]);
            vector<string> new_addresses = make_addresses_vector(new_first_address, new_last_address);
            vector<int> new_positions = make_positions_vector(new_from_position, new_last_position);

            first_adressess_and_from_position.erase(old_addresses);
            first_adressess_and_from_position.insert({new_addresses, new_positions});
            break;
        }
        
    }

    if(finded_first_address == 0) 
    {
        throw first_address_exception();
    }
}

void GC::shrink(void* region, size_t size)
{
    string first_address_to_string = make_to_string(region);
    int finded_first_address = 0;
    for(auto &j:first_adressess_and_from_position)
    {
        if(j.first[0] == first_address_to_string)
        {
            if((int)size > j.second[1]- j.second[0] + 1)
            {
                throw shrink_size_exception();
            }

            finded_first_address = 1;
            for(int i = j.second[0] + int(size); i<=j.second[1]; i++)
            {
                all_memory[i] = 48;
            }

            string new_first_address = j.first[0];
            string new_last_address = make_to_string(all_memory+size-1);
            int new_from_position = j.second[0];
            int new_last_position = j.second[0] + size-1;
            
            vector<int> new_first_and_last_positions = make_positions_vector(new_from_position, new_last_position);
            vector<string> new_addresses = make_addresses_vector(new_first_address, new_last_address);
            vector<string> old_addresses = make_addresses_vector(j.first[0], j.first[1]);

            first_adressess_and_from_position.erase(old_addresses);
            first_adressess_and_from_position.insert({new_addresses,new_first_and_last_positions});
            break;
        }
    }

    if(finded_first_address == 0) 
    {
        throw first_address_exception();
    }
}

void GC::dump(string file_path)
{
    ofstream file(file_path);
    int total_reserved_bytes = 0;

    for(auto i: first_adressess_and_from_position)
    {
        total_reserved_bytes += i.second[1] - i.second[0] + 1;
    }
    file << total_reserved_bytes << " / 1024" << endl;

    for(auto i: first_adressess_and_from_position)
    {
        file << i.first[0] << " - " << i.first[1] << " (" << i.second[1]-i.second[0]+1 << ")"<<endl;
    }
}  

void* BaseObject::operator new(size_t size)
{
    return GC::allocate(size);
}

void* BaseObject::operator new [](size_t size)
{
    return GC::allocate(size);
}

void BaseObject::operator delete(void* region)
{
    GC::free(region);
}

void BaseObject::operator delete[] (void* region)
{
    GC::free(region);
}
