package com.example.cst;

import java.util.Arrays;

enum State {
    Q0, Q1, Q2, Q3, Q4, Q5 // Add more states if necessary
}

public class TM {

    private char[] tape; // The tape of the Turing Machine
    private int head; // The current head position
    private State currentState; // The current state of the Turing Machine
    private StringBuilder steps; // To log the steps for display

    // Constructor
    public TM(String input) {
        int bufferSize = 1; // Buffer size to prevent out-of-bounds errors
        this.tape = new char[input.length() + bufferSize * 2]; // Create the tape

        Arrays.fill(this.tape, '#'); // Fill the tape with '#'

        for (int i = 0; i < input.length(); i++) {
            this.tape[i + bufferSize] = input.charAt(i); // Fill tape with input
        }

        // Set head position to the rightmost non-# character
        this.head = bufferSize + input.length() - 1; // Start at the end of the input
        this.currentState = State.Q0; // Initial state
        this.steps = new StringBuilder(); // Initialize steps
    }

    // Method to simulate the Turing machine
    public void run() {
        int stepCount = 0; // To track the steps

        // Log the initial tape state
        logStep(stepCount);

        while (currentState != State.Q5) {
            // Check boundaries
            if (head < 0 || head >= tape.length) {
                steps.append("Head moved out of bounds. Halting.\n");
                break;
            }

            char currentSymbol = tape[head]; // Get current symbol

            switch (currentState) {
                case Q0:
                    if (currentSymbol == '1') {
                        tape[head] = '0'; // Change 1 to 0
                        head--; // Move left
                        currentState = State.Q1;
                    } else if (currentSymbol == '0') {
                        tape[head] = '1'; // Change 0 to 1
                        head--; // Move left
                        currentState = State.Q0;
                    } else if (currentSymbol == '+') {
                        tape[head] = '#'; // Replace '+' with '#'
                        head++; // Move right
                        currentState = State.Q4;
                    } else if (currentSymbol == '#') {
                        head--; // Move left
                        currentState = State.Q0;
                    }
                    break;

                case Q1:
                    if (currentSymbol == '1' || currentSymbol == '0' || currentSymbol == '#') {
                        head--; // Move left
                        currentState = State.Q1;
                    } else if (currentSymbol == '+') {
                        tape[head] = '+'; // Keep '+' as is
                        head--; // Move left
                        currentState = State.Q2;
                    }
                    break;

                case Q2:
                    if (currentSymbol == '0') {
                        tape[head] = '1'; // Change 0 to 1
                        head++; // Move right
                        currentState = State.Q3;
                    } else if (currentSymbol == '1') {
                        tape[head] = '0'; // Change 1 to 0
                        head--; // Move left
                        currentState = State.Q2;
                    } else if (currentSymbol == '#') {
                        tape[head] = '1'; // Change # to 1
                        head++; // Move right
                        currentState = State.Q3;
                    } else if (currentSymbol == '+') {
                        head--; // Move left
                        currentState = State.Q2;
                    }
                    break;

                case Q3:
                    if (currentSymbol == '1' || currentSymbol == '0' || currentSymbol == '+') {
                        head++; // Move right
                        currentState = State.Q3;
                    } else if (currentSymbol == '#') {
                        tape[head] = '#'; // Keep #
                        head--; // Move left
                        currentState = State.Q0;
                    }
                    break;

                case Q4:
                    if (currentSymbol == '1') {
                        tape[head] = '#'; // Change 1 to #
                        head++; // Move right
                        currentState = State.Q4; // Continue in halt state
                    } else if (currentSymbol == '#') {
                        tape[head] = '#'; // Keep #
                        currentState = State.Q5; // Move to halt state
                    }
                    break;

                default:
                    currentState = State.Q5; // Move to halt state
                    break;
            }

            // Log the current step after each transition
            logStep(++stepCount);
        }

        // Final step and tape
        steps.append("Final Step " + (++stepCount) + ":\n");
        steps.append("Current State: " + currentState.toString().toLowerCase() + ", Head Position: " + head + ", Current Symbol: " + tape[head] + "\n");
        steps.append("Tape: ").append(new String(tape)).append("\n");

        // Extract the result from the tape
        StringBuilder resultBuilder = new StringBuilder();
        for (char c : tape) {
            if (c == '1' || c == '0') {
                resultBuilder.append(c); // Append only binary digits
            }
        }

        // Append the final answer
        steps.append("Answer: ").append(resultBuilder.toString()).append("\n");
    }

    // Log the step details
    private void logStep(int stepCount) {
        steps.append("Step " + stepCount + ":\n");
        steps.append("Current State: " + currentState.toString().toLowerCase() + ", Head Position: " + head + ", Current Symbol: " + tape[head] + "\n");
        steps.append("Tape: ").append(new String(tape)).append("\n\n"); // Added space for clarity
    }

    public String getSteps() {
        return steps.toString(); // Return logged steps
    }

    public String getFinalResult() {
        StringBuilder resultBuilder = new StringBuilder();
        for (char c : tape) {
            if (c == '1' || c == '0') {
                resultBuilder.append(c); // Append only binary digits
            }
        }
        return resultBuilder.toString();
    }

    public boolean isAccepted() {
        // Logic to determine if the Turing Machine accepted the input
        // Assuming that the Turing Machine accepts if it reaches the halt state without errors
        return currentState == State.Q5 && tape[head] == '#'; // Example condition for acceptance
    }
}
