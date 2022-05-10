#pragma once
#include "cell.hpp"
using namespace std;

class Residential : public Cell
{
protected:
    int people;
public:
    Residential(string residential_type, int maintenance, int energy, int people) : Cell(residential_type, maintenance, energy) { this->people = people; };
    virtual int getResidentCapacity() = 0;
};