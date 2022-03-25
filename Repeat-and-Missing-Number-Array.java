public class Solution {
    public int[] repeatedNumber(final int[] A) {
        int[] ans = new int[2];
        if (A.length == 0) return A;

        int xor = A[0];
        for (int i = 1; i < A.length; i++) { // n
            xor = xor ^ A[i];
        }

        for (int i = 1; i <= A.length; i++) { // n
            xor = xor ^ i;
        }

        // xor = A[0] ^ ... A[n-1] ^ 1 ^ ... n = x ^ y

        // int set_bit_number = xor & ~(xor - 1);
        int set_bit_number = 1;
        for (int i = 0; i < 32; i++) {
            if ((xor & set_bit_number) == 0) {
                set_bit_number = set_bit_number << 1;
            } else {
                break;
            }
        }

        int x = 0; // bucket1 
        int y = 0; // bucket2

        for (int i = 0; i < A.length; i++) { // n
            if ((set_bit_number & A[i]) != 0) {
                x = x ^ A[i];
            } else {
                y = y ^ A[i];
            }
        }

        for (int i = 1; i <= A.length; i++) { // n
            if ((set_bit_number & i) != 0) {
                x = x ^ i;
            } else {
                y = y ^ i;
            }
        }

        // TC: O(n)
        // SC: O(1)

        int xcount = 0;
        for (int i = 0; i < A.length; i++) { // n
            if (A[i] == x) {
                xcount++;
            }
        }

        if (xcount == 0) {
            ans[0] = y;
            ans[1] = x;
        } else {
            ans[0] = x;
            ans[1] = y;
        }
        

        return ans;
    }
}



/*
xor = 111 & 001 = 001

111 - 001 = ~110 = 001 */


/*
int set_bit_number = 1;
for (int i = 0; i < 32; i++) {
    if ((xor & set_bit_number) == 0) {
        set_bit_number = set_bit_number << 1;
    } else {
        break;
    }
} */
