package OCT15;/**
 * Created by ankurverma1994 on 6/10/15.
 */

import java.io.*;
import java.util.*;

class PERSHIFTS {
    //------------> Solution starts here!!
    void solve() {
        for (int tc = ii(); tc > 0; tc--) {
            int n = ii(), k = ii(), p[] = iia(n), q[] = iia(n);
            bruteforce(p, k);
        }
    }

    void bruteforce(int p[], int k) {
        Queue<int[]> found = new LinkedList<>();
        Queue<int[]> todo = new LinkedList<>();
        todo.add(p);
        System.out.println(todo.get(0));
        int n = p.length;
        while (!todo.isEmpty()) {
            int x[] = todo.poll(todo.size() - 1);
            int perm[] = new int[n];
            for (int i = 0; i < n - k + 1; i++) {
                int z = 0;
                for (int j = 0; j <= i; j++, z++) perm[z] = x[j];
                for (int j = i + 1; j <= i + k; j++, z++) perm[z] = x[j];
                for (int j = i; j <= i + 1; j++, z++) perm[z] = x[j];
                for (int j = i + k; j < n; j++, z++) perm[z] = x[j];
            }
            if (!found.contains(perm)) {
                found.add(perm);
                todo.add(perm);
            }
        }
        for (int i = 0; i < found.size(); i++)
            out.println(found.get(i).toString());
    }

    //------------> Solution ends here!!
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new PERSHIFTS().main1();
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
