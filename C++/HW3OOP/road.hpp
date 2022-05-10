#pragma once
#include "cell.hpp"
using namespace std;


class Road : public Cell
{
private:
    int length = 1;
public:
    Road() : Cell("RD", 1, 1) {}
    int get_length();
};