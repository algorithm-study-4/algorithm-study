#include<iostream>
#include<algorithm>

using namespace std;

int main() {
    int coin500 = 0;
    int coin100 = 0;
    int coin50 = 0;
    int coin10 = 0;

    int change;

    cin >> change;
    coin500 = change / 500;
    change %= 500;
    coin100 = change / 100;
    change %= 100;
    coin50 = change / 50;
    change %= 50;
    coin10 = change / 10;
    change %= 10;
    
    cout << coin500 + coin100 + coin50 + coin10;

    return 0;
}