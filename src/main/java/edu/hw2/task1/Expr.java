package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    public record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return this.value;
        }

        @Override
        public String toString() {
            return "" + this.value;
        }
    }


    public record Negate(Expr expr) implements Expr {
        @Override
        public double evaluate() {
            return -this.expr.evaluate();
        }

        @Override
        public String toString() {
            return "(-" + this.expr.toString() + ")";
        }
    }

    public record Exponent(Expr expr, double exponent) implements Expr {

        @Override
        public double evaluate() {
            return Math.pow(this.expr.evaluate(), this.exponent);
        }

        @Override
        public String toString() {
            return this.expr.toString() + "^" + this.exponent;
        }
    }

    public record Addition(Expr expr1, Expr expr2) implements Expr {

        @Override
        public double evaluate() {
            return this.expr1.evaluate() + this.expr2.evaluate();
        }

        @Override
        public String toString() {
            return "(" + this.expr1.toString() + " + " + this.expr2.toString() + ")";
        }
    }

    public record Multiplication(Expr expr1, Expr expr2) implements Expr {

        @Override
        public double evaluate() {
            return this.expr1.evaluate() * this.expr2.evaluate();
        }

        @Override
        public String toString() {
            return "(" + this.expr1.toString() + " * " + this.expr2.toString() + ")";
        }
    }
}
