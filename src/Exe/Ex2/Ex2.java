package Exe.Ex2;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * In general, such an array {-1,2,3.1} represents the following polynom 3.1x^2+2x-1=0,
 * The index of the entry represents the power of x.
 * <p>
 * Your goal is to complete the functions below, see the marking: // *** add your code here ***
 *
 * @author Uriel Shapiro
 * @id 314779745
 */
public class Ex2 {
    /**
     * Epsilon value for numerical computation, it serves as a "close enough" threshold.
     */
    public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
    /**
     * The zero polynom is represented as an array with a single (0) entry.
     */
    public static final double[] ZERO = {0};

    /**
     * Two polynoms are equal if and only if the have the same coefficients - up to an epsilon (aka EPS) value.
     *
     * @param p1 first polynom
     * @param p2 second polynom
     * @return true if p1 represents the same polynom as p2.
     */
    public static boolean equals(double[] p1, double[] p2) {
        //This function checks if two given polynoms coefficients are the same, by comparing each cell in p1 array with the cell in the same location in p2 array.
        //If the polynoms lengths are not the same, the program will check if the remnant coefficients of the longer array are close enough to epslion. And if they are not, the polynoms are not equal.
        boolean ans = true;            //We assume the polynoms are equal. We will check to see if they are not.
        double[] longarr = p2;        //The function will set p2 as the longer array and insert its value to "longarr" array
        if (p1.length > p2.length)        //If p1 is longer than p2, the function will switch longarr array value to p1.
        {
            longarr = p1;            //p1 is longer, so the program will change "longarr" value to p1.
        }
        for (int i = 0; i < Math.min(p1.length, p2.length); i++)    //For loop that runs on all the cells of both arrays until it reaches the shorter array length.
        {
            if (Math.abs(p1[i] - p2[i]) > EPS)                    //if the absolute value of p1 coefficient minus p2 coefficient is bigger than epsilon, they are not equal. as the assignment requirements. The program will return false.
            {
                return false;                                //The function retuns false because the coefficients are not equal
            }
        }
        for (int j = Math.min(p1.length, p2.length); j < longarr.length - 1; j++)    //for loop that runs on the longer array, the loop begins in the last cell of the short array.
        {
            if (Math.abs(longarr[j]) > EPS)                    //The function checks if the remaining coefficients are bigger than epsilon, if they are - the polynoms are not equal and the function will return false.
            {
                return false;                                //If the the remaining coefficients are bigger than epsilon. the function will return false.
            }
        }
        return ans;                                            //The function returns a boolean value of the question if the arrays are equal.
    }

    /**
     * Computes the f(x) value of the polynom at x.
     *
     * @param poly
     * @param x
     * @return f(x) - the polynom value at x.
     */
    public static double f(double[] poly, double x) {
        //This function computes a given polynom y value at a given x.
        if (poly.length == 0)                        //If the length of the polynom array is 0, there is no polynom to calculate. the function will assert fail.
        {
            fail("Invalid polynom length");        //Assert fail because of invalid length.
        }
        if (poly.length == 1)            //if the polynom length is one, the polynom y value will always be the the coefficient in that cell. therefor, the function will return the coefficient value.
        {
            return poly[0];            //The y value of the polynom is the number that is stored in it's only cell. the function will return that value.
        }
        double ans = 0.0;                    //"ans" variable will sum the value that poly returns for a given x. sum is initialised at 0.
        for (int i = 0; i < poly.length; i++)    //For loop that runs on all of the cells in poly array. in order to sum the value of the polynom returns for a given x.
        {
            ans = ans + (poly[i] * Math.pow(x, i));    //ans adds to itself the value of the coefficient * given x in the power of the index of the coefficient.
        }
        return ans;            //ans value is the y value of poly for a given x. the function will return that value.
    }

    /**
     * Computes a String representing the polynom.
     * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
     *
     * @param poly the polynom represented as an array of doubles
     * @return String representing the polynom:
     */
    public static String poly(double[] poly) {
        //This function convert an array of digits which represent the coefficients of a polynom, to string.
        //This function adds the correct power of x in dependence to its coefficient placement in the given array.
        String ans = "";                        //ans is the string which will store the finalised polynom. it is initialized at "".
        if (poly.length == 0)                    //If the array length is 0, there is nothing to convert to string. the function will return ans, which at this point is still set to "".
        {
            return ans;                        //The program will return ans, becuase there is nothing to convert. ans is still set to "".
        }
        if (poly.length == 1)                    //If the array length is 1. there are no coefficients of x in that array (you could say that is the coefficient of x^0). The function will convert the only digit in the array to string and return that value.
        {
            ans = String.valueOf(poly[0]);    //implying the value of the only number in the array to the polynom.
            return ans;                        //there are no other coefficients in the array, the function will return the string ans.
        }
        if (poly[poly.length - 1] != 0)            //The function starts from the last coefficient to the fist, so it will be arranged correctly in the string. If the last coefficient is not 0, the function will imply its value to the beginning of the string, with the power of x which is the location of the cell.
        {
            ans = poly[poly.length - 1] + "x^" + (poly.length - 1) + " ";        //ans string receives the value of the coefficient of the highest power of x in the given polynom. the function will add "x^" with the number of the placement of the cell in the array. At the end, the function will add space to the string. for easier reading.
        }
        for (int i = poly.length - 2; i > 0; i--)        //For loop that runs on all of the cells in the array from the last to one before the first cell. so that they will be arranged in the string from highest power to the lowest. The function excludes the first and last cells in the array in order to write them correctly (with or without + and without "x^").
        {
            if (poly[i] > 0)                                //if the coefficient is bigger then 0, it is positive, the function will add + before the number. The function exclude the number 0, because it has no effect on the polynom.
            {
                ans = ans + "+";                            //adding "+" to the string before the positive number
                ans = ans + poly[i];    //inserting the number from the array to the string
                ans = ans + "x^" + i + " ";                //Adding "x^" with the power of the index of the cell in the array, to the string.
            }
            if (poly[i] < 0)                                //If the coefficient is smaller than 0, it is negative. there is no need to add "+" before the number (it comes with "-").
            {
                ans = ans + poly[i];        //inserting the number from the array to the string
                ans = ans + "x^" + i + " ";                    //Adding "x^" with the power of the index of the cell in the array, to the string.
            }
        }
        if (poly[0] > 0)                                    //Checking if the last number (the coefficient of x^0) is positive or negative. That coefficient is in a separate "if" block because the function won't add "x^" after it in the string.
        {
            ans = ans + "+";                                //if the number is positive, the function will add "+" before it.
            ans = ans + poly[0];        //Inserting the number in the first cell in the array to the end of the string.
        }
        if (poly[0] < 0)                                    //Checking if the last number is smaller than 0. if it does, there is no need to add "+" before it.
        {
            ans = ans + poly[0];        //Inserting the number in the first cell of the array to the end of the string.
        }
        return ans;                                        //returning the string ans, which contains the conversion of the array polynom to string.
    }

    /**
     * Given a polynom (p), a range [x1,x2] and an epsilon eps.
     * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps,
     * assuming p(x1)*p(x2) <= 0.
     * This function should be implemented iteratively (none recursive).
     *
     * @param p   - the polynom.
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
     */
    public static double root(double[] p, double x1, double x2, double eps) {
        //This function will find the x value that |f(x)| < epsilon, therefore is close to 0 and is the root of the polynom.
        if (p.length == 0)                        //If there are not coefficients in the polynom array, there will be no root to calculate and the function will assert a fail.
        {
            fail("Invalid polynom length");    //The function will fail due to invalid array length.
        }
        if (p.length == 1 && p[0] != 0)            //If there is only the C coefficient, and it is not 0, there will be no root and the function will assert a fail.
        {
            fail("This function has no root");    //Fail because there is no root.
        }
        double x12 = (x1 + x2) / 2;        //This function uses the bisection method. it will narrow the range until |f(x)|< epsilon. According to: https://he.wikipedia.org/wiki/%D7%A9%D7%99%D7%98%D7%AA_%D7%94%D7%97%D7%A6%D7%99%D7%99%D7%94
        double fx12 = f(p, x12);        //fx12 contains the y value of the polynom at x12. makes the function run faster by not checking that value from the f function each time afterwards.
        while (Math.abs(fx12) > eps)    //The function will run until the |f(x12)| will be lower then epsilon. as the assignment requirements.
        {
            if (f(p, x1) * fx12 < 0)        //If f(x1)*f(x12)<0, one of them is above 0 and one of them is below. the root is between them.
            {
                x2 = x12;                //We know that the root is between x1 and x12, and we want to trim the range. therefore the function will set x2 to x12 (so that the range is trimmed to |x1 - x12|
            } else                    //If f(x1)*f(x12)>=0, the root is not between x1 and x12, so we could trim that range. and set the new range from x12 to x2
            {
                x1 = x12;                //the root is not in the range between x1 to x12, so it must be between x12 to x2. we will narrow the range to that.
            }
            x12 = (x1 + x2) / 2;            //we want to narrow the range more and more each time f(x12) is bigger then epsilon (and therefore x12 is not yet the x of the root). x1 and x2 value depends on the conditions before, so taking the avrage of them, narrows the range in the direction of the root.
            fx12 = f(p, x12);        //Updates fx12 variable with the new f(x12) value.
        }
        return x12;                    //When |f(x12)|<epsilon, the function will print the x value of the root (which is stored in x12)
    }

    /**
     * Given a polynom (p), a range [x1,x2] and an epsilon eps.
     * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps,
     * assuming p(x1)*p(x2) <= 0.
     * This function should be implemented recursivly.
     *
     * @param p   - the polynom
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
     */
    public static double root_rec(double[] p, double x1, double x2, double eps) {
        //This function calculates the root of polynom "p" by recursivly calling the function until f(x12)<epsilon.
        if (p.length == 0)                        //If there are not coefficients in the polynom array, there will be no root to calculate and the function will assert a fail.
        {
            fail("Invalid polynom length");    //The function will fail due to invalid array length.
        }
        if (p.length == 1 && p[0] != 0)            //If there is only the C coefficient, and it is not 0, there will be no root and the function will assert a fail.
        {
            fail("This function has no root");    //Fail because there is no root.
        }
        double x12 = (x1 + x2) / 2;            //This function uses the bisection method. it will narrow the range until |f(x)|< epsilon. According to: https://he.wikipedia.org/wiki/%D7%A9%D7%99%D7%98%D7%AA_%D7%94%D7%97%D7%A6%D7%99%D7%99%D7%94
        if (Math.abs(f(p, x12)) > eps)        //If |f(x12)>epsilon, x12 is not the x value of the root, therefore we want to change its value.
        {
            if (f(p, x1) * f(p, x12) < 0)        //If f(x1)*f(x12)<0, one of them is above 0 and one of them is below. and the root is between them
            {
                x2 = x12;                    //Because the root is between x1 and x12 (x12 is between x1 to x2), the function will narrow the range so that the new range will be x1 to x12.
            } else                        //If f(x1)*f(x12)=positive, the root is not between x1 to x12.
            {
                x1 = x12;                    //The function will narrow the range and set it from x12 to x2
            }
        }
        if (Math.abs(f(p, x12)) < eps)        //If |f(x12)|<epsilon, x12 is the x value of the root.
        {
            return x12;                    //The function will return the value of x12.
        }
        return root_rec(p, x1, x2, eps);    //If |f(x12)|>epsilon, the function will run again with the new x1 and x2 (The function will narrow the range each recursive call, until |f(x12)|<epsilon.
    }

    /**
     * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
     * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
     *
     * @param p1  - first polynom
     * @param p2  - second polynom
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p1(x) -p2(x)| < eps.
     */
    public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
        //This function will check the meeting point of two given arrays by subtracting one from the other, and then finding the "root" of the subtracted polynom (which is the meeting point of the given polinoms).
        if (p1.length == 0 || p2.length == 0)                                            //If one of the polynoms array has no length, there will be no meeting point. The function will assert a fail.
        {
            fail("There is no meeting point for the polynoms due to invalid polynom");    //Fail due to invalid polynom.
        }
        double[] meeting = substraction(p1, p2);        //Setting "meeting" array as the subtraction between p1 and p2, using the subtraction function.
        double x12 = root(meeting, x1, x2, eps);    //x12 will get the value of the root of "meeting" in the range x1 to x2, using the "root" function.
        return x12;                                    //The function will return x12, which is the meeting point of p1 and p2.
    }

    /**
     * Given two polynoms (p1,p2), a range [x1,x2] and an integer representing the number of "boxes".
     * This function computes an approximation of the area between the polynoms within the x-range.
     * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
     *
     * @param p1            - first polynom
     * @param p2            - second polynom
     * @param x1            - minimal value of the range
     * @param x2            - maximal value of the range
     * @param numberOfBoxes - a natural number representing the number of boxes between xq and x2.
     * @return the approximated area between the two polynoms within the [x1,x2] range.
     */
    public static double area(double[] p1, double[] p2, double x1, double x2, int numberOfBoxes) {
        //This function calculates the area between 2 polynoms using Riemann's like integral.
        //It will do so by receiving the number of "boxes" to calculate.
        //One side of the rectangle is the distance between x1 and x2, which is the given range. divided by the number of boxes. And the other side of the rectangle is the distance between one polynom to the other polynom at given x value.
        if (p1.length == 0 || p2.length == 0)                                            //If one of the polynoms array has no length, there will be no meeting point. The function will assert a fail.
        {
            fail("There is no meeting point for the polynoms due to invalid polynom");    //Fail due to invalid polynom.
        }
        if (numberOfBoxes == 0)        //If the number of boxes is 0, the area will always be 0.
        {
            return 0.0;                //The function will return 0.0.
        }
        double ans = 0.0;                                        //Setting a variable to sum the area that the boxes cover.
        if (p1 == p2)                                                //If both of the polynoms are equal, there is no area between them. the function will return 0.
        {
            return ans;                                            //The function will return "ans" which, at this point, is set to 0.
        }
        double boxbottom = Math.abs(x1 - x2) / numberOfBoxes;        //The bottom side of the rectangle is the distance between the start of the given range (x1) to the end of the range (x2) divided by the number of boxes.
        for (int i = 0; i < numberOfBoxes; i++)                        //For loop which will run in dependence to the number of boxes.
        {
            double x = x1 + (boxbottom * i);                        //The x value which we want to calculate f(x) for. initial value is the start of the range (x1 + numberOfBoxes * 0)
            ans = ans + Math.abs(f(p1, x) - f(p2, x)) * boxbottom;    //sum of the boxes. each box is the distance between the polynom at a given x. multiplied by the bottom of the box, which is the the range dividided by the number of the boxes.
        }
        return ans;                                                //The function returns the answer, which is the sum of the area of the boxes. which is the area between the polynoms.
    }

    /**
     * This function computes the array representation of a polynom from a String
     * representation. Note:given a polynom represented as a double array,
     * getPolynomFromString(poly(p)) should return an array equals to p.
     *
     * @param p - a String representing polynom.
     * @return
     */
    public static double[] getPolynomFromString(String p) {
        //This Function receive a polynom as a string and convert it to an array, sorted in a way that the power of the coefficient is the index of the cell it is in.
        //The function does it by removing all unnessccery symbols ("x","^","+"), so that only the coefficients and their power are left.
        //The function will split the strin into an array, in a way that in every even indexed cell there is a coefficient and in every odd cell it's his power. The function will then sort the the coefficients to a new array by their power.
        if (p.length() == 0)        //If the string length is 0, there is nothing to convert to array.
        {
            return ZERO;        //The function has nothing to convert, so it will return the zero polynom.
        }
        if (!p.contains("x"))    //If the received polynom does not contain "x", the only coefficient is of x^0. The function will convert the polynom to an array and return it.
        {
            String[] nocooef = new String[1];                    //New string array to insert the number to (and to convert that string array to double array later).
            nocooef[0] = p;                                        //Insert the value in the polynom to the first (and only) cell in the string array.
            double[] nocooefarray = new double[1];                //New double array to return in the end. this array has only one cell (because it needs to store only x^0 coefficient).
            nocooefarray[0] = Double.parseDouble(nocooef[0]);        //Insert the value from the string array to the double array.
            return nocooefarray;                                //Return the double array, which stores the only number in the polynom.
        }
        p = p.replace("x", " ");        //Replaces all "x" in the string with a space.
        p = p.replace("+", "");        //Removes all "+" from the string
        p = p.replace("^", "");        //Removes all "^" from the string
        String[] cutp = p.split(" ");                        //The function will split the string to an array. it will split it every time there is a space. That way the coefficients and their power are on the consecutive indexed cell. The function will later sort the array by that order.
        double[] power = new double[(cutp.length / 2)];        //New array that will include only the the power of the coefficients.
        int power_index = 0;                                            //Integer k will be used as index number for the power array.
        for (int i = 1; i < cutp.length; i = i + 2)                    //For loop that will run on the odd indexed cell on the string array. as i said, the power of the coefficients are stored only on the odd numbered cells. In this loop we want to take only these numbers.
        {
            power[power_index] = Double.parseDouble(cutp[i]);            //The function will convert each odd cell in the string array to double and insert it's value to the power array in index power_index. The index will progress every loop (can't use the i index because the power array is too short)
            power_index++;                                            //Adds 1 to to power_index each loop, in order to advance in cells that the function insert to.
        }
        double[] coefficients = new double[(int) power[0] + 1];                //New array that will contain the sorted coefficients. the length of the array is the length of the power array, because it includes all the of the powers of the coefficients +1 so that the x^0 number will have place.
        power_index = 0;                                                //Initialise power_index at 0, in order to use it again.
        for (int i = 0; i < cutp.length - 1; i = i + 2)                            //For loop that will run on all of the even indexed cells in the string array, We know that the coefficients are in the even indexed cells in that array. The function will insert their value to the coefficients array.

        {
            coefficients[(int) power[power_index]] = Double.parseDouble(cutp[i]);        //The function will insert each coefficient from the string array to the coefficients array in the index of that coefficient, using the power array as an index.
            power_index++;                                                            //Advances power_index by 1 every loop, in order to reach to the next cell in the power array.
        }
        coefficients[0] = Double.parseDouble(cutp[cutp.length - 1]);                        //Only the coefficients are currently in the coefficients array. This function will include the last number in the string array (the coefficient of x^0) to the coefficients array.

        return coefficients;
    }

    /**
     * This function computes the polynom which is the sum of two polynoms (p1,p2)
     *
     * @param p1
     * @param p2
     * @return
     */
    public static double[] add(double[] p1, double[] p2) {
        //This function sums two given polynoms and returns the sum.
        if (p1 == ZERO || p1.length == 0)    //If the first given polynom is {0} or {}, it adds nothing to the sum. the function will return the second polynom.
        {
            return p2;                    //Because the first polynom has no effect to the sum, the function returns the second polynom as the sum.
        }
        if (p2 == ZERO || p2.length == 0)    //If the second given polynom is {0} or {}, it adds nothing to the sum. the function will return the first polynom.
        {
            return p1;                    //Because the second polynom has no effect to the sum, the function returns the first polynom as the sum.
        }
        //In order not to call a cell that exceed the array length, the function will check which of the arrays has higher length.
        double[] shortarr = p1;            //The function assumes p1 is the shorter array
        double[] longarr = p2;            //The function assumes p2 is the longer array
        if (p1.length > p2.length)            //If the last assumptions were wrong, p1<p2, the function will replace the variables with the correct arrays
        {
            shortarr = p2;                //Setting p2 as the short array
            longarr = p1;                //Setting p1 as the long array
        }
        double[] addition = new double[longarr.length];        //New array that will store the sum of the two given arrays. It's length is of the longer array of the two.
        for (int i = 0; i < shortarr.length; i++)                    //For loop that runs from the first cell to the last cell of the shorter array. In order not to call a non existing cell in the shorter array.
        {
            addition[i] = p1[i] + p2[i];                        //Inserting the sum of the cells at each index of the two polynoms to the sum polynom ("addition") at that index.
        }
        //For loop that runs on the remaining cells that were not added in the previous loop.
        //Inserting each cell of the longer array to the same index cell in the sum array.
        System.arraycopy(longarr, shortarr.length, addition, shortarr.length, longarr.length - shortarr.length);
        return addition;        //The function returns the "addition" array, which stores the sum of p1 and p2.
    }

    /**
     * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
     *
     * @param p1
     * @param p2
     * @return
     */
    public static double[] mul(double[] p1, double[] p2) {
        //This function multiples two given polynoms and return the answer.
        if (p1.length == 0 || p2.length == 0)        //if one of the functions length is 0, there is nothing to multiply. the function will return {} (We weren't taught how to assert an error).
        {
            double[] nothing = {};        //Setting a new array with the value {}. which is the answer to multiplying a polynom with {}.
            return nothing;                //The function will return {} as the answer.
        }
        if (p1 == ZERO || p2 == ZERO)        //if one of the polynoms is {0}, the answer will always be {0}. The function will return {0}.
        {
            return ZERO;                //The function will return {0} as the answer (ZERO is a saved word for {0}).
        }
        double[] ans = new double[(p1.length + p2.length) - 1];        //"ans" array is the array that will store the value of the multiplication. it's length is the length of the first polynom + the second polynom minus 1. Because there will be 2 displays of x^0 if we weren't subtract 1. and the sum is because x^t+x^z=x^(t+z).
        //In order to multiply the arrays, we will multiply each cell in the first array with every cell of the second array. And according to the laws of power in mathematics, the answer to each multiplication will be inserted to the new ans array at (p1_index+p2_index).
        for (int i = 0; i < p1.length; i++)    //For loop that runs on all of the cells in p1
        {
            for (int j = 0; j < p2.length; j++)    //For loop that runs on all of the cells in p2
            {
                ans[i + j] += p1[i] * p2[j];    //ans array adds to himself the value of p1*p2 at a given index. the answer will be stored in (p1_index+p2_index) because of the laws of power in mathematics.
            }
        }
        return ans;        //The function will return the "ans" array, which contains the multiplied coefficients from each polynom.
        // **************************
    }

    /**
     * This function computes the derivative polynom of po.
     *
     * @param po
     * @return
     */
    public static double[] derivative(double[] po) {
        //This function calculates the derivative of a given polynom according to derivative laws.
        if (po.length == 0)                        //If the polynom length is 0, there is nothing to derivative. the function will assert a fail.
        {
            fail("Invalid polynom length");        //Fail due to invalid polynom length.
        }
        if (po.length == 1)    //If the polynom length is 1, there is no coefficient to x in that polynom, therefor the derivative is 0.
        {
            return ZERO;    //The function will return the polynom zero as the answer.
        }
        double[] deriv = new double[po.length - 1];    //Setting a new array that will contain the derivative of the given polynom. It's length is of the original polynom minus 1, because x^0 coefficient is not included in the derivative.
        //The idea to which the function calculates the derivative of the polynom. Is by inserting the value of the polynom at a given index multiplied by that index, to that index minus 1 at the derivative polynom ("deriv").
        for (int i = 1; i <= deriv.length; i++)            //For loop that runs from the second cell in the given polynim to the last cell in the derivative polynom.that is because the first cell in the original polynom should not be included in the derivative (according to the laws of derivative).
        {
            deriv[i - 1] = deriv[i - 1] + i * po[i];            //Inserting the value of a cell in the given polynom * the index of that cell to a cell in the derivative polynom at index-1 cell (because "deriv" does not include the first cell from the original polynom, the function skip it and set each value afterwards to index-1 cell, so that the derivative polynom will be sorted correctly).
        }
        return deriv;        //The function returns "deriv" array, which contains the derivative of the given polynom.
        // **************************
    }

    /**
     * This function computes a polynomial representation from a set of 2D points on the polynom.
     * Note: this function only works for a set of points containing three points, else returns null.
     *
     * @param xx
     * @param yy
     * @return an array of doubles representing the coefficients of the polynom.
     * Note: you can assume xx[0]!=xx[1]!=xx[2]
     */
    public static double[] PolynomFromPoints(double[] xx, double[] yy) {
        //This function calculates the coefficients of a polynom that "goes through" 3 given coordinates.
        double[] ans = new double[3];        //The polynom is quadratic, therefore, the highest power of it is 2. The answer will only have 3 coefficients - so the ans array has 3 cells.
        if (xx != null && yy != null && xx.length == 3 && yy.length == 3)    //Checking that the given arrays of the coordinates are set so that the function could find a polynom that goes thorugh them.
        {
            //This function calculates the coefficients of a polynom that "goes through" 3 given coordinates.
            //The formula was taken from (https://math.stackexchange.com/questions/889569/finding-a-parabola-from-three-points-algebraically)
            double mechane = (xx[0] - xx[1]) * (xx[0] - xx[2]) * (xx[1] - xx[2]);
            if (mechane != 0)        //If "mechane" is 0, the function will divide by 0. the function will check that the "mechane" is not 0 before dividing.
            {
                ans[2] = (xx[2] * (yy[1] - yy[0]) + xx[1] * (yy[0] - yy[2]) + xx[0] * (yy[2] - yy[1])) / mechane;    //The coefficient of x^2
                ans[1] = (Math.pow(xx[0], 2) * (yy[1] - yy[2]) + Math.pow(xx[2], 2) * (yy[0] - yy[1]) + Math.pow(xx[1], 2) * (yy[2] - yy[0])) / mechane; //The coefficient of x
                ans[0] = (Math.pow(xx[1], 2) * (xx[2] * yy[0] - xx[0] * yy[2]) + xx[1] * (Math.pow(xx[0], 2) * yy[2] - Math.pow(xx[2], 2) * yy[0]) + xx[0] * xx[2] * (xx[2] - xx[0]) * yy[1]) / mechane; //coefficient C
            } else {
                fail("The coordinates are invalid.");
            }
        }
        return ans;        //The function returns the ans array which contains the coefficients of the polynom that goes through the 3 given coordinates.
    }

    ///////////////////// Private /////////////////////
    public static double[] substraction(double[] p1, double[] p2) {
        //This function subtracts p2 from p1, by adding -1*p2 to p1.
        if (p2 == ZERO || p2.length == 0) //If p2 is {} or {0}, subtracting it from p1 won't affect p1.
        {
            return p1;                //The program will return p1.
        }
        double[] longarr = p2;        //The function will set p2 as the longer array and insert its value to "longarr" array
        if (p1.length > p2.length)        //If p1 is longer than p2, the function will switch longarr array value to p1.
        {
            longarr = p1;            //p1 is longer, so the program will change "longarr" value to p1.
        }
        double[] sub = new double[longarr.length];        //new array which will store the subtraction of p2 from p1. will store values from both arrays, therefore needs to be as long as the longer array of the two.
        for (int i = 0; i < p2.length; i++)                    //For loop that runs on all of the length of p2 array.
        {
            sub[i] = -1 * p2[i];                            //Multiplying p2 by -1 and inserting that value to "sub" array. "sub" array will later be added to p1.
        }
        sub = add(p1, sub);                                //"sub" array new value is the sum of sub and p1, which in other words is p1+(-p2)=p1-p2.
        return sub;                                        //The function will return the result of p1-p2, which is stored in "sub" array.
    }
}