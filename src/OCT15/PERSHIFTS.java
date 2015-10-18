package OCT15;/**
 * Created by ankurverma1994 on 6/10/15.
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class PERSHIFTS {
    //------------> Solution starts here!!
    long fact[];
    int mod = 1000000007;

    void solve() {
        fact = new long[100001];
        fact[1] = 1;
        fact[0] = 1;
        for (int i = 2; i <= 100000; i++)
            fact[i] = (fact[i - 1] * i) % mod;
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), k = ii();
            int p[] = iia(n), q[] = iia(n);
            if (k == 1) {
                boolean waah = false;
                for (int i = 0; i < n; i++)
                    if (p[i] != q[i]) {
                        out.println("-1");
                        waah = true;
                        break;
                    }
                if (!waah)
                    out.println("1");
                continue;
            }
            if (k == n) {
                int j = 0;
                for (; j < n; j++)
                    if (p[0] == q[j])
                        break;
                boolean haan = true;
                j++;
                if (j >= n) j = 0;
                for (int i = 1; i < n; i++) {
                    if (p[i] != q[j]) {
                        haan = false;
                        break;
                    }
                    j++;
                    if (j >= n)
                        j = 0;
                }
                if (haan)
                    out.println(q[0]);
                else
                    out.println("-1");
                continue;
            }
            if (k % 2 == 0) {
                out.println(findRank(q, n));
                continue;
            } else {
                int x[] = new int[n];
                int y[] = new int[n];
                for (int i = 0; i < n; i++) {
                    x[i] = p[i];
                    y[i] = q[i];
                }
                int P = parity(x), Q = parity(y);
                // out.println(P + "  " + Q);
                if (P != Q) {
                    //  out.println("Fuck OFF");

                    out.println("-1");
                } else {
                    long rank = findRank(q, n);
                    boolean odd = false;
                    if (rank % 2 == 1)
                        odd = true;
                    if (odd) {
                        rank = (rank * 500000004) % mod;
                        rank = (rank - 500000004) % mod;
                        while (rank < 0)
                            rank += mod;
                        rank++;
                        rank = rank % mod;
                    } else {
                        rank = (rank * 500000004) % mod;
                    }
                    out.println(rank);
                }
            }
        }
//        int n=ii(),k=ii();
//        String  p=is(), q = is();
//            bruteforce(p, q, k);
    }

    // for counting inversions in an array http://codereview.stackexchange.com/questions/54756/counting-inversions
    int parity(int[] a) {
        return (mergeSort(a, 0, a.length) % 2);
    }

    int mergeSort(int[] a, int low, int high) {
        if (low == high - 1) return 0;

        int mid = (low + high) / 2;

        return mergeSort(a, low, mid) + mergeSort(a, mid, high) + merge(a, low, mid, high);
    }

    int merge(int[] a, int low, int mid, int high) {
        int count = 0;
        int[] temp = new int[a.length];

        for (int i = low, lb = low, hb = mid; i < high; i++) {

            if (hb >= high || lb < mid && a[lb] <= a[hb]) {
                temp[i] = a[lb++];
            } else {
                count = count + (mid - lb);
                temp[i] = a[hb++];
            }
        }

        System.arraycopy(temp, low, a, low, high - low);

        return count;
    }

    long findRank(int a[], int n) {
        long mul = fact[n];
        long rank = 1;
        int count[] = new int[n + 1];
        for (int i = 1; i <= n; i++) count[i] = i;
        for (int i = 0; i < n; i++) {
            //    mul /= (n - i);
            mul = fact[n - i - 1] % mod;
            //  out.println(count[a[i]-1]+"  "+mul);
            rank = (rank + (count[a[i] - 1] * mul) % mod) % mod;
            for (int j = a[i]; j <= n; j++)
                count[j]--;
            // out.println(Arrays.toString(count));
        }
        rank = rank % mod;
        while (rank < 0)
            rank += mod;
        return rank;
    }

    List<String> bruteforce(String p, String q, int k) {
        List<String> strings = new ArrayList<>();
        String perm;
        strings.add(p);
        int j = 0;
        while (j < strings.size()) {
            String tmp = strings.get(j);
            int n = tmp.length();
            for (int i = 0; i <= (n - k); i++) {
                perm = tmp.substring(0, i) + tmp.charAt(i + k - 1) + tmp.substring(i, i + k - 1) + tmp.substring(i + k);
                if (!strings.contains(perm))
                    strings.add(perm);
            }
            out.print(strings.get(j));
            bruteinversion(strings.get(j));
            j++;
        }
        out.println(strings.size());
        if (strings.contains(q))
            out.println("YES");
        else
            out.println("NO");
        return strings;
    }

    void bruteinversion(String a) {
        char c[] = a.toCharArray();
        int count = 0;
        for (int i = 0; i < c.length - 1; i++) {
            for (int j = i + 1; j < c.length; j++)
                if (c[j] > c[i])
                    count++;
        }
        count = count % 2;
        out.println("           " + count);
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new PERSHIFTS().main1();
    }

    void main1() throws IOException {
        double t1 = System.currentTimeMillis() / 1000.0;
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        //   obj = check.isEmpty() ? new FileInputStream("/home/abhishek20113/in.txt") : new ByteArrayInputStream(check.getBytes());
        solve();
        double t2 = System.currentTimeMillis() / 1000.0;
        //  out.println(t2 - t1);
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
