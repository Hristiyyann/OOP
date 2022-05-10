#pragma once
#include "cell.hpp"
using namespace std;

class Leisure : public Cell
{
protected:
    int happiness = 0;
public:
    Leisure(string leisure_type, int maintenance, int energy) : Cell(leisure_type, maintenance, energy) {}
    virtual int getHappiness() = 0;
};