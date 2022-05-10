#include <exception>

class indexes_error : public exception
{
public:
    const char* what()
    {
        return "These indexes aren't valid!";
    }
};

class building_over_building_error : public exception
{
public:
    const char* what()
    {
        return "You make try to put building over another one!";
    }
};

class road_error : public exception
{
public:
    const char* what()
    {
        return "The buildint isn't next to the road";
    }
};