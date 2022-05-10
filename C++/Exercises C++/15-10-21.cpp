#include <vector>
#include <iostream>
#include <algorithm>
#include <list>

using namespace std;

void vector_operations()
{
    vector<string>foods;

    foods.push_back("eggs");
    foods.push_back("milk");
    foods.push_back("sugar");
    foods.push_back("chocolate");
    foods.push_back("flour");

    foods.pop_back();
    foods.push_back("coffee");


    int position = 0;

    for(auto it = foods.begin(); it !=foods.end(); it++)
    {
        if(*it == "sugar")
        {
            foods.erase(foods.begin() + position);
            foods.insert(foods.begin() + position ,"honey");
        }

        if(*it == "milk")
        {
            foods.erase(foods.begin() + position);
        }

        position++;
    }
    sort(foods.begin(), foods.end());

    cout << foods.back() << " back " << foods.front() << " front " << endl;
    for(auto it = foods.begin(); it !=foods.end(); it++)
    {
        cout<<*it<<endl;
    }
}

void sort_vector()
{
    vector<int>numbers;

    for(int i = 0; i < 6; i++)
    {
        cout << "Number:" << endl;
        int number;
        cin >> number;
        numbers.push_back(number);
    }

    sort(numbers.begin(), numbers.end());

    for(int j = 0; j < numbers.size(); j++)
    {
        cout <<numbers[j] <<" ";
    }
}

void list_operations()
{
    list<string> food;
    food.push_back("eggs");
    food.push_back("milk");
    food.push_back("chocolate");
    food.push_back("flour");

    food.pop_front();
    food.push_front("coffee");
    food.push_front("sugar");

    int position = 0;
    auto new_it = food.begin();

    for(auto it = food.begin(); it != food.end(); it++)
    {
        if(*it == "sugar")
        {
            food.remove(*it);
            advance(new_it, position);
            food.insert(new_it,"honey");     
        }

        position++;
    }

    for(auto it = food.begin(); it != food.end(); it++)
    {
        cout<< *it << endl;
    }
}

int find_how_many_X(int position_start, string sentence)
{
    int count_XX;

    for(int i = position_start; sentence.length(); i++)
    {
        if(sentence[i] == 'X')
        {
            count_XX ++;
        }
        else
        {
            break;
        }
    }
    return count_XX;
}

void string_manipulation()
{
    string sentence = "Congratulations Mrs. <name>, you and Mr. <name> are the lucky recipients of a trip for two to XXXXXX. Your trip to XXX is already scheduled.";
    string name_for_replacing = "Luzer";
    string XXX_for_replacing = "Siberia";
    string words_for_add_before_lucky = "un";
    string words_for_concation = " for December";
    int letter_for_replacing,i;

    for(i = 0; sentence[i]!='\0'; i++)
    {
        if(sentence[i] == '<')
        {
            sentence.replace(i, 6,name_for_replacing);
        }
        else if(sentence[i] == 'X')
        {
            letter_for_replacing = find_how_many_X(i,sentence);
            sentence.replace(i,letter_for_replacing,XXX_for_replacing);
        }
    }
    sentence.replace(sentence.find("luck"), 0, words_for_add_before_lucky);
    sentence.replace(i+1,0, words_for_concation);
    cout<<sentence<<endl;
}

void list_to_vector()
{
    list<string>my_list;
    vector<string>my_vector;

    my_list.push_back("eggs");
    my_list.push_back("milk");
    my_list.push_back("sugar");
    my_list.push_back("chocolate");
    my_list.push_back("flour");

    for(auto it = my_list.begin(); it != my_list.end(); it++)
    {
        my_vector.push_back(*it);
    }
    for(auto it = my_vector.begin(); it != my_vector.end(); it++)
    {
        cout<<*it<<endl;
    }
    
}

int main()
{
    string word = "w3recource in 2008 gfg 2r4 wr 4t3 43";
    string new_word = "";
    int total = 0;

    for(int i = 0; i < word.length(); i++)
    {
        if(word[i] >= '0' && word[i] <= '9')
        { 
            while(word[i] >= '0' && word[i] <= '9')
            {
                new_word += word[i];
                i++;
            }
            total += stoi(new_word);
            new_word.clear();
        }
    }
    cout<<total<<endl;
}