#pragma once
#include "residential.hpp"

class DenseResidential : public Residential
{
public:
    DenseResidential() : Residential("DR", 6, 8, 50) {}
    int getResidentCapacity();
};