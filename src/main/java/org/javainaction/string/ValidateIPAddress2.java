package org.javainaction.string;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Given a string IP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 *
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses but "192.168.01.1", while "192.168.1.00" and "192.168@1.1" are invalid IPv4 addresses.
 *
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 *
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits, lower-case English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
 * Leading zeros are allowed in xi.
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 *
 *
 *
 * Example 1:
 *
 * Input: IP = "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 * Example 2:
 *
 * Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 * Example 3:
 *
 * Input: IP = "256.256.256.256"
 * Output: "Neither"
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 * Example 4:
 *
 * Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334:"
 * Output: "Neither"
 * Example 5:
 *
 * Input: IP = "1e1.4.5.6"
 * Output: "Neither"
 */
public class ValidateIPAddress2 {
    public String validIPAddress(String ip) {
        var none = "Neither";
        String[] ipv4 = ip.split("\\.");
        String[] ipv6 = ip.split("\\:");

        if (ip.chars().filter(c -> c == '.').count() == 3 && ipv4.length == 4) {
            for (String input : ipv4) {
                if (!isValidIPv4(input)) return none;
            }
            return "IPv4";
        }

        if (ip.chars().filter(c -> c == ':').count() == 7 && ipv6.length == 8) {
            for (String input : ipv6) {
                if (!isValidIPv6(input)) return none;
            }
            return "IPv6";
        }

        return none;
    }

    private boolean isValidIPv4(String ipAddress) {
        try {
            Predicate<Integer> ipvFunction =
                    (ip) -> ipAddress.length() == String.valueOf(ip).length()
                    && ip >= 0 && ip <= 255;

            return ipvFunction.test(Integer.parseInt(ipAddress));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidIPv6(String ipAddress) {
        try {
            Predicate<String> ipvFunction =
                    (ip) -> ip.length() <= 4
                            && Integer.parseInt(ip,16) >=0
                            && ip.charAt(0) != '-';

            return ipvFunction.test(ipAddress);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        var obj = new ValidateIPAddress2();
        System.out.println("172.16.254.1 is valid : " + obj.validIPAddress("172.16.254.1"));

        System.out.println("2001:0db8:85a3:0:0:8A2E:0370:7334 is valid : "
                + obj.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));

        System.out.println("256.256.256.256 is valid : " + obj.validIPAddress("256.256.256.256"));

        System.out.println("2001:0db8:85a3:0:0:8A2E:0370:7334: is valid : "
                + obj.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));

        System.out.println("1e1.4.5.6 : " + obj.validIPAddress("1e1.4.5.6"));

        System.out.println("1.0.1. : " + obj.validIPAddress("1.0.1."));

    }
}
