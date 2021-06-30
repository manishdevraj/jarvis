package org.javainaction.string;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros.
 * For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses but "192.168.01.1", while "192.168.1.00"
 * and "192.168@1.1" are invalid IPv4 addresses.
 *
 * Input "1921680
 *
 * Valid IP address combinations
 *   "1.9.216.80"
 *   "1.92.16.80"
 *   "1.92.168.0"
 *   "19.2.16.80"
 *   "19.2.168.0"
 *   "19.21.6.80"
 *   "19.21.68.0"
 *   "19.216.8.0"
 *   "192.1.6.80"
 *   "192.1.68.0"
 *   "192.16.8.0"
 */
public class ValidateIPAddress {
    //O(n) time | O(1) space
    public ArrayList<String> validIPAddresses(String string) {
        var ipAddresses = new ArrayList<String>();
        Predicate<String> isValidIP =
                (ip) -> Integer.parseInt(ip) <= 255 && ip.length() == Integer.toString(Integer.parseInt(ip)).length();

        for (int i = 1; i < Math.min(string.length(), 4); i++) {
            String[] currentIPAddresses = new String[] {"", "", "", ""};

            currentIPAddresses[0] = string.substring(0, i);
            if (isValidIP.negate().test(currentIPAddresses[0])) {
                continue;
            }

            for (int j = i + 1; j < i + Math.min(string.length() - i, 4); j++) {
                currentIPAddresses[1] = string.substring(i, j);
                if (isValidIP.negate().test(currentIPAddresses[1])) {
                    continue;
                }

                for (int k = j + 1; k < j + Math.min(string.length() - j, 4); k++) {
                    currentIPAddresses[2] = string.substring(j, k);
                    currentIPAddresses[3] = string.substring(k);

                    if (isValidIP.test(currentIPAddresses[2])
                            && isValidIP.test(currentIPAddresses[3])) {
                        ipAddresses.add(join(currentIPAddresses));
                    }
                }
            }

        }
        return ipAddresses;
    }

    public String join(String[] input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            output.append(input[i]).append(i < input.length - 1 ? "." : "");
        }
        return output.toString();
    }

    public static void main(String[] args) {
        String input = "1921680";
        var expected = new ArrayList<String>();
        expected.add("1.9.216.80");
        expected.add("1.92.16.80");
        expected.add("1.92.168.0");
        expected.add("19.2.16.80");
        expected.add("19.2.168.0");
        expected.add("19.21.6.80");
        expected.add("19.21.68.0");
        expected.add("19.216.8.0");
        expected.add("192.1.6.80");
        expected.add("192.1.68.0");
        expected.add("192.16.8.0");
        var actual = new ValidateIPAddress().validIPAddresses(input);
        System.out.println(actual);
    }
}
