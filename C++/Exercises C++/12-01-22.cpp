#include <iostream>
#include <vector>
using namespace std;

class Skill
{
private:
    string skill_name;
public:
    Skill(string skill_name) : skill_name(skill_name) {}
    virtual void apply(Pokemon& self, Pokemon& opponent) = 0;
};

class Pokemon
{
private:
    int current_health_points;
    int attack;
    int defense;
    string name;
    vector<class Skill*> skills;
public:
    Pokemon(int current_health_points, int attack, int defense, string name) : current_health_points(current_health_points), attack(attack), defense(defense), name(name) {}
    virtual void useSkill(Pokemon& opponent, unsigned short skillindex) = 0;
    int get_health_points() { return current_health_points; }
    int get_attack() { return attack; }
    int get_defense() { return defense; }
    string get_name() { return name; }
    vector<class Skill*> get_skills() { return skills; }
    void set_health_points(int health_points) { this->current_health_points = health_points; }
    void set_attack(int attack) { this->attack = attack; }
    void set_defese(int defense) { this->defense = defense; }
    void set_name(string name) { this->name = name; }
    void set_skill(class Skill* skill_for_add) { skills.push_back(skill_for_add); }
};

class StatusEffect
{
public:
    virtual void apply(Pokemon& target) = 0;
};

class Bite : public Skill
{
public:
    void apply(Pokemon& self, Pokemon& opponent)
    {
        opponent.set_health_points(opponent.get_health_points() - 15 - opponent.get_defense());
    }
};

class Pound : public Skill
{
public:
    void apply(Pokemon& self, Pokemon& opponent)
    {
        opponent.set_health_points(self.get_attack() - opponent.get_defense() + 10);
    }
};

class Heal : public Skill
{
public:
    void apply(Pokemon& self, Pokemon& opponent)
    {
        if(10 > self.get_health_points())
        {
            self.set_health_points(self.get_health_points() * 2);
        }
        else
        {
            opponent.set_health_points(self.get_attack() - opponent.get_defense() + 10);
        }
    }
};

class PowerUp : public StatusEffect
{
public:
    void apply(Pokemon& target)
    {
        target.set_attack(target.get_attack() + 15);
    }
};

class PowerDown : public StatusEffect
{
public:
    void apply(Pokemon& target)
    {
        if(target.get_attack() - 10 < 0)
        {
            target.set_attack(0);
        }
        else
        {
            target.set_attack(target.get_attack() - 10);
        }
    }
};


class Charm : public Skill
{
public:
    void apply(Pokemon& self, Pokemon& opponent)
    {
        PowerDown pd;
        pd.apply(opponent);
    }
};

class Mediate : public Skill
{
public:
    void apply(Pokemon& self, Pokemon& opponent)
    {
        PowerUp pu;
        pu.apply(self);
    }
};


class Pikachu : public Pokemon
{
public:
    Pikachu(int current_health_points, int attack, int defense, string name) : Pokemon(current_health_points, attack, defense, name){}
    void useSkill(Pokemon& opponent, unsigned short skillindex);
};

int main()
{
    Pikachu p1(100, 10, 15, "Opredeleno");
    cout << p1.get_attack();
    return 0;
}