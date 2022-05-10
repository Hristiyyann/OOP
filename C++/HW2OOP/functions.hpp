#pragma once
#include<iostream>
#include<vector>
#include<map>
#include<sstream>
#include<fstream>

using namespace std;

class GC
{
private:
    static char* all_memory;
    static map<vector<string>, vector<int>> first_adressess_and_from_position;
    GC() {}
    friend class BaseObject;
public:
    static void initialization();
    static void freing();
    static string make_to_string(void* region_for_making);
    static vector<string> make_addresses_vector(string first_address, string last_address);
    static vector<int> make_positions_vector(int first_position, int last_position);
    static void* allocate(size_t size);
    static void free(void* region);
    static void grow(void* region, size_t size);
    static void shrink(void* region, size_t size);
    static void dump(string file_path);
};

class BaseObject
{
public:
    void* operator new(size_t size);
    void* operator new [](size_t size);
    void operator delete(void* region);
    void operator delete[] (void* region);
};