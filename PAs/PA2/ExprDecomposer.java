/*
    Name: Tonia Le
    PID:  A15662706
*/

import java.util.EmptyStackException;

/**
 * Class Expr Decomposer used IntStack to decompose a normal arithmetic expression into a special
 * kid of expression
 *
 * @author Tonia Le
 * @since 01-19-20
 */
public class ExprDecomposer {

    public char[] decompose(String expr) {
        /* TODO */

        return null;
    }

    /**
     * UTILITY METHOD, DO NOT MODIFY *
     * Check if the given token represents a digit
     * @param token to check
     * @return boolean true if token is a digit, false otherwise
     */
    private boolean isDigit(char token) {
        return (token >= '0') && (token <= '9');
    }

    /**
     * UTILITY METHOD, DO NOT MODIFY *
     * Check if the given token represents an operator
     * @param token to check
     * @return boolean true if token is an operator, false otherwise
     */
    private boolean isOperator(char token) {
        return (token == '+') || (token == '-') || (token == '*') || (token == '/');
    }

    /**
     * Inner class CharStack.
     * Note: You can remove methods and variables that you will not use for
     * this question, but you must keep both push() and pop() methods and they
     * should function properly.
     */
    protected class CharStack {

        /* TODO: add variables, constructors and methods that
                 you will use to implement the decompose() method.  */

        public void push(char element) {
            /* TODO: must implement */
            return;
        }

        public char pop() {
            /* TODO: must implement */
            return 0;
        }
    }
}
