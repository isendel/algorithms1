import edu.princeton.cs.algs4.StdRandom;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: viktorp
 * Date: 9/12/15
 * Time: 4:42 PM
 */
public class PercolationTest {
    private Percolation percolation;

    @Before
    public void setUp() throws Exception {
        percolation = new Percolation(10);
    }

    @Test
    public void testConstructor() throws Exception {
        try {
            new Percolation(0);
            new Percolation(-1);
            assertTrue("Constructor could accept only positive values of N", false);
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    public void testAssertIndexBoundries() throws Exception {
        try {
            new Percolation(4).open(0, 0);
            assertTrue("Input index has to be within 1:4 bounds.", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
        try {
            new Percolation(4).open(1, 0);
            assertTrue("Input index has to be within 1:4 bounds.", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
        try {
            new Percolation(4).open(0, 1);
            assertTrue("Input index has to be within 1:4 bounds.", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
        try {
            new Percolation(4).open(-1, -1);
            assertTrue("Input index has to be within 1:4 bounds.", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
        try {
            new Percolation(4).open(5, 1);
            assertTrue("Input index has to be within 1:4 bounds.", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
        try {
            new Percolation(4).open(1, 5);
            assertTrue("Input index has to be within 1:4 bounds.", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
        try {
            new Percolation(4).open(5, 5);
            assertTrue("Input index has to be within 1:4 bounds.", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
        try {
            new Percolation(4).open(6, 6);
            assertTrue("Input index has to be within 1:4 bounds.", false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIsOpen() throws Exception {
        Percolation p = new Percolation(10);
        p.open(2,2);
        p.open(3,4);
        assertTrue(p.isOpen(2, 2));
        assertTrue(p.isOpen(3, 4));
        assertFalse(p.isOpen(1, 1));
        assertFalse(p.isOpen(10, 10));
    }

    @Test
    public void testIsFull() throws Exception {
        Percolation p = new Percolation(10);
        p.open(1,2);
        assertTrue(p.isFull(1, 2));
        assertFalse(p.isFull(1, 1));
        assertFalse(p.isFull(10, 10));
        p.open(2, 2);
        p.open(4, 2);
        assertTrue(p.isFull(2, 2));
        assertFalse(p.isFull(4, 2));
    }

    @Test
    public void testPercolates() throws Exception {
        Percolation p = new Percolation(4);
        p.open(1,2);
        assertFalse(p.percolates());
        p.open(2, 2);
        assertFalse(p.percolates());
        p.open(3, 2);
        assertFalse(p.percolates());
        p.open(4, 2);
        assertTrue(p.percolates());
    }
    @Test
    public void testPercolates2() throws Exception {
        Percolation p = new Percolation(4);
        p.open(1,1);
        assertFalse(p.percolates());
        p.open(2, 1);
        assertFalse(p.percolates());
        p.open(2, 2);
        assertFalse(p.percolates());
        p.open(2, 3);
        assertFalse(p.percolates());
        p.open(3, 3);
        assertFalse(p.percolates());
        p.open(4, 3);
        assertTrue(p.percolates());
    }

    @Test
    public void testPercolateBackward() throws Exception {
        Percolation p = new Percolation(4);
        p.open(1,3);
        p.open(2,3);
        p.open(3,3);
        p.open(4,3);
        assertTrue(p.percolates());
        p.open(4, 1);
        assertFalse(p.isFull(4, 1));
        p.open(3, 1);
        p.open(2, 1);
        p.open(1, 1);
        assertTrue(p.isFull(4, 1));
    }

    @Test
    public void testPercolateBackward2() throws Exception {
        Percolation p = new Percolation(4);
        p.open(1,3);
        p.open(2,3);
        p.open(3,3);
        p.open(4,3);
        assertTrue(p.percolates());
        p.open(2, 1);
        assertFalse(p.isFull(2, 1));
        p.open(3, 1);
        p.open(4, 1);
        p.open(1, 1);
        assertTrue(p.isFull(4, 1));
    }

    @Test
    public void testPercolateBackward3() throws Exception {
        Percolation p = new Percolation(4);
        p.open(2,3);
        p.open(3,3);
        p.open(4, 3);
        assertFalse(p.percolates());
        p.open(4, 1);
        assertFalse(p.isFull(4, 1));
        p.open(3, 1);
        p.open(2, 1);

        p.open(1,3);
        assertFalse(p.isFull(4, 1));

        p.open(1, 1);
        assertTrue(p.isFull(4, 1));
    }
}