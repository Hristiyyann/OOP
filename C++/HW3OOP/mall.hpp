#pragma once
#include "leisure.hpp"

class Mall : public Leisure
{
public:
    Mall() : Leisure("ML", 8, 4) {}
    int getHappiness();
    void set_happiness(int happiness);
};