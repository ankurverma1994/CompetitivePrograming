package APAC;
/**
 * Created by ankurverma1994 on 18/10/15.
 */

import java.io.*;
import java.util.*;

class gRanks {
    //------------> Solution starts here!!
    void solve() {
        int TT = ii();
        for (int tc = 1; tc <= TT; tc++) {
            int P = ii(), S[] = iia(P);
            Set<String> rank = new HashSet<>();
            ArrayList<Integer> arrayList[] = new ArrayList[100000];
            int len = 0;
            for (int N = ii(); N > 0; N--) {
                int W = ii();
                String names[] = isa(P);
                for (int i = 0; i < P; i++) {
                    if (!rank.contains(names[i])) {
                        rank.add(names[i]);
                        arrayList[len] = new ArrayList<>();
                        arrayList[len].add(W * S[i]);
                        len++;
                    } else {
                        arrayList[len].add(W * S[i]);
                    }
                }
            }
            int M = ii();
            int arr[][] = new int[len][2];
            for (int i = 0; i < len; i++) {
                Integer[] abc = (Integer[]) arrayList[i].toArray();
                Arrays.sort(abc);
                arr[i][0] = abc[abc.length - 1] + abc[abc.length - 2];
                arr[i][1] = i;
            }
            Arrays.sort(arr, (int aa[], int bb[]) ->
            {
                if (aa[0] < bb[0])
                    return -1;
                return 1;
            });
            out.println("Case #" + tc + ":");
            int R = 1;
            for (int i = 0; i < len; i++) {

            }
        }
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new gRanks().main1();
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
