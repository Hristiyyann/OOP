#include<iostream>

using namespace std;

class Test
{
public:
    int x, y;
    
    Test() : x(0), y(0) {}
    Test(int X, int Y) : x(X), y(Y) {}

    Test operator+(Test t1)
    {
        Test t3;
        t3.x = this->x + t1.x;
        t3.y = this->y + t1.y;
        return t3;
    }

    friend ostream& operator<<(ostream& Cout, const Test& test)
    {
        Cout << test.x << ", " << test.y << ", " << endl;
        return Cout;
    }
};

int main()
{
    Test t1(2,4), t2(6,7);
    Test t3 = t1+t2;
    Test t4 = t2 + t3;
    cout << t4.x << " " << t4.y << endl;
    cout << t3;

    return 0;
}