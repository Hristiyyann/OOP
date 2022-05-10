#pragma once
#include<exception>
using namespace std;

class allocate_exception : public exception
{
public:
    const char* what()
    {
        return "There is no available space for allocation";
    }
};

class first_address_exception : public exception
{
public:
    const char* what()
    {
        return "You aren't passing pointer to first_address";
    }
};

class grow_size_exception : public exception
{
public:
    const char* what()
    {
        return "You are passing to grow smaller size than the current";
    }
};

class shrink_size_exception : public exception
{
public:
    const char* what()
    {
        return "You are passing to shrink larger size than the current";
    }
};

class grow_allocation_exception : public exception
{
public:
    const char* what()
    {
        return "The pointer can't grow its size";
    }
};