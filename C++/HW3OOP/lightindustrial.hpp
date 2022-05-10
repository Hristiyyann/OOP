#pragma once
#include "industrial.hpp"

class LightIndustrial : public Industrial
{
public:
    LightIndustrial() : Industrial("LI", 4, 4) {}
    int getHappiness();
};

