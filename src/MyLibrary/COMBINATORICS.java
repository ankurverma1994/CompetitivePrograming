/**
 *
 * @author ankurverma1994
 */
package MyLibrary;
class COMBINATORICS {
    public static void main(String[] args) {
    }

    // Complexity O(log n)
    long modpow(long base, int exp, int mod)
    {
        base%=mod;
        long result=1;
        while(exp>0){
            if((exp&1) ==1)
                result=(result*base)%mod;
            base=(base*base)%mod;
            exp>>=1;
        }
        return result;
    }
    // inverse modulo prime number
    // a^(-1) mod P = a^(P-2) mod P
    long inversemodp(long base, int mod)
    {
        return modpow(base, mod-2, mod);
    }

    /*nCr using recurence relation
    *      nCr = (n-1)C(r-1) + (n-1)C(r)
    *      Complexity O(nr)
    */
    long nCr(int n,int r, int mod)
    {
        long C[][]=new long[n+1][r+1];
        for(int i=0;i<=n;i++)
        {
            for(int k=0;k<=r && k<=i;k++)
                if(k==0 || k==i)
                    C[i][k]=1;
                else
                    C[i][k]=(C[i-1][k-1] + C[i-1][k])%mod;
        }
        return C[n][r];
    }
    int gcd(int a, int b)
    {
        while(b > 0){
            int c = a;
            a = b;
            b = c % b;
        }
        return a;
    }
    void primeFactors(int n, int[] a)
    {
        int[] temp = new int[101];
        while (n%2 == 0)
        {
            temp[2]++;
            n = n/2;
        }
        if(temp[2]>a[2])
            a[2]=temp[2];
        // n must be odd at this point.  So we can skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i = i+2)
        {
            // While i divides n, print i and divide n
            while (n%i == 0)
            {
                temp[i]++;
                if(temp[i]>a[i])	a[i]=temp[i];
                n = n/i;
            }
        }
        // This condition is to handle the case whien n is a prime number
        // greater than 2
        if (n > 2)
            temp[n]++;
        if(temp[n]>a[n])
            a[n]=temp[n];
    }
    boolean isPowerOfTwo (int x)
    {
        while (((x % 2) == 0) && x > 1) /* While x is even and > 1 */
            x /= 2;
        return (x == 1);
    }
}