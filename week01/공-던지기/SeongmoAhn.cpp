#include <string>
#include <vector>

using namespace std;

int solution(vector<int> numbers, int k) {
    return numbers[(2 * k - 2) % numbers.size()];
}