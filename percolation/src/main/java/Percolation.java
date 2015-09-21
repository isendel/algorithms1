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


    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N value has to be greater then zero");
        this.n = n;
        sites = new boolean[n][n];
        bottomOpened = new int[n];
        unionUF = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int i, int j) {
        if (isOpen(i, j)) return;
        assertIndexBoundries(i, j);
        sites[i - 1][j - 1] = true;
        int computeNum = computeNum(i - 1, j - 1);
        if (i == 1) {
            unionUF.union(computeNum, 0);
        }
        System.out.println("computeNum = " + computeNum);
        System.out.println("unionUF = " + unionUF.find(computeNum));
        if (i == n) {
//            bottomOpened[bottomOpenedCount++] = computeNum;
            unionUF.union(computeNum(i - 1, j - 1), n * n);
        }
        int siteValue = computeNum;
        if (i < n && isOpen(i + 1, j)) {
            unionUF.union(siteValue, computeNum(i, j - 1));
        }
        if (j < n && isOpen(i, j + 1)) {
            unionUF.union(siteValue, computeNum(i - 1, j));
        }
        if (i > 1 && isOpen(i - 1, j)) {
            unionUF.union(siteValue, computeNum(i - 2, j - 1));
        }
        if (j > 1 && isOpen(i, j - 1)) {
            unionUF.union(siteValue, computeNum(i - 1, j - 2));
        }
/*
        if (!percolates && isFull(i, j)) {
            for (int k = 0; k < bottomOpenedCount; k++) {
                if (unionUF.connected(0, bottomOpened[k])) {
                    percolates = true;
                    break;
                }
            }
        }
*/
    }

    public boolean isOpen(int i, int j) {
        assertIndexBoundries(i, j);
        return sites[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        assertIndexBoundries(i, j);
        return unionUF.connected(0, computeNum(i - 1, j - 1));
    }

    public boolean percolates() {
        return unionUF.connected(0, n * n);
    }

    private void assertIndexBoundries(int i, int j) {
        if (i <= 0 || j <= 0 || i > n || j > n)
            throw new IndexOutOfBoundsException("Input arguments has to be within site " +
                    "matrix bounds 1:N. Input i=" + i + ",j=" + j);
    }

    private int computeNum(int i, int j) {
        return i * n + j + 1;
    }


}
