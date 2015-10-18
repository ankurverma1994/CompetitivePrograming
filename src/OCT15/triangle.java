package OCT15;
/**
 * Created by ABHISHEK SHANKHADHAR on 09-10-2015.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

class triangle {
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new triangle().ace();
    }

    //Solution !!
    boolean flag = false;
    int count = 0;
    boolean prime[];
    int arr[];

    void solution() {
        prime = new boolean[31624]; //sqrt of 10^9 is 31622.77
        Arrays.fill(prime, true);
        sieve();
        int m = 1, n = 5000000;
        arr = new int[175000];
        if (m < 31624) {
            for (int i = m; i <= n && i < 31624; i++)
                if (prime[i] && i % 4 == 1) {
                    arr[count] = i;
                    count++;
                }
            if (n >= 31624)
                SegmentedSieve(31624, n);
        } else
            SegmentedSieve(m, n);

        for (int u = 1; u <= 5000000; u++) {
            flag = false;
            //   int u = inti();
            for (int i = 0; i < 175000; i++) {
                if (arr[i] > u || arr[i] == 0) break;
                if (u % arr[i] == 0) {
                    flag = true;
                    break;
                }
            }
            if (flag)
                out.print("1");
            else
                out.print("0");
        }
    }

    void sieve() {
        prime[0] = prime[1] = false;
        for (int i = 2; 2 * i < 31624; i++)
            prime[2 * i] = false;
        for (int i = 3; i < 31624; i += 2)
            if (prime[i])
                for (int j = 3; i * j < 31624; j += 2)
                    prime[i * j] = false;
    }

    void SegmentedSieve(int L, int U) {
        int d = U - L + 1;
        boolean flag[] = new boolean[d];
        Arrays.fill(flag, true);
        int j = L % 2 != 0 ? 1 : 0;
        for (int i = j; i < d; i += 2)
            flag[i] = false;
        for (int i = 3; i * i <= U; i += 2) {
            j = (L / i) * i; //nearest multiple of i
            if (j < L)
                j += i;
            for (j = j - L; j < d; j += i)
                flag[j] = false;
        }
        for (int i = 0; i < d; i++)
            if (flag[i] && i % 4 == 1) {
                arr[count] = L + i;
                count++;
            }
    }


    //------->ends !!
    void ace() throws IOException {
        out = new PrintWriter("/home/ankurverma1994/ans.txt");
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solution();
        out.flush();
        out.close();
    }

    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1)
            throw new InputMismatchException();
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0)
            return -1;
        return inbuffer[ptrbuffer++];
    }

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    String stri() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    int inti() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    long loni() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    float fl() {
        return Float.parseFloat(stri());
    }

    double dou() {
        return Double.parseDouble(stri());
    }

    char chi() {
        return (char) skip();
    }

    int[] arri(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = inti();
        return a;
    }

    long[] arrl(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = loni();
        return a;
    }

    String[] stra(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = stri();
        return a;
    }
}