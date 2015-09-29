import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
/* *
*
* @author ankurverma1994
*/
class DCGAME {
    //------------> Solution starts here!!
    int[] logTable;
    int[][] rmq;
    int[] a;
    long[] freq;
    int num[][];

    void solve() {
        int n = ii(), m = ii();
        a = iia(n);
        freq = new long[n];
        num = new int[n][2];
        RmqSparseTable();
        fill(0, n - 1);
        Arrays.sort(num, Comparator.comparingInt(xx -> xx[0]));
//        for(int i=0;i<n;i++)
//            out.println(num[i][0]+"   "+freq[num[i][1]]);
        int len = 1;
        for (int i = 1; i < n; i++)
            if (num[i - 1][0] != num[i][0])
                len++;
        int arr[] = new int[len];
        long f[] = new long[len];
        arr[0] = num[0][0];
        f[0] = freq[num[0][1]];
        // int j=0;
//        int arr[]=new int[n];
//        long f[]=new long[n];
        for (int i = 1, j = 0; i < n; i++) {
            if (num[i - 1][0] != num[i][0]) {
                j++;
                arr[j] = num[i][0];
                f[j] += freq[num[i][1]];
            } else {
                arr[j] = num[i][0];
                f[j] += freq[num[i][1]];
            }
        }
        long sum[] = new long[len];
        sum[0] = f[0];
        for (int i = 1; i < len; i++)
            sum[i] = sum[i - 1] + f[i];
//        for(int i=0;i<len;i++)
//            out.println(arr[i]+"  "+f[i]);
        for (int Q = 0; Q < m; Q++) {
            char c = ic();
            int k = ii();
            char start = ic();
            int index = Arrays.binarySearch(arr, k);
            long count = 0;
            if (index >= 0) {
                switch (c) {
                    case '<':
                        if (index == 0)
                            count = 0;
                        else {
                            count = sum[index - 1];

                        }
                        break;
                    case '>':
                        count = sum[len - 1] - sum[index];
                        break;
                    case '=':
                        if (index > 0) {
                            count = sum[index] - sum[index - 1];
                            System.out.println(count);
                        } else {
                            count = sum[index];

                        }
                        break;
                }
            } else if (index < 0) {
                index = index + 1;
                index = -1 * index;
                if (c == '<') {
                    if (index == 0)
                        count = 0;
                    else
                        count = sum[index - 1];
                } else if (c == '>') {
                    if (index == 0)
                        count = sum[len - 1];
                    else
                        count = sum[len - 1] - sum[index - 1];
                }
            }
            //      out.print(count);
            if (count % 2 == 1)
                out.print(start);
            else {
                if (start == 'D')
                    out.print("C");
                else
                    out.print("D");
            }
        }
    }

    void RmqSparseTable() {
        int n = a.length;
        logTable = new int[n + 1];
        for (int i = 2; i <= n; i++)
            logTable[i] = logTable[i >> 1] + 1;
        rmq = new int[logTable[n] + 1][n];
        for (int i = 0; i < n; ++i)
            rmq[0][i] = i;
        for (int k = 1; (1 << k) < n; ++k) {
            for (int i = 0; i + (1 << k) <= n; i++) {
                int x = rmq[k - 1][i];
                int y = rmq[k - 1][i + (1 << k - 1)];
                rmq[k][i] = a[x] >= a[y] ? x : y;
            }
        }
    }

    int maxPos(int i, int j) {
        int k = logTable[j - i];
        int x = rmq[k][i];
        int y = rmq[k][j - (1 << k) + 1];
        return a[x] >= a[y] ? x : y;
    }

    void fill(int start, int end) {
        if (end < start)
            return;
        int M = maxPos(start, end);
        long N = end - start + 1, L = M - start, R = end - M;
        N = N * (N + 1) / 2;
        L = L * (L + 1) / 2;
        R = R * (R + 1) / 2;
        freq[M] = N - L - R;
        num[M][0] = a[M];
        num[M][1] = M;
        fill(start, M - 1);
        fill(M + 1, end);
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new DCGAME().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        // obj=check.isEmpty() ? new FileInputStream("/home/ankurverma1994/d001951c-2-input-d001891.txt") : new ByteArrayInputStream(check.getBytes());
        solve();
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

    String[] isa(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = is();
        return a;
    }

    double[][] idm(int n, int m) {
        double a[][] = new double[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = id();
        return a;
    }

    int[][] iim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = ii();
        return a;
    }
}