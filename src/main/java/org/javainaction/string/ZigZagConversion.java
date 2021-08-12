package org.javainaction.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 *
 * Input: s = "A", numRows = 1
 * Output: "A"
 */
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> builderList = new ArrayList<>();

        int count = 0;
        while (count != numRows) {
            builderList.add(new StringBuilder());
            count++;
        }

        int row = 0;
        boolean isGoingDown = false;
        for (char c : s.toCharArray()) {
            builderList.get(row).append(c);
            if (row == 0 || row == numRows - 1) isGoingDown = !isGoingDown;
            row += isGoingDown ? 1 : -1;
        }
        final StringBuilder output = new StringBuilder();
        builderList.forEach(output::append);
        return output.toString();
    }

    public static void main(String[] args) {
        var obj = new ZigZagConversion();
        System.out.println(obj.convert("PAYPALISHIRING", 4)); //PINALSIGYAHRPI
    }
}
