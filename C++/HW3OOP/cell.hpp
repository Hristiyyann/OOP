#pragma once
#include <string>
#include <iostream>

using namespace std;

class Cell
{
private:
    string cell_type;
    int maintenance;
    int energy;
public:
    Cell(string cell_type, int maintenance, int energy) : cell_type(cell_type), maintenance(maintenance), energy(energy) {};
    Cell(){}
    string get_type();
    int get_maintenance();
    int get_energy();
};