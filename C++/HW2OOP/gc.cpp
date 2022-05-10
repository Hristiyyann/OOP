#include<iostream>
#include<vector>
#include<map>
#include<sstream>
#include<fstream>
#include "functions.hpp"
#include "exception.hpp"
using namespace std;

class Test : public BaseObject{};

int main()
{ 
    GC::initialization();
    try
    { 
        int* a = (int*)GC::allocate(sizeof(int));
        GC::dump("bytes.txt");
        *a = 5;
        cout << a << " " << *a << endl;
        GC::free(a);
        GC::dump("bytes.txt");

        int *b = (int*)GC::allocate(sizeof(int) * 5);
        GC::dump("bytes.txt");
        GC::free(b);
        GC::dump("bytes.txt");

        Test* test = (Test*)new Test();
        GC::dump("bytes.txt");
        delete test;
        GC::dump("bytes.txt");
        
        Test* test2 = (Test*) new Test[5];
        GC::dump("bytes.txt");
        delete[] test2;
        GC::dump("bytes.txt");
    }
    catch(allocate_exception& exception)
    {
        cout << exception.what() << endl;
    }
    
    try
    {
        int* c = (int*)GC::allocate(sizeof(int)); // Правя демонстрация и за гроу и шринк
        GC::grow(c, 10);
        GC::dump("bytes.txt");
        GC::shrink(c,7);
        GC::dump("bytes.txt");
    }
    
    catch(first_address_exception& exception)
    {
        cout << exception.what() << endl;
    }
    catch(grow_size_exception& exception)
    {
        cout << exception.what() << endl;
    }
    catch(shrink_size_exception& exception)
    {
        cout << exception.what() << endl;
    }    
    catch(grow_allocation_exception& exception)
    {
        cout << exception.what() << endl;
    }

    GC::freing();
    return 0;
}