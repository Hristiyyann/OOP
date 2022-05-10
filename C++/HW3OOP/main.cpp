#include <iostream>
#include "residential.hpp"
#include "lightresidential.hpp"
#include "mediumresidential.hpp"
#include "denseresidential.hpp"
#include "cell.hpp"
#include "city.hpp"
#include "road.hpp"
#include "leisure.hpp"
#include "mall.hpp"
#include "park.hpp"
#include "industrial.hpp"
#include "denseindustrial.hpp"
#include "lightindustrial.hpp"
#include "exception.hpp"
#include <list>
using namespace std;

struct input_entry_t 
{
    int x;
    int y;
    Cell* cell;
};

int main()
{
    City new_city(6, 4);
    Road r1;
    Cell *cell = (Cell*)(&r1);
    LightResidential l1;
    Cell *cell2 = (Cell*)(&l1);
    MediumResidential m1;
    Cell *cell3 = (Cell*)(&m1);
    DenseResidential d1;
    Cell *cell4 = (Cell*)(&d1);
    Mall ml1;
    Cell *cell5 = (Cell*)(&ml1);
    Park p1;
    Cell *cell6 = (Cell*)(&p1);
    LightIndustrial li1;
    Cell *cell7 = (Cell*)(&li1);
    DenseIndustrial di;
    Cell *cell8 = (Cell*)(&di);

    list<input_entry_t> entries;
    entries.push_back({1,1, cell});
    entries.push_back({2,1, cell});
    entries.push_back({3,1, cell});
    entries.push_back({4,1, cell});
    entries.push_back({4,2, cell});
    entries.push_back({1,0, cell2});
    entries.push_back({2,0, cell2});
    entries.push_back({4,0, cell3});
    entries.push_back({5,0, cell3});
    entries.push_back({5,1, cell3});
    entries.push_back({1,2, cell4});
    entries.push_back({2,2, cell4});
    entries.push_back({3,2, cell5});
    entries.push_back({3,0, cell6});
    entries.push_back({0,0, cell7});
    entries.push_back({0,1, cell7});
    entries.push_back({3,3, cell8});
    entries.push_back({4,3, cell8});
    entries.push_back({5,2, cell8});
   
    for(auto entry : entries)
    {
        try
        {
            new_city.build(entry.x, entry.y, entry.cell);
        }
        catch(indexes_error& error)
        {
            cout << error.what() << endl;
        }
        catch(building_over_building_error& error)
        {
            cout << error.what() << endl;
        }
        catch(road_error& error)
        {
            cout << error.what() << endl;
        }
    }
    
    new_city.print_pattern();
    cout << "Maintenance = "<<new_city.getMaintenanceCost() << endl;
    cout << "Roads = " << new_city.getRoadLength() << endl;
    cout << "Residents = " << new_city.getResidentCapacity() << endl;
    cout << "Energy =  " << new_city.getRequiredPower() << endl;
    cout << "Happiness = "  << new_city.getHappiness() << endl;
    return 0;
}
