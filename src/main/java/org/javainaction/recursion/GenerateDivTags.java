package org.javainaction.recursion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a numberOfTags return a list of all the valid strings that you can generate with matching
 * <quote>
 *     <div></div>
 * </quote>
 *
 * numberOfTags  = 2
 *
 * Out put would be
 * <quote>
 *     <div></div><div></div>
 *     <div><div></div></div>
 * </quote>
 */
public class GenerateDivTags {
    public ArrayList<String> generateDivTags(int numberOfTags) {
        ArrayList<String> result = new ArrayList<>();
        generateDivTagsRecursive(numberOfTags, numberOfTags,"", result);
        return result;
    }

    public void generateDivTagsRecursive(int openDiv,
                                         int closeDiv,
                                         String divBuilder,
                                         ArrayList<String> result) {

        if (openDiv > 0) {
            String newBuilder = divBuilder + "<div>";
            generateDivTagsRecursive(openDiv - 1, closeDiv,
                    newBuilder, result);
        }

        if (openDiv < closeDiv) {
            String newBuilder = divBuilder + "</div>";
            generateDivTagsRecursive(openDiv, closeDiv - 1,
                    newBuilder, result);
        }

        if (closeDiv == 0) {
            result.add(divBuilder);
        }
    }

    public static void main(String[] args) {
        int input = 3;
        ArrayList<String> expected =
                new ArrayList<String>(
                        Arrays.asList(
                                "<div><div><div></div></div></div>",
                                "<div><div></div><div></div></div>",
                                "<div><div></div></div><div></div>",
                                "<div></div><div><div></div></div>",
                                "<div></div><div></div><div></div>"));
        var actual = new GenerateDivTags().generateDivTags(input);
        System.out.println("Valid div strings with 3 : ");
        actual.forEach(System.out::println);
    }
}
