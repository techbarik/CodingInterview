public class Solution {
    public int[] repeatedNumber(final int[] A) {
        int[] ans = new int[2];
        if (A.length == 0) return ans;

        int xor = A[0];

        for (int i = 1; i < A.length; i++) {
            xor = xor ^ A[i];
        }

        for (int i = 1; i <= A.length; i++) {
            xor = xor ^ i;
        }       

        // int bit_num = xor & ~(xor - 1);
        int bit_num = 1;
        for (int i = 1; i <= 32; i++) {
            if ((bit_num & xor) == 0) {
                bit_num = bit_num << 1;
            } else {
                break;
            }
        }

        int x = 0;
        int y = 0;

        for (int i = 0; i < A.length; i++) {
            if ((A[i] & bit_num) != 0) {
                x = x ^ A[i];
            } else {
                y = y ^ A[i];
            }
        }

        for (int i = 1; i <= A.length; i++) {
            if ((i & bit_num) != 0) {
                x = x ^ i;
            } else {
                y = y ^ i;
            }
        }

        int xcount = 0;
        for (int i = 0; i < A.length; i++) {
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

        /*
        1,2,3,4,5
        First Bucket: if bit_num th bit is 1
        3
        
        Second Bucket: if bit_num th bit is 0 
        4 */

        /*
            [3, 1, 2, 5, 3]
            xor = 3 ^ 4   (x^y)
            100
            011
            111
        */

         /*
        int mask = 1;
        for (int i = 1; i <= 32; i++) {
            if (mask & xor != 0) {
                mask = mask << 1;
            }
        } */



        return ans;
    }
}
