package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
    private String number = "0";  // string containing the binary value '0' or '1'

    /**
     * A constructor that generates a binary object.
     *
     * @param number a String of the binary values. It should contain only zeros or ones with any length and order.
     *               otherwise, the value of "0" will be stored. Trailing zeros will be excluded and empty string will be considered as zero.
     */
    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0"; // Default to "0" for null or empty input
            return;
        }

        // Validate the binary string (only '0' or '1' allowed)
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0"; // Default to "0" for invalid input
                return;
            }
        }

        // Remove leading zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }

        // If all digits are '0', ensure number is "0"
        this.number = (beg == number.length()) ? "0" : number.substring(beg);

        // replace empty strings with a single zero (safety)
        if (this.number.isEmpty()) {
            this.number = "0";
        }
    }

    /**
     * Return the binary value of the variable
     *
     * @return the binary value in a string format.
     */
    public String getValue()
    {
        return this.number;
    }

    /**
     * Adding two binary variables.
     *
     * @param num1 The first addend object
     * @param num2 The second addend object
     * @return A binary variable with a value of num1+num2.
     */
    public static Binary add(Binary num1, Binary num2)
    {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;

        int carry = 0;
        String num3 = "";

        while(ind1 >= 0 || ind2 >= 0 || carry != 0)
        {
            int sum = carry;

            if(ind1 >= 0){
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
                ind1--;
            }
            if(ind2 >= 0){
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
                ind2--;
            }

            carry = sum / 2;
            sum = sum % 2;

            num3 = ((sum == 0) ? "0" : "1") + num3;
        }

        return new Binary(num3);
    }

    //  CODE BELOW 

    // Helper: pad with leading zeros to a certain length
    private static String padLeftZeros(String s, int length) {
        String out = s;
        while (out.length() < length) {
            out = "0" + out;
        }
        return out;
    }

    /**
     * Bitwise OR of two binary variables.
     *
     * @param num1 first binary number
     * @param num2 second binary number
     * @return Binary result of num1 OR num2
     */
    public static Binary OR(Binary num1, Binary num2) {
        String a = num1.number;
        String b = num2.number;

        int n = Math.max(a.length(), b.length());
        a = padLeftZeros(a, n);
        b = padLeftZeros(b, n);

        String result = "";
        for (int i = 0; i < n; i++) {
            char r = (a.charAt(i) == '1' || b.charAt(i) == '1') ? '1' : '0';
            result += r;
        }

        return new Binary(result);
    }

    /**
     * Bitwise AND of two binary variables.
     *
     * @param num1 first binary number
     * @param num2 second binary number
     * @return Binary result of num1 AND num2
     */
    public static Binary AND(Binary num1, Binary num2) {
        String a = num1.number;
        String b = num2.number;

        int n = Math.max(a.length(), b.length());
        a = padLeftZeros(a, n);
        b = padLeftZeros(b, n);

        String result = "";
        for (int i = 0; i < n; i++) {
            char r = (a.charAt(i) == '1' && b.charAt(i) == '1') ? '1' : '0';
            result += r;
        }

        return new Binary(result);
    }

    /**
     * Multiply two binary variables.
     *
     * @param num1 first binary number
     * @param num2 second binary number
     * @return Binary result of num1 * num2
     */
    public static Binary multiply(Binary num1, Binary num2) {
        int a = Integer.parseInt(num1.number, 2);
        int b = Integer.parseInt(num2.number, 2);
        int product = a * b;
        return new Binary(Integer.toBinaryString(product));
    }
}
