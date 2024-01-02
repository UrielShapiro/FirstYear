package Exe.Ex2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This JUnit class represents a very simple unit testing for Ex2.
 * This class should be improved and generalized significantly.
 * Make sure you add documentations to this Tesing class.
 *
 * @author Uriel Shapiro
 * @id 3147797454
 */

class Ex2Test {
    static double[] po1 = {2, 0, 3, -1, 0}, po2 = {0.1, 0, 1, 0.1, 3}, po3 = {10, 2, 5, 1};

    @Test
    public void testEqualArrays() {
        double[] p1 = {1, 0.99999, 3, 1};
        double[] p2 = {1, 1, 3, 1};
        double[] p3 = {1, 2, 1, 1};
        double[] p4 = {1, 1, 1, 1};
        double[] p5 = {1, 1, 1, 1, 1, 1};
        double[] p6 = {1, 1, 3, 1, 0, 0, 0};
        boolean p12 = Ex2.equals(p1, p2);
        boolean p13 = Ex2.equals(p1, p3);
        boolean p23 = Ex2.equals(p3, p2);
        boolean p14 = Ex2.equals(p1, p4);
        boolean p45 = Ex2.equals(p4, p5);
        boolean p26 = Ex2.equals(p2, p6);
        assertEquals(p12, true);
        assertEquals(p13, false);
        assertEquals(p23, false);
        assertEquals(p14, false);
        assertEquals(p45, false);
        assertEquals(p26, true);
    }

    @Test
    public void testF() {
        double fx0 = Ex2.f(po1, 0);
        double fx1 = Ex2.f(po1, 1);
        double fx2 = Ex2.f(po1, 2);
        double fx3 = Ex2.f(po3, 0);
        double fx4 = Ex2.f(po3, 1.1);
        double[] onechar = {4};
        double fx5 = Ex2.f(onechar, 4);
        assertEquals(fx0, 2);
        assertEquals(fx1, 4);
        assertEquals(fx2, 6);
        assertEquals(fx3, 10);
        assertEquals(fx4, 19.581);
        assertEquals(fx5, 4);

    }

    @Test
    public void testRoot() {
        double[] parabula = {0, 0, 1};
        double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
        double x13 = Ex2.root(po3, -10, 0, Ex2.EPS);
        double testparab = Ex2.root(parabula, -1, 1, Ex2.EPS);
        assertEquals(testparab, 0, Ex2.EPS);
        assertEquals(x13, -5, Ex2.EPS);
        assertEquals(x12, 3.1958, Ex2.EPS);
    }

    @Test
    public void testRoot_rec() {
        double[] parabula = {0, 0, 1};
        double x12 = Ex2.root_rec(po1, 0, 10, Ex2.EPS);
        double x13 = Ex2.root_rec(po3, -10, 0, Ex2.EPS);
        double testparab = Ex2.root_rec(parabula, -1, 1, Ex2.EPS);
        assertEquals(testparab, 0, Ex2.EPS);
        assertEquals(x13, -5, Ex2.EPS);
        assertEquals(x12, 3.1958, Ex2.EPS);
    }

    @Test
    public void testAdd() {
        double[] p12 = Ex2.add(po1, po2);
        double[] minus1 = {-1};
        double[] pp2 = Ex2.mul(po2, minus1);
        double[] p1 = Ex2.add(p12, pp2);
        assertArrayEquals(new double[]{2.1, 0.0, 4.0, -0.9, 3.0}, p12, Ex2.EPS);
        assertArrayEquals(new double[]{-0.1, 0, -1, -0.1, -3}, pp2, Ex2.EPS);
        assertArrayEquals(new double[]{2.0, 0, 3.0, -1.0, 0}, p1, Ex2.EPS);
        assertEquals(Ex2.poly(po1), Ex2.poly(p1));
    }

    @Test
    public void testMulDoubleArrayDoubleArray() {
        double[] p12 = Ex2.add(po1, po2);
        double dd = Ex2.f(p12, 5);
        assertEquals(dd, 1864.6, Ex2.EPS);
    }

    @Test
    public void testDerivativeArrayDoubleArray() {
        double[] p = {1, 2, 3}; // 3X^2+2x+1
        double[] dp1 = {2, 6}; // 6x+2
        double[] dp2 = Ex2.derivative(p);
        assertArrayEquals(dp1, dp2, Ex2.EPS);
        assertEquals(dp1[0], dp2[0], Ex2.EPS);
        assertEquals(dp1[1], dp2[1], Ex2.EPS);
        assertEquals(dp1.length, dp2.length);
        double[] p3 = {3, 5, 6, 77, 0.33};
        double[] dp3 = {5, 12, 231, 1.32};
        double[] ansdp3 = Ex2.derivative(p3);
        assertArrayEquals(dp3, ansdp3, Ex2.EPS);
        assertEquals(dp3[0], ansdp3[0], Ex2.EPS);
        assertEquals(dp3[1], ansdp3[1], Ex2.EPS);
        assertEquals(dp1.length, dp2.length);


    }

    @Test
    public void testsameValue() {
        double[] p1 = {0, 1};
        double[] p2 = {0, -1};
        double test3 = Ex2.sameValue(p1, p2, -1, 1, Ex2.EPS);
        assertEquals(0.0, test3, Ex2.EPS);
        double test1 = Ex2.sameValue(po1, po3, -5, 0, Ex2.EPS);
        assertEquals(-1.74295, test1, Ex2.EPS);
        double test2 = Ex2.sameValue(po1, po2, 0, 1, Ex2.EPS);
        assertEquals(0.981738, test2, Ex2.EPS);

    }

    @Test
    public void testSubstraction() {
        double[] p1 = {2, 0, 3, -1, 0};
        double[] p2 = {1, 2, 4, 1};
        double[] p3 = {10, 2, 5, 1};
        double[] p4 = {0};
        double[] p5 = {};
        double[] test1 = Ex2.substraction(p2, p3);
        double[] test2 = Ex2.substraction(p3, p1);
        double[] test3 = Ex2.substraction(p2, p4);
        double[] test4 = Ex2.substraction(p1, p5);
        assertArrayEquals(new double[]{8.0, 2.0, 2.0, 2.0, 0.0}, test2, Ex2.EPS);
        assertArrayEquals(new double[]{-9.0, 0, -1, 0}, test1, Ex2.EPS);
        assertArrayEquals(p2, test3, Ex2.EPS);
        assertArrayEquals(p1, test4, Ex2.EPS);


    }

    @Test
    public void testPoly() {
        String ans1 = Ex2.poly(po1);
        double[] poly1 = {4, 4, 0, -5, 6, -44, 0};
        String ans2 = Ex2.poly(poly1);
        double[] poly2 = {0, 0.004, -2, -55, 0};
        String ans3 = Ex2.poly(poly2);
        double[] poly3 = {};
        String ans4 = Ex2.poly(poly3);
        double[] poly4 = {7};
        String ans5 = Ex2.poly(poly4);
        assertEquals("-1.0x^3 +3.0x^2 +2.0", ans1);
        assertEquals("-44.0x^5 +6.0x^4 -5.0x^3 +4.0x^1 +4.0", ans2);
        assertEquals("-55.0x^3 -2.0x^2 +0.004x^1 ", ans3);
        assertEquals("", ans4);
        assertEquals("7.0", ans5);


    }

    @Test
    public void testFromString() {
        String p4 = "";
        double[] ans4 = Ex2.getPolynomFromString(p4);
        assertArrayEquals(Ex2.ZERO, ans4);
        String p5 = "4.6";
        double[] ans5 = Ex2.getPolynomFromString(p5);
        assertArrayEquals(new double[]{4.6}, ans5);
        String p1 = "-5.5x^5 +4.4x^4 -3.3x^3 +2.2x^1 -1.1";
        double[] ans1 = Ex2.getPolynomFromString(p1);
        String p3 = "7.5x^5 +30.4x^4 +8.3x^3 +7.0";
        double[] ans3 = Ex2.getPolynomFromString(p3);
        assertArrayEquals(new double[]{-1.1, 2.2, 0.0, -3.3, 4.4, -5.5}, ans1);
        assertArrayEquals(new double[]{7.0, 0.0, 0.0, 8.3, 30.4, 7.5}, ans3);
        double[] ans = Ex2.getPolynomFromString("2.44x^10 -5.8x^7 +4.8x^3 -2.6");
        assertArrayEquals(new double[]{-2.6, 0.0, 0.0, 4.8, 0.0, 0.0, 0.0, -5.8, 0.0, 0.0, 2.44}, ans);
    }

    @Test
    public void testMul() {
        double[] p1 = {2, 0, 3, -1, 0};
        double[] p2 = {1, 2, 4, 1};
        double[] p3 = {10, 2, 5, 1};
        double[] p4 = Ex2.ZERO;
        double[] p5 = {};
        double[] p13 = Ex2.mul(p1, p3);
        double[] p12 = Ex2.mul(p1, p2);
        double[] p23 = Ex2.mul(p2, p3);
        double[] p34 = Ex2.mul(p3, p4);
        double[] p35 = Ex2.mul(p3, p5);
        assertArrayEquals(new double[]{2.0, 4.0, 11.0, 7.0, 10.0, -1.0, -1.0, 0}, p12, Ex2.EPS);
        assertArrayEquals(new double[]{20.0, 4.0, 40.0, -2.0, 13.0, -2.0, -1.0, 0}, p13, Ex2.EPS);
        assertArrayEquals(new double[]{10.0, 22.0, 49.0, 29.0, 24.0, 9.0, 1.0}, p23, Ex2.EPS);
        assertArrayEquals(new double[]{0}, p34, Ex2.EPS);
        assertArrayEquals(new double[]{}, p35, Ex2.EPS);


    }

    @Test
    public void testArea() {
        double area12 = Ex2.area(po1, po2, 0, 2, 50000);
        assertEquals(area12, 17.8536, Ex2.EPS);
        double area13 = Ex2.area(po1, po3, 0, 3, 1000000);
        assertEquals(area13, 91.5, Ex2.EPS);
        double area11 = Ex2.area(po1, po1, -1, 1, 5000);
        assertEquals(area11, 0.0, Ex2.EPS);
        double nobox = Ex2.area(po1, po2, 0, 2, 0);
        assertEquals(nobox, 0.0, Ex2.EPS);

    }

    @Test
    public void testPolynomFromPoints() {
        double[] xx1 = {1, 5, -1};
        double[] yy1 = {3, 2, 4};
        double[] ans1 = Ex2.PolynomFromPoints(xx1, yy1);
        assertArrayEquals(new double[]{3.458, -0.5, 0.04167}, ans1, Ex2.EPS);
        double[] xx2 = {3, 7, -4};
        double[] yy2 = {6, 1, 3};
        double[] ans2 = Ex2.PolynomFromPoints(xx2, yy2);
        assertArrayEquals(new double[]{6.545, 0.2760, -0.1526}, ans2, Ex2.EPS);
        double[] xx3 = {3, 7};
        double[] yy3 = {6, 1, 3};
        double[] ans3 = Ex2.PolynomFromPoints(xx3, yy3);
        assertArrayEquals(new double[]{0, 0, 0}, ans3, Ex2.EPS);


    }
}