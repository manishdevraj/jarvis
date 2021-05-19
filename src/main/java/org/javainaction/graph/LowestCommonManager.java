package org.javainaction.graph;

import java.util.ArrayList;
import java.util.List;

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

    static class OrgChart {
        public char name;
        public List<OrgChart> directReports;

        OrgChart(char name) {
            this.name = name;
            this.directReports = new ArrayList<OrgChart>();
        }

        // This method is for testing only.
        public void addDirectReports(OrgChart[] directReports) {
            for (OrgChart directReport : directReports) {
                this.directReports.add(directReport);
            }
        }
    }

    static class OrgInfo {
        public OrgChart lowestCommonManager;
        int numReports;

        OrgInfo(OrgChart lowestCommonManager, int numReports){
            this.lowestCommonManager = lowestCommonManager;
            this.numReports = numReports;
        }

    }
}
