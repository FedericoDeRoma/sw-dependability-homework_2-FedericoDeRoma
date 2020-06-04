package org.magee.math;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.junit.runner.RunWith;
import org.magee.math.Rational;

public class RationalTest {

    /* This method test the following operation: -[(-1/-1)-(-1)]
    The result expected should be -2, not 0.
    The validity of the test is due to the fact that the method subtract(long) in the production class convert the integer
    into rational in a wrong way. It has to use 1 as denominator instead of (-1).
     */
    @Test
    public void test01()  throws Throwable  {
        Rational rational0 = new Rational((-1L), (-1L));
        Rational rational1 = rational0.subtract((-1L));
        Rational rational2 = rational1.negate();
        assertEquals(-2.0, rational2.doubleValue(), 0.01); //OK
    }


    /* This method test the following operation: |-1/-1| * (-1/-1) = 1/1 * (-1/-1) = -1/-1 = 1
    The method multiply(Rational) in the production class divides the numerators instead of multiplying them.
    */
    @Test
    public void test02()  throws Throwable  {
        Rational rational0 = new Rational((-1L), (-1L));
        Rational rational1 = rational0.abs();
        Rational rational2 = rational1.multiply(rational0);
        assertEquals((-1L), rational2.numerator);
        assertEquals((byte) 1, rational1.byteValue());  //OK
    }

    /* This method test the following operation: [-(-2685/-2685)]^(-1) = -1
     */
    @Test
    public void test03()  throws Throwable  {
        Rational rational0 = new Rational((-2685L), (-2685L));
        Rational rational1 = rational0.negate();
        Rational rational2 = rational1.inverse();
        assertEquals(2685L, rational2.denominator);
        assertEquals((-2685L), rational0.numerator);
        assertEquals((short) (-1), rational2.shortValue());
    }

    /* This method test the following operation: -1432/0 * 1
       This operation is not allowed because the denominator is 0!
    */
    @Test
    public void test04()  throws Throwable  {
        Rational rational0 = new Rational((-1432L), 24840256L);
        rational0.denominator = 0L;
        // Undeclared exception!
        try {
            rational0.multiply(1L);
            fail("Expecting exception: NumberFormatException");

        } catch(NumberFormatException e) {
            //
            // Cannot create a Rational object with zero as the denominator
            //
            verifyException("org.magee.math.Rational", e);
        }
    }

    /* This method test the following operation: (667/1415) / (667/1415) = (667/1415) * (1415/667) = 943805/943805 = 1
      The problem it's the same as the test02 -> the method multiply(Rational) in the production class has a bug.
    */
    @Test
    public void test05()  throws Throwable  {
        Rational rational0 = new Rational(667L, 1415L);
        Rational rational1 = rational0.divide(rational0);
        float float0 = rational1.floatValue();
        assertEquals(1.0F, float0, 0.01F);  //OK
        assertEquals(0.4713781F, rational0.floatValue(), 0.01F);
        assertEquals(943805L, rational1.denominator);
    }

    /* This method test the following operation: (-1/-1) / (-1/-1) = (-1/-1) * (-1/-1) = 1/1 = 1
     */
    @Test
    public void test06()  throws Throwable  {
        Rational rational0 = new Rational((-1L), (-1L));
        Rational rational1 = rational0.divide(rational0);
        assertEquals((-1L), rational0.denominator);
        assertEquals(1L, rational1.longValue());
    }

    /* This method test the following operation: (2/2)^0 = 2^0/2^0 = 1/1 = 1
     */
    @Test
    public void test07()  throws Throwable  {
        Rational rational0 = new Rational(2L, 2L);
        Rational rational1 = rational0.pow(0);
        assertEquals(1L, rational1.numerator);
        assertEquals((short)1, rational0.shortValue());
        assertEquals(1.0, rational1.doubleValue(), 0.01);
    }

    /* This method test the following operation: 1439/1439 + 0 = 1
    The method add(long) in the production class convert in a wrong way the integer into a rational because it uses 0
    as a denominator raising a NumberFormatException.
    */
    @Test
    public void test08()  throws Throwable  {
        Rational rational0 = new Rational(1493L, 1493L);
        // Undeclared exception!
        try {
            rational0.add(0L);
            fail("Expecting exception: NumberFormatException");

        } catch(NumberFormatException e) {
            //
            // Cannot create a Rational object with zero as the denominator
            //
            verifyException("org.magee.math.Rational", e);
        }
    }

    /* This method test the following operation: 0/2503 = 0/0 - (-4002) = 1
    This operation is not allowed because there is 0 as denominator.
    */
    @Test
    public void test09()  throws Throwable  {
        Rational rational0 = new Rational(0L, 2503L);
        rational0.denominator = 0L;
        // Undeclared exception!
        try {
            rational0.subtract((-4002L));
            fail("Expecting exception: NumberFormatException");

        } catch(NumberFormatException e) {
            //
            // Cannot create a Rational object with zero as the denominator
            //
            verifyException("org.magee.math.Rational", e);
        }
    }

    /* This method test the following operation: 1/1 + null
    This operation is not allowed!
    */
    @Test
    public void test10()  throws Throwable  {
        Rational rational0 = new Rational(1L, 1L);
        // Undeclared exception!
        try {
            rational0.add((Rational) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            verifyException("org.magee.math.Rational", e);
        }
    }
}
