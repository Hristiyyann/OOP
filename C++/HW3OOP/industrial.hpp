#pragma once
#include "cell.hpp"
using namespace std;

class Industrial : public Cell
{
protected:
    int happiness = 0;
public:
    Industrial(string industrial_type, int maintenance, int energy) : Cell(industrial_type, maintenance, energy) {}
    virtual int getHappiness() = 0;
};