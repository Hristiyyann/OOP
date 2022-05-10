#pragma once
#include "residential.hpp"

class LightResidential : public Residential
{
public:
    LightResidential() : Residential("LR", 2, 2, 5) {}
    int getResidentCapacity();
};