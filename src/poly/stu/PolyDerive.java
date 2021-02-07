package poly.stu;

import java.util.Arrays;

/**
 * This class can compute the derivative of a polynomial.
 *
 * @author RIT CS
 * @author Clinten Hopkins
 */
public class PolyDerive {

    /**
     * Unused constructor, made private to avoid javadoc generation.
     */
    private PolyDerive() {
    }

    /**
     * Computes the derivative for a polynomial.  For example:
     * <pre>
     * poly=[1]: [0]
     * poly=[3, -1]: [-1]
     * poly=[0, 3]: [3]
     * poly=[2, -1, -2, 1]: [-1, -4, 3]
     * poly=[-5, 0, 0, 3, 3, 1]: [0, 0, 9, 12, 5]
     * </pre>
     *
     * @param poly A native array representing the polynomial, in reverse order.
     * @rit.pre poly is not an empty array.  Minimally it will contain
     *      a constant term.
     * @return A polynomial as a native array in reverse order.
     */
    public static int[] computeDerivative(int[] poly) {

        if (poly.length <= 1) {
            return new int[]{0};
        }

        int[] deriv= new int[poly.length - 1];

        // create a new int array that removes the first item from the poly array keeps the rest (to get rid of the
        // deriv. of a constant being 0 causing a incorrect number of terms)
        int[] temp = new int[poly.length - 1];
        for (int i = 0; i < poly.length - 1; i++) {
            temp[i] = poly[i + 1];
        }

        // actually calculate the derivative of each remaining term
        for (int i = 0; i < temp.length; i++) {
            deriv[i] = temp[i] * (i + 1);
        }

        return deriv;
    }
}
