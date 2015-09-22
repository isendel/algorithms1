import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * User: viktorp
 * Date: 9/12/15
 * Time: 4:37 PM
 */
public class Percolation {
    private final int n;
    private boolean[][] sites;
    private WeightedQuickUnionUF unionUF;
    private boolean percolates = false;
    private int[] bottomOpened;
    private int bottomOpenedCount = 0;
    private int topParent;


    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N value has to be greater then zero.");
        this.n = n;
        sites = new boolean[n][n];
        bottomOpened = new int[n];
        unionUF = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int i, int j) {
        if (isOpen(i, j)) return;
        assertIndexBoundaries(i, j);
        sites[i - 1][j - 1] = true;
        int siteValue = computeNum(i - 1, j - 1);
        if (i == 1) {
            unionUF.union(siteValue, 0);
        }
        if (i == n) {
            unionUF.union(siteValue, n * n + 1);
//            bottomOpened[bottomOpenedCount++] = computeNum(i - 1, j - 1);
//            if (topParent == unionUF.find(bottomOpened[bottomOpenedCount - 1])) {
//                percolates = true;
//            }
        }
        //open to the bottom
        if (i < n && isOpen(i + 1, j)) {
            unionUF.union(siteValue, computeNum(i, j - 1));
        }
        //open to the right
        if (j < n && isOpen(i, j + 1)) {
            unionUF.union(siteValue, computeNum(i - 1, j));
        }
        //open to the top
        if (i > 1 && isOpen(i - 1, j)) {
            unionUF.union(siteValue, computeNum(i - 2, j - 1));
        }
        //open to the left
        if (j > 1 && isOpen(i, j - 1)) {
            unionUF.union(siteValue, computeNum(i - 1, j - 2));
        }

//        if (!percolates && unionUF.connected(0, n * n + 1)) {
//            percolates = true;
//        }
//        topParent = unionUF.find(0);
    }

    public boolean isOpen(int i, int j) {
        assertIndexBoundaries(i, j);
        return sites[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        assertIndexBoundaries(i, j);
        return isOpen(i, j) && unionUF.connected(0, computeNum(i - 1, j - 1));
    }

    public boolean percolates() {
//        if (bottomOpenedCount == 0) return false;
//        if (percolates) return true;
//        for (int i = 0; i < bottomOpenedCount; i++) {
//            if (topParent == unionUF.find(bottomOpened[i])) {
//                percolates = true;
//                break;
//            }
//        }
//        return percolates;
        return unionUF.connected(0, n * n + 1);
    }

    private void assertIndexBoundaries(int i, int j) {
        if (i <= 0 || j <= 0 || i > n || j > n)
            throw new IndexOutOfBoundsException("Input arguments has to be within site " +
                    "matrix bounds 1:N. Input i=" + i + ",j=" + j);
    }

    private int computeNum(int i, int j) {
        return i * n + j + 1;
    }


}
