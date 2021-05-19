package org.javainaction.twopointers;

/**
 * Comparing Strings containing Backspaces (medium) #
 * Given two strings containing backspaces (identified by the character ‘#’), check if the two strings are equal.
 *
 * Example 1:
 *
 * Input: str1="xy#z", str2="xzz#"
 * Output: true
 * Explanation: After applying backspaces the strings become "xz" and "xz" respectively.
 * Example 2:
 *
 * Input: str1="xy#z", str2="xyz#"
 * Output: false
 * Explanation: After applying backspaces the strings become "xz" and "xy" respectively.
 * Example 3:
 *
 * Input: str1="xp#", str2="xyz##"
 * Output: true
 * Explanation: After applying backspaces the strings become "x" and "x" respectively.
 * In "xyz##", the first '#' removes the character 'z' and the second '#' removes the character 'y'.
 * Example 4:
 *
 * Input: str1="xywrrmp", str2="xywrrmu#p"
 * Output: true
 * Explanation: After applying backspaces the strings become "xywrrmp" and "xywrrmp" respectively.
 */
public class BackspaceCompare {
    public static boolean compare(String str1, String str2) {
        int i = str1.length() - 1;
        int j = str2.length() - 1;

        while (i >= 0 || j >= 0) {
            int indexOne = getValidIndex(str1, i);
            int indexTwo = getValidIndex(str2, j);

            if (indexOne < 0 && indexTwo < 0) return true;
            if (indexOne < 0 || indexTwo < 0) return false;
            if (str1.charAt(indexOne) != str2.charAt(indexTwo)) return false;
            i = indexOne - 1;
            j = indexTwo - 1;
        }
        return true;
    }

    public static int getValidIndex(String str, int index){
        int backspaceCount = 0;
        while (index >= 0) {
            if (str.charAt(index) == '#') {
                backspaceCount++;
            } else if (backspaceCount > 0) {
                backspaceCount--;
            } else {
                break;
            }
            index--;
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(BackspaceCompare.compare("xy#z", "xzz#"));
        System.out.println(BackspaceCompare.compare("xy#z", "xyz#"));
        System.out.println(BackspaceCompare.compare("xp#", "xyz##"));
        System.out.println(BackspaceCompare.compare("xywrrmp", "xywrrmu#p"));
    }
}
