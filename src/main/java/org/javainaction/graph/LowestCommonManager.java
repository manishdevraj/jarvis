package org.javainaction.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * You have given an org chat object with all its directs only pointing to their directs.
 * First input is top manager who does not have any manager and two directs A, B
 * You need to find the lowest common manager of two
 *
 *              A
 *           B      C
 *        D   E   F   G
 *     H    I
 *
 *  When you are given top manager = A and two directs as (E, I) the lowest common manager of two would be B
 *
 *
 * @see org.javainaction.bt.dfs.LowestCommonAncestor
 */
public class LowestCommonManager {
    // O(n) time | O(d) space where d is depth of org chart
    public static OrgChart getLowestCommonManager(
            OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
        return	getOrgInfo(topManager, reportOne, reportTwo).lowestCommonManager;
    }


    public static OrgInfo getOrgInfo(OrgChart manager,
                                     OrgChart reportOne,
                                     OrgChart reportTwo){
        int numReports = 0;
        for (OrgChart directReport : manager.directReports) {
            OrgInfo orgInfo = getOrgInfo(directReport, reportOne, reportTwo);
            if (orgInfo.lowestCommonManager != null) return orgInfo;
            numReports += orgInfo.numReports;
        }

        if (manager == reportOne || manager == reportTwo) numReports++;
        OrgChart lowestCommonManager = numReports == 2 ? manager : null;
        OrgInfo newOrgInfo = new OrgInfo(lowestCommonManager, numReports);
        return newOrgInfo;
    }

    static class OrgInfo {
        public OrgChart lowestCommonManager;
        int numReports;

        OrgInfo(OrgChart lowestCommonManager, int numReports){
            this.lowestCommonManager = lowestCommonManager;
            this.numReports = numReports;
        }

    }

    static class OrgChart {
        public char name;
        public List<OrgChart> directReports;

        OrgChart(char name) {
            this.name = name;
            this.directReports = new ArrayList<OrgChart>();
        }

        // This method is for testing only.
        public void addDirectReports(OrgChart[] directReports) {
            this.directReports.addAll(Arrays.asList(directReports));
        }

        @Override
        public String toString() {
            return "OrgChart{" +
                    "name=" + name +
                    '}';
        }
    }

    public static HashMap<Character, OrgChart> getOrgCharts() {
        var orgCharts = new HashMap<Character, OrgChart>();
        var alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char a : alphabet.toCharArray()) {
            orgCharts.put(a, new OrgChart(a));
        }
        orgCharts.get('X').addDirectReports(new OrgChart[] {orgCharts.get('Z')});
        return orgCharts;
    }

    public static void main(String[] args) {
        var orgCharts = getOrgCharts();
        orgCharts
                .get('A')
                .addDirectReports(new OrgChart[] {orgCharts.get('B'), orgCharts.get('C')});
        orgCharts
                .get('B')
                .addDirectReports(new OrgChart[] {orgCharts.get('D'), orgCharts.get('E')});
        orgCharts
                .get('C')
                .addDirectReports(new OrgChart[] {orgCharts.get('F'), orgCharts.get('G')});
        orgCharts
                .get('D')
                .addDirectReports(new OrgChart[] {orgCharts.get('H'), orgCharts.get('I')});

        OrgChart lcm =
                getLowestCommonManager(orgCharts.get('A'), orgCharts.get('E'), orgCharts.get('I'));
        System.out.println("Lowest common manager is " + lcm);
    }
}
