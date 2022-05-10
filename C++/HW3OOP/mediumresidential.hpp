#pragma once
#include "residential.hpp"

class MediumResidential : public Residential
{
public:
    MediumResidential() : Residential("MR", 4, 4, 20) {}
    int getResidentCapacity();
};