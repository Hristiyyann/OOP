#pragma once
#include <exception>
using namespace std;

class add_exception : public exception
{
public:
    const char* what()
    {
        return "You can't add recipe like this";
    }
};

class equal_name : public exception
{
public:
    const char* what()
    {
        return "You are adding recipe with eqaul name";
    }
};

class open_error : public exception
{
public:
    const char* what()
    {
        return "The file can't be open ";
    }
};

class empty_file : public exception
{
public:
    const char* what()
    {
        return "The file is empty";
    }
};

class empty_vector : public exception
{
public:
    const char* what()
    {
        return "The vector is empty";
    }
};
