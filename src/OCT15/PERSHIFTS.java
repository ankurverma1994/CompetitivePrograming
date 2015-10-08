package OCT15;/**
 * Created by ankurverma1994 on 6/10/15.
 */

import java.io.*;
import java.util.*;

class PERSHIFTS {
    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), k = ii();
            String p = is(), q = is();
            bruteforce(p, q, k);
//            List<String> one = bruteforce("abcdef", "abcdef", 3);
//            List<String> two = bruteforce("abcdef", "abcdef", 5);
//            boolean wa = true;
//            for (int i = 0; i < one.size(); i++)
//                if (two.contains(one.get(i)))
//                    continue;
//                else {
//                    wa = false;
//                    break;
//                }
//            if (wa)
//                out.println("Same");
//            else
//                out.println("Unequal");
        }
    }

    List<String> bruteforce(String p, String q, int k) {
        List<String> strings = new ArrayList<>();
        String perm = "";
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
            out.println(strings.get(j));
            j++;
        }
        out.println(strings.size());
        if (strings.contains(q))
            out.println("YES");
        else
            out.println("NO");
        return strings;
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new PERSHIFTS().main1();
    }

    void main1() throws IOException {
        out = new PrintWriter("/home/ankurverma1994/aa.txt");
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
