package org.javainaction.dp;

/**
 * Suppose we abstract our file system by a string in the following manner:
 *
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 *
 * dir
 *     subdir1
 *     subdir2
 *         file.ext
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 *
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 *
 * dir
 *     subdir1
 *         file1.ext
 *         subsubdir1
 *     subdir2
 *         subsubdir2
 *             file2.ext
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and
 * an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2
 * containing a file file2.ext.
 *
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system.
 * For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext",
 * and its length is 32 (not including the double quotes).
 *
 * Given a string representing the file system in the above format, return the length of the longest absolute path to
 * file in the abstracted file system. If there is no file in the system, return 0.
 */
public class LongestAbsoluteFilePath {
    public static int lengthLongestPath(String input) {
        //split files by new line, this will give us all possible depths in our path
        String[] files = input.split("\n");
        int[] stack = new int[files.length + 1];
        int maxLength = 0;
        stack[0] = 0;
        for (String s : files) {
            //key is to find last index of tab such that we know we are at some sub structure and
            //also helps us find at what level we are, single tab vs double tab will given two different levels
            //even when are as passed on sub tree structure, the next sibling structure will not change level
            //dir \t sub dir1 \t\t file 1 \t sub dir2 \t\t\ file 2
            // at sub dir 1 level will be 1
            // at file 1 level will be 2, but
            // at sub dir 2 level will be still 1
            // at file 2 level will be 2
            int level = s.lastIndexOf("\t") + 1;
            //to find current length, get last length and add current string length minus level
            int curLength = stack[level] + s.length() - level + 1;
            //add that for next time
            stack[level + 1] = curLength;
            //if path ended with . that means we are at file so get that to calculation max current length
            if (s.contains(".")) {
                maxLength = Math.max(maxLength, curLength - 1);
            }
        }
        return maxLength;
    }

    public static void main(String[] arg) {
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(lengthLongestPath(input));
    }
}

