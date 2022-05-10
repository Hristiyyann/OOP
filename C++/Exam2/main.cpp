#include "functions.hpp"
#include "exception.hpp"
#include<iostream>

int main()
{
    try
    {
        bar_system new_system;
        vector<string> vec1;
        vector<string> vec2;

        vec1.push_back("25ml white rum");
        vec1.push_back("25ml vodka");
        vec1.push_back("50ml pineapple juice");
        vec2.push_back("Pour all in glass");
        vec2.push_back("Mix");

        recipe new_recipe = recipe("Some Recipe", "A short example recipe", vec1, vec2);
        new_system.add(new_recipe);

        vec1.clear();
        vec2.clear();

        vec1.push_back("25ml tequila");
        vec1.push_back("25ml whiskey");
        vec1.push_back("50ml vodka");
        vec2.push_back("Put all in shaker with ice");
        vec2.push_back("Shake");
        vec2.push_back("Pour with a slice of lemon");

        recipe second_recipe = recipe("Another Recipe", "Another example recipe", vec1, vec2);
        new_system.add(second_recipe);

        bool is_equal = new_recipe == second_recipe;
        cout <<"Are equal "<<is_equal <<endl;

        new_system.save("Recipes.txt");
        const list<recipe> returned_recipes= new_system.search(vec1);
        for(auto i: returned_recipes)
        {
            cout << i.get_recipe_name();
        }
    }
    catch(add_exception& exception)
    {
       cout << exception.what();
    }
    catch(equal_name& exception)
    {
        cout << exception.what();
    }
    catch(open_error& exception)
    {
        cout << exception.what();
    }
    catch(empty_vector& exception)
    {
        cout << exception.what();
    }

    
    return 0;
}