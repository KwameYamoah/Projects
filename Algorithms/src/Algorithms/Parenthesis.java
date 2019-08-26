package Algorithms;

import java.util.ArrayList;

public class Parenthesis {
    /***
     * Program to check if a syntactically correct expression is valid(opening braces matches closing braces)
     * @param expression is the expression supplied
     * @return true if valid,else false
     */
    static boolean isValid(String expression) {
        if (expression.equals("")) return true;          //if empty
        if (expression.length() % 2 == 1) return false;      //if odd
        if (expression.length() == 1) return false;        //if has size of 1

        ArrayList<Character> opened_brackets = new ArrayList<>();
        opened_brackets.add('{');
        opened_brackets.add('[');
        opened_brackets.add('(');
        ArrayList<Character> closed_bracket = new ArrayList<>();
        closed_bracket.add('}');
        closed_bracket.add(']');
        closed_bracket.add(')');

        if (closed_bracket.contains(expression.charAt(0))) return false; //if it starts with closing brace

        ArrayList<Character> stack = new ArrayList<>();     //holds consecutive opening braces
        ArrayList<Character> stack2 = new ArrayList<>();    //holds consecutive closing braces

        int index = 0;    // points to current character

        while (index < expression.length()) {        //while not at end of expression

            //stores consecutive opening braces into stack
            while (index < expression.length() && opened_brackets.contains(expression.charAt(index))) {
                stack.add(expression.charAt(index));
                index++;
            }

            System.out.println("Stack1 - " + stack);

            //stores consecutive closing braces into stack2
            while (index < expression.length() && closed_bracket.contains(expression.charAt(index))) {
                stack2.add(expression.charAt(index));
                index++;
            }
            System.out.println("Stack2 - " + stack);


            if (stack.size() != stack2.size()) return false;      //if stack sizes are uneven -> invalid expression
            else {                                               //else check if corresponding braces are equal

                System.out.println(stack);
                System.out.println(stack2);
                while (stack.size() != 0) {
                    char pop_stack_1 = stack.get(stack.size() - 1);     //end of opening braces list to start from inner parenthesis
                    stack.remove(stack.size() - 1);

                    char pop_stack_2 = stack2.get(0);                   //first of closing braces to start from inner parenthesis
                    stack2.remove(0);
                    if (!oppositeOf(pop_stack_1, pop_stack_2))
                        return false;      //if braces aren't opposite - > invalid expression
                }
            }
        }

        return true;    // if all checks are passed, it must be a valid expression
    }

    private static boolean oppositeOf(char pop_stack_1, char pop_stack_2) { //checks if pop_stack_2 is the closing brace of pop_stack_1
        switch (pop_stack_1) {
            case '{':
                if (pop_stack_2 == '}') return true;
                break;
            case '[':
                if (pop_stack_2 == ']') return true;
                break;
            case '(':
                if (pop_stack_2 == ')') return true;
                break;
        }
        return false;
    }

}

class Main {
    public static void main(String[] args) {
        String exp1 = "()(){(())}";
        String exp2 = "";
        String exp3 = "([{}])()";
        String exp4 = "(){}[]";
        String exp5 = "[{()}][{}]";
        System.out.println(Parenthesis.isValid(exp1));
        System.out.println(Parenthesis.isValid(exp2));
        System.out.println(Parenthesis.isValid(exp3));
        System.out.println(Parenthesis.isValid(exp4));
        System.out.println(Parenthesis.isValid(exp5));
    }
}


