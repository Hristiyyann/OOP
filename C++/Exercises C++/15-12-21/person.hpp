#pragma once

class Person
{
protected:
    int health_points;
    int shield;
    int attack_points;
public:
    Person(int health_points, int shield, int attack) : health_points(health_points), shield(shield), attack_points(attack) {}
    int get_health_points();
    int get_shield();
    int get_attack();
    void set_health_points(int health_points);
    void set_shield(int shield);
    void set_attack(int attack);
};