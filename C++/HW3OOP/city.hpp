#pragma once
#include "cell.hpp"
#include <vector>

class City
{
private:
    Cell*** all_cells;
    unsigned int width, height;
    float total_city_maintenance = 0, total_city_energy = 0;
    int total_city_roads = 0, total_city_residents = 0, total_city_happiness = 0;
public:
    City(unsigned int length, unsigned int width);
    ~City();
    void print_pattern();
    float getMaintenanceCost();
    int getRoadLength();
    vector<int> find_indexes(unsigned int x, unsigned int y);
    void update_happiness();
    int getResidentCapacity();
    void build(unsigned int x, unsigned int y, Cell* cell);
    float getRequiredPower();
    int getHappiness();
};
