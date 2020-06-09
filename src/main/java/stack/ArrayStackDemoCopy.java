package stack;

import java.util.Scanner;

/**
 * @author: liming
 * @Date: 2020/1/17 15:04
 * @Description: 数组模拟栈
 */

public class ArrayStackDemoCopy {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayStack numStack = new ArrayStack(10);

        ArrayStack operStack = new ArrayStack(10);

        String s = "(22.22+11.11) * 2";
        int index = 0;
        int num = ArrayStack.getNum(s, index);


        /*while (true) {
            System.out.print("请输入需要计算的表达式: ");
            String expression = sc.next();

            int index = 0;
            int num1;
            int num2;
            char c;
            int result;

            while (true) {
                num1 = ArrayStack.getNum(expression, index);
                // 判断是不是数字，如果是数字，直接入数栈
                if (num1 > index) {
                    numStack.push(Integer.parseInt(expression.substring(index, num1)));
                    index = num1 - 1;
                } else {
                    c = expression.substring(index, index + 1).charAt(0);
                    // 判断是否是操作符
                    if (ArrayStack.priority(c) > -1) {
                        // 判断操作符栈是否为空，如果为空，直接入操作符栈
                        if (operStack.isEmpty()) {
                            operStack.push(c);
                        } else if (ArrayStack.priority(c) == ArrayStack.LEFT_PARENTHESIS) {
                            operStack.push(c);
                        } else {
                            if (ArrayStack.priority(c) == ArrayStack.RIGHT_PARENTHESIS) {
                                while (true) {
                                    int pop = operStack.pop();
                                    if (ArrayStack.priority(pop) == ArrayStack.LEFT_PARENTHESIS) {
                                        break;
                                    }
                                    num1 = numStack.pop();
                                    num2 = numStack.pop();
                                    result = ArrayStack.calc(num1, num2, pop);
                                    numStack.push(result);
                                }
                            }
                            // 从操作符栈中查看最顶层操作符，与当前操作符比较优先级，
                            // 如果操作服栈中的操作符小于或等于当前操作符优先级，则从数栈中 pop 出两个数，从操作符栈中 pop 出一个操作符进行运算，
                            // 将结果 push 到 数栈中
                            else if (ArrayStack.priority(operStack.peek()) >= ArrayStack.priority(c) && ArrayStack.priority(operStack.peek()) != ArrayStack.LEFT_PARENTHESIS) {
                                num1 = numStack.pop();
                                num2 = numStack.pop();
                                result = ArrayStack.calc(num1, num2, operStack.pop());
                                numStack.push(result);
                            }

                            if (ArrayStack.priority(c) != ArrayStack.RIGHT_PARENTHESIS) {
                                operStack.push(c);
                            }
                        }
                    }
                }
                index++;
                if (index == expression.length()) {
                    break;
                }
            }

            while (true) {

                if (operStack.isEmpty()) {
                    break;
                }
                num1 = numStack.pop();
                num2 = numStack.pop();
                result = ArrayStack.calc(num1, num2, operStack.pop());
                numStack.push(result);
            }
            System.out.println("表达式: " + expression + ", 值为: " + numStack.pop());
        }*/

    }

    static class ArrayStack {

        public static final int ADD = 0;
        public static final int SUB = 0;
        public static final int MUL = 1;
        public static final int DIV = 1;
        public static final int LEFT_PARENTHESIS = 2;
        public static final int RIGHT_PARENTHESIS = 3;
        private int maxSize;

        private int[] stack;

        private int top = -1;

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[maxSize];
        }

        /**
         * 栈满
         * @return
         */
        public boolean isFull() {
            return top == maxSize - 1;
        }

        /**
         * 栈空
         * @return
         */
        public boolean isEmpty() {
            return  top == -1;
        }

        /**
         * 入栈
         * @param value
         */
        public void push (int value) {
            if (isFull()) {
                System.out.println("栈满");
                return;
            }

            top++;
            stack[top] = value;
        }

        /**
         * 出栈
         * @return
         */
        public int pop() {
            if (isEmpty()) {
                throw new NullPointerException("栈空");
            }

            int value = stack[top];
            top--;
            return value;
        }

        /**
         *  遍历
         */
        public void list() {
            for (int i = top; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        }

        /**
         *  查看栈顶数据
          * @return
         */
        public int peek() {
            if (isEmpty()) {
                throw new NullPointerException("栈空");
            }

            return stack[top];
        }

        /**
         * 判断操作符的优先级
          * @param c
         * @return
         */
        public static int priority(int c) {
            if (')' == c) {
              return RIGHT_PARENTHESIS;
            } else if ('(' == c) {
              return LEFT_PARENTHESIS;
            } else if ('*' == c || '/' == c) {
                return MUL;
            } else if ('+' == c || '-' == c) {
                return ADD;
            } else {
                return -1;
            }
        }

        /**
         * 判断是否是数字
         * @param c
         * @return
         */
        public static boolean isNum(char c) {
            return ('0' <= c && c <= '9') || c == '.';
        }

        /**
         * 根据索引判断是否是数字如果是数字，则返回 index 到数字结尾的步幅
         * @param expression 表达式
         * @param index 索引
         * @return 索引位置
         */
        public static int getNum(String expression, int index) {
            char c = expression.substring(index, index + 1).charAt(0);
            if (isNum(c)) {
                index++;
                if (index < expression.length() - 1 && isNum(expression.substring(index, index + 1).charAt(0))) {
                    return getNum(expression, index);
                }
            }
            return index;
        }

        /**
         * 计算
         * @param num1
         * @param num2
         * @param c 操作符
         * @return
         */
        public static int calc(int num1, int num2, int c) {
            int result;
            switch (c) {
                case '*' :
                    result = num1 * num2;
                    break;
                case '/' :
                    result = num2 / num1;
                    break;
                case '+' :
                    result = num1 + num2;
                    break;
                case '-' :
                    result = num2 - num1;
                    break;
                default:
                    throw new RuntimeException("操作符输入有误");
            }
            return result;
        }
    }
}
