package com.example.cst;

public class DFA {

    private String state;

    public DFA() {
        this.state = "q0"; // Start state
    }

    public boolean isAccepted(String input) {
        state = "q0"; // Reset to start state
        for (char c : input.toCharArray()) {
            switch (state) {
                case "q0":
                    if (c == '0') state = "q1";
                    else if (c == '1') state = "q0";
                    break;
                case "q1":
                    if (c == '0') state = "q2";
                    else if (c == '1') state = "q0";
                    break;
                case "q2":
                    if (c == '0' || c == '1') state = "q2";
                    break;
            }
        }
        return state.equals("q2");
    }
}
