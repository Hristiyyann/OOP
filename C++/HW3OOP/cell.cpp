#include "cell.hpp"
using namespace std;

string Cell::get_type()
{
    return this->cell_type;
}

int Cell::get_maintenance()
{
    return this->maintenance;
}

int Cell::get_energy()
{
    return this->energy;
}