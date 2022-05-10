#pragma once

class Mafioso;
class Policeman;

class Mafioso
{
protected:
    int health_points;
    int shield;
    int attack_points;
public:
    Mafioso(int health_points, int shield, int attack) : health_points(health_points), shield(shield), attack_points(attack) {}
    int get_health_points();
    int get_shield();
    int get_attack();
    void set_health_points(int health_points);
    void set_shield(int shield);
    void set_attack(int attack);
    virtual void attack(Policeman& p);
    void print();
};

class Policeman
{
protected:
    int health_points;
    int shield;
    int attack_points;
public:
    Policeman(int health_points, int shield, int attack) : health_points(health_points), shield(shield), attack_points(attack) {}
    int get_health_points();
    int get_shield();
    int get_attack();
    void set_health_points(int health_points);
    void set_shield(int shield);
    void set_attack(int attack);
    virtual void attack(Mafioso& m);
    void print();
}; 

class ToughGuy : public Mafioso
{
public:
    ToughGuy(int health_points, int shield, int attack) : Mafioso(health_points, shield, attack) {}
    //void attack(Policeman& p);

};

class Specialist : public Mafioso
{
private:
    int bombs_power = 4;
};

class Enforcer : public Policeman
{

};

class Swat : public Policeman
{

};