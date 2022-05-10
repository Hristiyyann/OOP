#include<iostream>
using namespace std;
#include "functions.hpp"

void fight(Mafioso& m, Policeman& p)
{
    int value; 
    while(1)
    {
        value  = m.get_health_points() - (p.get_attack() - m.get_shield());
        if(value< 0)
        {
            m.set_health_points(p.get_health_points() - 1);
        }
        else
        {
            m.set_health_points(value);
        }
        
        if(m.get_health_points() == 0)
        {
            break;
        }

        value  = p.get_health_points() - (m.get_attack() - p.get_shield());

        if(value< 0)
        {
            p.set_health_points(m.get_health_points() - 1);
        }
        else
        {
            p.set_health_points(value);
        }

        if(p.get_health_points() == 0)
        {
            break;
        }
    }
}


int main()
{
    Mafioso mafiot(20, 2, 5);
    Policeman policeman(25, 4, 5);
    policeman.attack(mafiot);
    mafiot.attack(policeman);
    mafiot.print();
    policeman.print();

    fight(mafiot, policeman);
    mafiot.print();
    policeman.print();

    return 0;
}


