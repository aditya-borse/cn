// Write a program for error detection and correction for 7/8 bits ASCII codes using Hamming Codes
#include <bits/stdc++.h> 
using namespace std;

void generateHammingCode(int data[], int dataBits, int hammingBits)
{
    int totalBits = dataBits + hammingBits;
    int hammingCode[totalBits + 1];
    for (int i = 1; i <= totalBits; i++)
    {
        hammingCode[i] = 0;
    } 

    int index = 0;
    for (int i = 1; i <= totalBits; i++)
    {
        if ((i & (i - 1)) != 0)
        {
            hammingCode[i] = data[index];
            index++;
        }
    }

    for (int i = 0; i < hammingBits; i++)
    {
        int parity = 0;
        for (int j = 1; j <= totalBits; j++)
        {
            if ((j & (1 << i)) && hammingCode[j])
            {
                parity ^= 1;
            }
        }
        hammingCode[(1 << i)] = parity;
    }

    cout << "Generated hamming code: ";
    for (int i = 1; i <= totalBits; i++)
    {
        cout << hammingCode[i] << " ";
    }
    cout << endl;
}

void checkAndCorrectError(int hammingCode[], int dataBits, int hammingBits)
{
    int errorBit = 0;
    int totalBits = dataBits + hammingBits;
    int copyHammingCode[totalBits + 1];

    for (int i = 0; i < totalBits; i++)
    {
        copyHammingCode[i + 1] = hammingCode[i];
    }

    for (int i = 0; i < hammingBits; i++)
    {
        int parity = 0;
        for (int j = 1; j <= totalBits; j++)
        {
            if ((j & (1 << i)) && copyHammingCode[j])
            {
                parity ^= 1;
            }
        }

        if (parity != 0)
        {
            errorBit += (1 << i);
        }
    }

    if (errorBit)
    {
        cout << "Error detected at bit position: " << errorBit<< endl;
        copyHammingCode[errorBit] ^= 1; // correction
        cout << "Corrected Hamming Code: ";
        for (int i = 1; i <= totalBits; i++) 
        {
            cout << copyHammingCode[i] << " ";
        }
        cout << endl;
    }
    else{
        cout << "No error detected.\n";
    }
}

int main() 
{
    int data[] = {0, 1, 0, 0, 0, 0, 0, 1}; // ascii for 'A' in binary (65)
    int dataBits = 8;
    int hammingBits = 4;
    generateHammingCode(data, dataBits, hammingBits);
    int receivedHammingCode[] = {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0}; // received hamming code
    cout << "Received Hamming Code: ";
    for (int i = 0; i < dataBits + hammingBits; i++)
    {
        cout << receivedHammingCode[i] << " ";
    }
    cout << endl;
    checkAndCorrectError(receivedHammingCode, dataBits, hammingBits);
}