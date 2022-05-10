#include "functions.hpp"
#include "exception.hpp"
#include<string>
#include<vector>
#include <list>
#include <iostream>
#include<fstream>
using namespace std;

vector<string> recipe::get_ingredients()
{
    return ingredients;
}

vector <string> recipe::get_steps()
{
    return steps;
}

string recipe::get_recipe_name()
{
    return recipe_name; 
}

string recipe::get_short_description()
{
    return short_description;
}

bool recipe::operator==(recipe& other)
{
    if(this->recipe_name == other.recipe_name)
    {
        if(this->ingredients == other.ingredients)
        {
            return 1;
        }
    }
    return 0;
}

void bar_system::add(recipe recipe)
{
    if(recipe.get_recipe_name() == " " || recipe.get_ingredients().empty() || recipe.get_steps().empty())
    {
        throw add_exception();
    }

    for(auto i: recipes)
    {
        if(i.get_recipe_name() == recipe.get_recipe_name())
        {
            throw equal_name();
        }
    }
    recipes.push_back(recipe);
}

void bar_system::save(string file_path)
{
    ofstream file(file_path);
    int count_steps = 1;
    if(file.is_open())
    {
        for(auto i: recipes)
        {
            
            file << i.get_recipe_name() << endl;
            file << i.get_short_description() << endl;
            for(auto i:i.get_ingredients())
            {
                file << i << endl;
            }
            for(auto i:i.get_steps())
            {
                file <<"Step - "<< count_steps << ": "<< i<<endl;
                count_steps++;
            }
            count_steps = 1;
            file << endl;
        }

        file.close();
    }
    else
    {
        throw open_error();
    }
}

const list<recipe> bar_system::search(vector<string> ingredients)
{
    if(ingredients.empty())
    {
        throw empty_vector();
    }
    list<recipe> searched_recipes;
    int flag = 1;
    for(auto i: recipes)
    {
        for(auto j: i.get_ingredients())
        {
            for(auto k: ingredients)
            {
                if(j != k)
                {
                    flag = 0;
                    break;
                }
            }
            if(flag )
            {
                searched_recipes.push_back(i);   
            }

            flag = 1;
        }
    }
    return searched_recipes;
}

