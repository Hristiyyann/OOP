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
using namespace std;

City::City(unsigned int length, unsigned int width)
{
    this->height = length;
    this->width = width;
    all_cells = new Cell**[length];

    
    for(int i = 0; i < length; i++)
    {
        all_cells[i] = new Cell*[width];
    }

    for(int i = 0; i < length; i++)
    {
        for(int j = 0; j < width; j++)
        {
            all_cells[i][j] = NULL;
        }
    }
}

City::~City()
{
    for(int i = 0; i < height; i++)
    {
        delete[] all_cells[i];
    }
    delete[] all_cells;
} 

vector<int> City::find_indexes(unsigned int x, unsigned int y) // Тази функция връща от кой до кой индекс да се проверява както за реда, така и за колоната.
{
    int row_start, column_start;
    int row_end, column_end;
    vector<int> indexes;
    unsigned int width = 4, height = 6;
    width = width - 1;
    height = height - 1;

    if(x == 0 && y == 0)
    {
        row_start = 0;
        row_end = 1;
        column_start = 0;
        column_end = 1;
    }

    else if(x == height && y == 0)
    {
        row_start = height - 1;
        row_end = x;
        column_start = 0;
        column_end = y + 1;
    }

    else if(x == 0 && y == width)
    {
        row_start = 0;
        row_end = 1;
        column_start = width -1;
        column_end = y;
    }

    else if(x == height && y == width)
    {
        row_start = x - 1;
        row_end = x;
        column_start = y - 1;
        column_end = y;
    }
    
    else if(x == 0)
    {
        row_start = 0;
        row_end = 1;
        column_start = y - 1;
        column_end = y + 1;
    }

    else if(x == height)
    {
        row_start = height - 1 ;
        row_end = x;
        column_start = y - 1;
        column_end = y + 1;
    }

    else if(y == 0)
    {
        row_start = x - 1;
        row_end = x + 1;
        column_start = y;
        column_end = y + 1;
    }

    else if(y == width)
    {
        row_start = x - 1;
        row_end = x + 1;
        column_start = y - 1;
        column_end = y;
    }
    
    else
    {   
        row_start = x - 1;
        row_end = x + 1;
        column_start = y - 1;
        column_end = y + 1;
    }

    indexes.push_back(row_start);
    indexes.push_back(column_start);
    indexes.push_back(row_end);
    indexes.push_back(column_end);
    
    return indexes;
}

void City::update_happiness()
{
    int count_happiness = 0, count_current_happiness = 0;
    vector<int> indexes;
    
    int row_start, column_start;
    int row_end, column_end;

    for(int i = 0; i < height; i++)
    {
        for(int j = 0; j < width; j++)
        {
            if(all_cells[i][j] != NULL)
            {
                if(all_cells[i][j]->get_type() == "ML" || all_cells[i][j]->get_type() == "PK" || all_cells[i][j]->get_type() == "DI" || all_cells[i][j]->get_type() == "LI")
                {
                    indexes = find_indexes(i, j);
                    row_start = indexes.front();
                    indexes.erase(indexes.begin());
                    column_start = indexes.front();
                    indexes.erase(indexes.begin());
                    row_end = indexes.front();
                    indexes.erase(indexes.begin());
                    column_end = indexes.front();
                    indexes.erase(indexes.begin());

                    indexes.clear();
                
                    for(int k = row_start; k <= row_end; k++) 
                    {
                        for(int m = column_start; m <= column_end; m++)
                        {
                            if(all_cells[k][m] != NULL)
                            {
                                if(all_cells[i][j]->get_type() == "ML")
                                {
                                    if(all_cells[k][m]->get_type() == "LR" || all_cells[k][m]->get_type() == "MR" || all_cells[k][m]->get_type() == "DR")
                                    {
                                        count_happiness++;
                                    }
                                }

                                else if(all_cells[i][j]->get_type() == "PK")
                                {
                                    if(all_cells[k][m]->get_type() == "LR") { count_happiness++;}
                                    else if(all_cells[k][m]->get_type() == "MR") { count_happiness += 2;}
                                    else if(all_cells[k][m]->get_type() == "DR") { count_happiness += 3;}
                                }
                                
                                else if(all_cells[i][j]->get_type() == "LI")
                                {
                                    if(all_cells[k][m]->get_type() == "LR" || all_cells[k][m]->get_type() == "MR" || all_cells[k][m]->get_type() == "DR")
                                    {
                                        count_happiness--;
                                    }
                                }
                                else
                                {
                                    if(all_cells[k][m]->get_type() == "LR" || all_cells[k][m]->get_type() == "MR" || all_cells[k][m]->get_type() == "DR" || all_cells[k][m]->get_type() == "ML") { count_happiness-=2;} 
                                }
                            }
                        }
                    }

                    count_current_happiness += count_happiness; 
                    count_happiness = 0;
                }
            }
        }
    }
    total_city_happiness = count_current_happiness;
}

void City::print_pattern()
{
    for(int i = 0; i < height; i++)
    {
        for(int j = 0; j < width; j++)
        {
            if(all_cells[i][j] == NULL)
            {
                cout << "--" << ' ';
            }
            else
            {
                cout << all_cells[i][j]->get_type() << ' ';
            }
        }
        cout << endl;
    }
}

void City::build(unsigned int x, unsigned int y, Cell* cell)
{
    if(!((x >= 0 && x < height) && (y >= 0 && y < width)))
    {
        throw indexes_error();
    }

    if(all_cells[x][y] != NULL)
    {
        throw building_over_building_error();
    }

    vector<int> indexes;
    indexes = find_indexes(x, y);
    
    int row_start, column_start, row_end, column_end;
    bool finded_road = false;

    row_start = indexes.front();
    indexes.erase(indexes.begin());
    column_start = indexes.front();
    indexes.erase(indexes.begin());
    row_end = indexes.front();
    indexes.erase(indexes.begin());
    column_end = indexes.front();
    indexes.erase(indexes.begin());

    if(cell->get_type() == "RD")
    {
        all_cells[x][y] = cell;
        total_city_roads++; total_city_maintenance++; total_city_energy++;
    }

    else
    {
        for(int i = row_start; i <= row_end; i++)
        {
            for(int j = column_start; j <= column_end; j++)
            {
                if(all_cells[i][j] != NULL)
                {
                    if(all_cells[i][j]->get_type() == "RD")
                    {
                        finded_road = true;
                        all_cells[x][y] = cell;
                        total_city_energy += cell->get_energy();
                        total_city_maintenance += cell->get_maintenance();

                        if(cell->get_type() == "LR" || cell->get_type() == "MR" || cell->get_type() == "DR")
                        {
                            Residential *residence = (Residential *)(all_cells[x][y]);
                            total_city_residents += residence->getResidentCapacity();
                        }
                        
                        return;
                    }
                }
            }
        }

        if(finded_road != true)
        {
            throw road_error();
        }
    }
}

float City::getMaintenanceCost()
{
    return total_city_maintenance;
}

int City::getRoadLength()
{
   return total_city_roads;
}

int City::getResidentCapacity()
{
    return total_city_residents;
}

float City::getRequiredPower()
{
    return total_city_energy;
}

int City::getHappiness()
{
    update_happiness();
    return total_city_happiness;
}