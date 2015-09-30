package MyLibrary;

/**
 * Created by ankurverma1994 on 30/9/15.
 */
class SegmentTree {
    public static void main(String[] args) {
        int a[] = {4, 5, 6, 10, 2, 12};
        SegmentTree segmentTree = new SegmentTree(0, a.length - 1, a);
        System.out.println(segmentTree.query(0, 5));
        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(3, 5));
        System.out.println(segmentTree.query(0, 1));
        System.out.println(segmentTree.query(2, 2));
        System.out.println(segmentTree.query(3, 4));
        System.out.println(segmentTree.query(5, 5));
        segmentTree.update(2, 10, a);
        System.out.println(segmentTree.query(0, 5));
        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(3, 5));
        System.out.println(segmentTree.query(0, 1));
        System.out.println(segmentTree.query(2, 2));
        System.out.println(segmentTree.query(3, 4));
        System.out.println(segmentTree.query(5, 5));

    }

    int from;
    int to;
    long sum;
    SegmentTree left;
    SegmentTree right;

    SegmentTree(int from, int to, int A[]) {
        this.from = from;
        this.to = to;
        if (from == to) {
            sum = A[from];
        } else {
            int mid = (from + to) / 2;
            left = new SegmentTree(from, mid, A);
            right = new SegmentTree(mid + 1, to, A);
            sum = (left.sum + right.sum);
        }
    }

    long query(int L, int R) {
        if (L <= from && to <= R) {
            return sum;
        } else if (to < L || R < from) {
            return 0;
        } else {
            long sumL = left.query(L, R);
            long sumR = right.query(L, R);
            return (sumL + sumR);
        }
    }

    void update(int pos, int val, int A[]) {
        int diff = val - A[pos];
        A[pos] = val;
        updateUtil(pos, diff);
    }

    void updateUtil(int pos, int diff) {
        if (pos < from || pos > to)
            return;
        sum = sum + diff;
        if (from != to) {
            left.updateUtil(pos, diff);
            right.updateUtil(pos, diff);
        }
    }
}