package edu.hw2.task1;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExprTest {

    @Test
    void test1() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));

        String answer = res + " = " + res.evaluate();
        assertEquals("(((2.0 + 4.0) * (-1.0))^2.0 + 1.0) = 37.0", answer);
    }

    @Test
    void test2() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var mult = new Expr.Exponent(new Expr.Addition(two, four), 2);

        assertEquals(36.0, mult.evaluate());
    }

}
