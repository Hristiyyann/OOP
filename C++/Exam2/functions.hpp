#pragma once
#include<string>
#include<vector>
#include <list>
using namespace std;

class recipe
{
private:
    string recipe_name;
    string short_description;
    vector<string> ingredients;
    vector<string> steps;
public:
    string get_recipe_name();
    string get_short_description();
    vector <string> get_ingredients();
    vector <string> get_steps();
    recipe(string recipe_name, string short_description, vector<string> ingredients, vector<string>steps) : recipe_name(recipe_name), short_description(short_description), ingredients( ingredients), steps(steps) {}
    bool operator == (recipe &other);
};

class bar_system
{
private:
    vector<class recipe> recipes;
public:
    void add(recipe recipe);
    void save(string file_path);
    void load(string file_path);
    const list<class recipe> search(vector<string> ingredients);
    const list<class recipe> extended_search(vector<string> ingredients); 
};

