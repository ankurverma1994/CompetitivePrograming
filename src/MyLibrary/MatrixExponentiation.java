package MyLibrary;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/* *
 *
 * @author ankurverma1994
 */
// Recurrence Relation
// T(n)= aT(n-1) + bT(n-2) + cT(n-3) + d
class MatrixExponentiation {
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new MatrixExponentiation().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solve();
        out.flush();
        out.close();
    }

    //------------> Solution starts here!!
    int mod = 1000000007;

    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int a = ii(), b = ii(), c = ii(), d = ii(), T1 = ii(), T2 = ii(), T3 = ii(), n = ii();
            if (n < 4) {
                if (n == 1)
                    out.println(T1);
                else if (n == 2)
                    out.println(T2);
                else if (n == 3)
                    out.println(T3);
                continue;
            }
            long M[][] = {
                    {a, b, c, 1},
                    {1, 0, 0, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 1}
            };
            M = ModPow(M, n - 3);
            int inital[] = {T3, T2, T1, d};
            long result = 0;
            for (int i = 0; i < 4; i++)
                result += M[0][i] * inital[i];
            Math.floorMod(result, mod);
            out.println(result);
        }
    }

    // Nth power of Matrix with mod in O(log n)
    long[][] ModPow(long M[][], int exp) {
        long result[][] = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        long pow[][] = M;
        while (exp != 0) {
            if ((exp & 1) == 1)
                result = MulMatrix(result, pow);
            exp >>= 1;
            pow = MulMatrix(pow, pow);
        }
        return result;
    }

    // Multiplication of Matrix in O(k^3) where k is size of matrix
    long[][] MulMatrix(long A[][], long B[][]) {
        long C[][] = new long[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                long value = 0;
                for (int k = 0; k < 4; k++) {
                    value += A[i][k] * B[k][j];
                }
                C[i][j] = value % mod;
            }
        }
        return C;
    }

    //------------> Solution ends here!!
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

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    String is() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    int ii() {
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

    long il() {
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

    float nf() {
        return Float.parseFloat(is());
    }

    double id() {
        return Double.parseDouble(is());
    }

    char ic() {
        return (char) skip();
    }

    int[] iia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = ii();
        return a;
    }

    long[] ila(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = il();
        return a;
    }
}