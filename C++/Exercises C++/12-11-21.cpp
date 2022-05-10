#include<iostream>
#include<vector>
using namespace std;

class Point
{
private:
    int X, Y;
public:
    const int getX()
    {
        return X;
    }
    const int getY()
    {
        return Y;
    }

    Point(int x, int y) : X(x), Y(y) {}
    Point() : Point(0,0) {}
    Point(const Point &for_copy) : X(for_copy.X), Y(for_copy.Y) {}

    void print()
    {
        cout << X << " " << Y << endl;
    }
};

class Mesh
{
private:
    vector<class Point*> points;
    int min_max_XY[4];

    void calcBounds(int bounds[], const vector<class Point*> &points)
    {
        for(auto it = points.begin(); it != points.end(); it++)
        {
            if((*it)->getX() < min_max_XY[0])
            {
                min_max_XY[0] = (*it)->getX();
            }
            if((*it)->getY() < min_max_XY[1])
            {
                min_max_XY[1] = (*it)->getY();
            }
            if((*it)->getX() > min_max_XY[2])
            {
                min_max_XY[2] = (*it)->getX();
            }
            if((*it)->getY() > min_max_XY[3])
            {
                min_max_XY[3] = (*it)->getY();
            }
        }
    }

public:

    vector<class Point*> getPoints()
    {
        return points;
    }
    Mesh() : points(points), min_max_XY{5,0,0,0} {}
    Mesh(vector<class Point*> points)
    {
        Point *p1 = new Point(5,6);
        Point *p2 = new Point();
        Point *p3 = new Point(1,4);
        Point *p4 = new Point(4,7);

        points.push_back(p1);
        points.push_back(p2);
        points.push_back(p3);
        points.push_back(p4);
    }
    Mesh(const Mesh &for_copy) : points(for_copy.points) 
    {
        for(int i = 0; i < 4; i++)
        {
            min_max_XY[i] = for_copy.min_max_XY[i];
        }
    }

    void addPoint(Point* for_add)
    {
        points.push_back(for_add);
        calcBounds(min_max_XY, points);
    }

    void print()
    {
        for(int i = 0; i < 4; i++)
        {
            cout << min_max_XY[i] << endl;
        }
        for(auto it = points.begin(); it != points.end(); it++)
        {
            cout <<"X- "<<(*it)->getX() << "  Y -" << (*it)->getY() << endl;
        }
    }
};


int main()
{
    vector<class Point*> points;
    Mesh m1 = Mesh(points);
    Point *new_point = new Point(-2, 6);
    m1.addPoint(new_point);
    m1.print();
    return 0; 
}