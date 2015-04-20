package edu.uw.aad.mzm.sample.masterdetail.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces
 */
public class Content {

    /**
     * An array of AndroidVersions.
     */
    public static List<AndroidVersion> VERSIONS = new ArrayList<AndroidVersion>();

    /**
     * A map of AndroidVersions, by ID.
     */
    public static Map<String, AndroidVersion> VERSION_MAP = new HashMap<String, AndroidVersion>();

    static {
        // Add 3 sample items.
        addItem(new AndroidVersion("1", "Cupcake", "1.5", "API 3", "April 2009", "Support for Widgets"));
        addItem(new AndroidVersion("2", "Donut", "1.6", "API 4", "September 2009", "Improvements in search"));
        addItem(new AndroidVersion("3", "Éclair", "2.0-2.1", "API 5-7", "Oct 2009 - Jan 2010", "Improvements in Google Maps"));
    }

    private static void addItem(AndroidVersion item) {
        VERSIONS.add(item);
        VERSION_MAP.put(item.id, item);
    }

    /**
     * An object representing an Android Version object
     */
    public static class AndroidVersion {
        public String id;
        public String content;
        public String codeName;
        public String versionNo;
        public String apiLevel;
        public String releaseDate;
        public String features;

        public AndroidVersion(String id, String codeName, String versionNo, String apiLevel, String releaseDate, String features) {
            this.id = id;
//            this.content = content;
            this.codeName = codeName;
            this.versionNo = versionNo;
            this.apiLevel = apiLevel;
            this.releaseDate = releaseDate;
            this.features = features;
        }

        @Override
        public String toString() {
            return codeName;
        }
    }
}
