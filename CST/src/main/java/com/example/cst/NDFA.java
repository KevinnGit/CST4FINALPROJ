package com.example.cst;

import java.util.Random;

public class NDFA {

    private String state;
    private Random random;

    public NDFA() {
        this.state = "q0"; // Start state
        this.random = new Random();
    }

    public boolean isAccepted(String input) {
        state = "q0"; // Reset to start state
        for (char c : input.toCharArray()) {
            switch (state) {
                case "q0":
                    if (c == '1') {
                        // Nondeterministic transition from q0 with input '1' can lead to either q1 or q3
                        state = random.nextBoolean() ? "q1" : "q3";
                    }
                    break;
                case "q1":
                    // No transition from q1, so we stay here if reached
                    break;
                case "q2":
                    if (c == '0') {
                        state = "q3";
                    } else if (c == '1') {
                        state = "q2";
                    }
                    break;
                case "q3":
                    if (c == '0') {
                        state = "q1";
                    } else if (c == '1') {
                        state = "q3";
                    }
                    break;
                default:
                    break;
            }
        }
        // Return true if the final state is an accepting state
        return state.equals("q1");
    }
}
