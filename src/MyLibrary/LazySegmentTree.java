package MyLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
/*
Solution of ADDMUL from JULY'15 Long Challenge
Taken code of stolis from codechef
 */

/**
 * Created by ankurverma1994 on 30/9/15.
 */
class LazySegmentTree {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(System.out);
        int N = Integer.parseInt(reader.readLine());
        int Q = Integer.parseInt(reader.readLine());
        long A[] = new long[N];
        for (int i = 0; i < N; i++) A[i] = Long.parseLong(reader.readLine());
        Node root = new Node(0, N - 1, A);
        for (int q = 0; q < Q; q++) {
            int type = Integer.parseInt(reader.readLine());
            int L = Integer.parseInt(reader.readLine()) - 1;
            int R = Integer.parseInt(reader.readLine()) - 1;
            if (type == 1) {
                long value = Long.parseLong(reader.readLine());
                root.update(L, R, 1, value);
            } else if (type == 2) {
                long value = Long.parseLong(reader.readLine());
                root.update(L, R, value, 0);
            } else if (type == 3) {
                long value = Long.parseLong(reader.readLine());
                root.set(L, R, value);
            } else {
                long answer = root.read(L, R);
                output.println(answer);
            }
        }
        output.close();
    }
}

class Node {
    int mod = 1000000007;
    final int from;
    final int to;
    final Node left;
    final Node right;
    // actual sum = times*sum+(to-from+1)*plus
    long sum;
    long times = 1;
    long plus;
    boolean set;
    long setValue;

    Node(int from, int to, long A[]) {
        this.from = from;
        this.to = to;
        if (from == to) {
            sum = A[from];
            left = right = null;
        } else {
            int mid = (from + to) >> 1;
            left = new Node(from, mid, A);
            right = new Node(mid + 1, to, A);
            sum = (left.sum + right.sum) % mod;
        }
    }

    void update(int L, int R, long mult, long add) {
        if (R < from || to < L) {
            // no-op
        } else if (L <= from && to <= R) {
            times *= mult;
            times %= mod;
            plus = (plus * mult + add) % mod;
            if (set) {
                setValue = (setValue * mult + add) % mod;
            }
        } else {
            pushDown();
            left.update(L, R, mult, add);
            right.update(L, R, mult, add);
            sum = (left.times * left.sum + left.plus * (left.to - left.from + 1) + right.times * right.sum + right.plus * (right.to - right.from + 1)) % mod;
            times = 1;
            plus = 0;
        }
    }

    void set(int L, int R, long value) {
        if (R < from || to < L) {
            // no-op
        } else if (L <= from && to <= R) {
            sum = ((to - from + 1) * value) % mod;
            plus = 0;
            times = 1;
            set = true;
            setValue = value;
        } else {
            pushDown();
            left.set(L, R, value);
            right.set(L, R, value);
            sum = (left.times * left.sum + left.plus * (left.to - left.from + 1) + right.times * right.sum + right.plus * (right.to - right.from + 1)) % mod;
            times = 1;
            plus = 0;
        }
    }

    long read(int L, int R) {
        if (R < from || to < L) {
            return 0;
        } else if (L <= from && to <= R) {
            return (times * sum + plus * (to - from + 1)) % mod;
        } else {
            pushDown();
            return (left.read(L, R) + right.read(L, R)) % mod;
        }
    }

    void pushDown() {
        if (set) {
            left.set(from, to, setValue);
            right.set(from, to, setValue);
            sum = (left.times * left.sum + left.plus * (left.to - left.from + 1) + right.times * right.sum + right.plus * (right.to - right.from + 1)) % mod;
            times = 1;
            plus = 0;
        } else if (times != 1 || plus != 0) {
            left.update(from, to, times, plus);
            right.update(from, to, times, plus);
            sum = (left.times * left.sum + left.plus * (left.to - left.from + 1) + right.times * right.sum + right.plus * (right.to - right.from + 1)) % mod;
            times = 1;
            plus = 0;
        }
        set = false;
    }

}
