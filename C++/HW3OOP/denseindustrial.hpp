#pragma once
#include "industrial.hpp"

class DenseIndustrial : public Industrial
{
public:
    DenseIndustrial() : Industrial("DI", 6, 8) {}
    int getHappiness();
};