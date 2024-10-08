package com.example.cst;

public class CFG {
    private StringBuilder derivationSteps;

    public CFG() {
        this.derivationSteps = new StringBuilder();
    }

    public boolean isAccepted(String input) {
        derivationSteps.setLength(0); // Reset derivation steps for each check
        boolean accepted = deriveString(input); // Start derivation with input

        // If not accepted, append a message
        if (!accepted) {
            derivationSteps.append("No valid derivation for input: ").append(input).append("\n");
        }
        return accepted;
    }

    public boolean deriveString(String str) {
        derivationSteps.append("Derivation steps:\n");
        String current = "S";
        derivationSteps.append("S -> ").append(current).append("\n");
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (left == right) {
                if (str.charAt(left) == 'c') {
                    current = current.replaceFirst("S", "c");
                    derivationSteps.append("S -> ").append(current).append("\n");
                    return true;
                } else {
                    return false;
                }
            }
            if (str.charAt(left) == 'a' && str.charAt(right) == 'a') {
                current = current.replaceFirst("S", "aSa");
                derivationSteps.append("S -> ").append(current).append("\n");
                left++;
                right--;
            } else if (str.charAt(left) == 'b' && str.charAt(right) == 'b') {
                current = current.replaceFirst("S", "bSb");
                derivationSteps.append("S -> ").append(current).append("\n");
                left++;
                right--;
            } else {
                derivationSteps.append("Derivation failed. Expected matching characters at both ends.\n");
                return false;
            }
        }
        return current.equals(str);
    }

    public String getDerivationSteps() {
        return derivationSteps.toString(); // Return recorded derivation steps
    }
}