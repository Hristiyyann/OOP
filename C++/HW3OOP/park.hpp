#pragma once
#include "leisure.hpp"

class Park : public Leisure
{
public:
    Park() : Leisure("PK", 4, 2) {}
    int getHappiness();
};