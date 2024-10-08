package com.example.cst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PDA {

    public List<String> isAccepted(String input, int n) {
        List<String> transitions = new ArrayList<>(); // To hold transition steps
        if (input.length() != 2 * n) {
            transitions.add("Input length mismatch. Expected: " + (2 * n) + ", Got: " + input.length());
            return transitions; // Return early if length is incorrect
        }

        Stack<Character> stack = new Stack<>();
        int countA = 0;

        for (char ch : input.toCharArray()) {
            if (ch == 'a') {
                stack.push('X');
                countA++;
                transitions.add("Transition: Read 'a', push 'X'. Stack: " + stack);
            } else if (ch == 'b') {
                if (stack.isEmpty()) {
                    transitions.add("Transition: Read 'b', but stack is empty. Rejected.");
                    return transitions; // Reject if there's an attempt to pop from an empty stack
                }
                stack.pop();
                transitions.add("Transition: Read 'b', pop 'X'. Stack: " + stack);
            } else {
                transitions.add("Transition: Invalid character '" + ch + "'. Rejected.");
                return transitions; // Reject if an invalid character is encountered
            }
        }

        // Check final acceptance criteria
        if (countA == n && stack.isEmpty()) {
            transitions.add("Input accepted.");
        } else {
            transitions.add("Input rejected. Count of 'a': " + countA + ", Stack not empty: " + !stack.isEmpty());
        }

        return transitions;
    }
}
