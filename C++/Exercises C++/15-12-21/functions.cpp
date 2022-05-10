#include<iostream>
#include "functions.hpp"

using namespace std;

int Mafioso::get_health_points()
{
    return health_points;
}

int Mafioso::get_shield()
{
    return shield;
}

int Mafioso::get_attack()
{
    return attack_points;
}

void Mafioso::set_health_points(int health_points)
{
    this->health_points = health_points;
}

void Mafioso::set_shield(int shield)
{
    this->shield = shield;
}

void Mafioso::set_attack(int attack)
{
    this->attack_points = attack;
}

int Policeman::get_health_points()
{
    return health_points;
}

int Policeman::get_shield()
{
    return shield;
}

int Policeman::get_attack()
{
    return attack_points;
}

void Policeman::set_health_points(int health_points)
{
    this->health_points = health_points;
}

void Policeman::set_shield(int shield)
{
    this->shield = shield;
}

void Policeman::set_attack(int attack)
{
    this->attack_points = attack;
}

void Policeman::print()
{
    cout << "Policeman - ";
    cout << "Health " << health_points << " Shield " << shield << " Attack " << attack_points << endl;
}
void Mafioso::print()
{
    cout << "Mafioso -";
    cout << "Health " << health_points << " Shield " << shield << " Attack " << attack_points << endl;
}
void Policeman::attack(Mafioso& m)
{
    int value = m.get_health_points() - (attack_points-m.get_shield());
    if(value< 0)
    {
        m.set_health_points(m.get_health_points()-1);
    }
    else
    {
        m.set_health_points(value);
    }

}
void Mafioso::attack(Policeman& p)
{
    int value = p.get_health_points() - (attack_points-p.get_shield());
    if(value< 0)
    {
        p.set_health_points(p.get_health_points()-1);
    }
    else
    {
        p.set_health_points(value);
    }

}

