package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author: liming
 * @Date: 2020/1/20 16:25
 * @Description: 逆波兰表达式
 */

public class ReversePolishDemo {

    public static void main(String[] args) {

        //后缀表达式的计算求值：
//          从左至右扫描表达式，
//          遇到数字时，将数字压入堆栈，
//          遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算(次顶元素和栈顶元素)，并将结果入栈
//          重复上述过程直到表达式最右端，最后运算得出的值为表达式的结果

//        例如：(3+4)*5-6 对应的后缀表达式(逆波兰表达式)为：3 4 + 5 * 6 -
//        String expression = "3 4 + 5 * 6 -";
//        例：(((100-50)*2+10)*4-100)/10 对应的后缀表达式(逆波兰表达式)为：100 50 - 2 * 10 + 4 * 100 - 10 /
//        String expression = "100 50 - 2 * 10 + 4 * 100 - 10 /";
//        例：(100-50)*2+(100*4-100)/10 对应的后缀表达式(逆波兰表达式)为：100 50 - 2 * 100 4 * 100 - 10 / +

        /*String reversePolishExpression = "10 2 10 * + 10 - 2 *";
        String[] strings = reversePolishExpression.split(" ");

        List<String> list = Arrays.asList(strings);

        System.out.println(calculate(list));*/

        // 中缀表达式转后缀表达式(逆波兰表达式)
//        思路分析:
//        1) 初始化两个栈：运算符栈 s1 和储存中间结果栈 s2
//        2) 从左至右扫描中缀表达式
//        3) 遇到操作数时，将其压入 s2
//        4) 遇到运算符时，比较其与 s1 栈顶运算符的优先级：
//          1. 如果 s1 为空，或栈顶运算符为左括号 '('，则直接将此运算符入栈
//          2. 否则，若优先级比栈顶运算符的高，也将运算符压入 s1
//          3. 否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到 (4-1) 与 s1 中新的栈顶运算符相比较
//        5) 遇到括号时：
//          1. 如果是左括号 '('，则直接压入 s1
//          2. 如果是右括号 ')'，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
//        6) 重复步骤 2 至 5，直到表达式的最右边
//        7) 将 s1 中剩余的运算符依次弹出并压入 s2
//        8) 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式(逆波兰表达式)

        String infixExpression = "2.5*2";


        List<String> stack = infixSwitchPostfix(infixExpression);

        System.out.println(calculate(stack));

    }

    public static List<String> infixSwitchPostfix(String expression) {
        Stack<String> s1 = new Stack<>();

        List<String> s2 = new ArrayList<>();

        int index = 0;
        int num;
        char c;

        while (true) {
            num = ArrayStackDemo.ArrayStack.getNum(expression, index);
            if (num > index) {
                s2.add(expression.substring(index, num));
                index = num -1 ;
            } else {
                c = expression.substring(index, index + 1).charAt(0);
                num = ArrayStackDemo.ArrayStack.priority(c);
                if (num > -1) {
                    if (s1.empty()) {
                        s1.push(String.valueOf(c));
                    } else if (num == ArrayStackDemo.ArrayStack.LEFT_PARENTHESIS) {
                        s1.push(String.valueOf(c));
                    } else if (num == ArrayStackDemo.ArrayStack.RIGHT_PARENTHESIS) {
                        while (true) {
                            String pop = s1.pop();
                            if (ArrayStackDemo.ArrayStack.priority(pop.charAt(0)) == ArrayStackDemo.ArrayStack.LEFT_PARENTHESIS) {
                                break;
                            }
                            s2.add(pop);
                        }
                    } else {
                        /*if (ArrayStackDemo.ArrayStack.priority(s1.peek().charAt(0)) == ArrayStackDemo.ArrayStack.LEFT_PARENTHESIS) {
                            s1.push(String.valueOf(c));
                        } else if (ArrayStackDemo.ArrayStack.priority(s1.peek().charAt(0)) < num) {
                            s1.push(String.valueOf(c));
                        } else {
                            */
                        while (!s1.empty() && !s1.peek().equals("(") && !(ArrayStackDemo.ArrayStack.priority(s1.peek().charAt(0)) < num)) {
                //        4) 遇到运算符时，比较其与 s1 栈顶运算符的优先级：
                //          1. 如果 s1 为空，或栈顶运算符为左括号 '('，则直接将此运算符入栈
                //          2. 否则，若优先级比栈顶运算符的高，也将运算符压入 s1
                //          3. 否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到 (4-1) 与 s1 中新的栈顶运算符相比较

                            /*if (ArrayStackDemo.ArrayStack.priority(s1.peek().charAt(0)) < num) {
                                s1.push(String.valueOf(c));
                                break;
                            }
                            String peek = s1.peek();
                            if (ArrayStackDemo.ArrayStack.priority(peek.charAt(0)) == 2) {
                                s1.push(String.valueOf(c));
                                break;
                            }
                            if (ArrayStackDemo.ArrayStack.priority(peek.charAt(0)) < num) {
                                s1.push(String.valueOf(c));
                                break;
                            }*/
                            s2.add(s1.pop());
                        }

                        s1.push(String.valueOf(c));
                    }
                } else {
                    throw new RuntimeException("表达式有误");
                }
            }

            index++;
            if (index == expression.length()) {
                break;
            }
        }

        while (!s1.empty()) {
            /*if (s1.empty()) {
                break;
            }*/
            s2.add(s1.pop());
        }

        return s2;

    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<String>();

        list.forEach(a -> {
            if (a.matches("\\d+")) {
                stack.push(a);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result;
                if ("+".equals(a)) {
                    result = num2 + num1;
                } else if ("-".equals(a)) {
                    result = num2 - num1;
                } else if ("*".equals(a)) {
                    result = num2 * num1;
                } else if ("/".equals(a)) {
                    result = num2 / num1;
                } else {
                    throw new RuntimeException("参数有误");
                }
                stack.push("" + result);
            }
        });

        return Integer.parseInt(stack.pop());
    }
}
