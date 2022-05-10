#include "person.hpp"

int Person::get_health_points()
{
    return health_points;
}
int Person::get_shield()
{
    return shield;
}
int Person::get_attack()
{
    return attack_points;
}
void Person::set_health_points(int health_points)
{
    this->health_points = health_points;
}
void Person::set_shield(int shield)
{
    this->shield = shield;
}
void Person::set_attack(int attack)
{
    this->attack_points = attack;
}