package edu.uw.aad.mzm.sample.database.model;

/**
 * Created by Margaret on 2/19/2015.
 *
 * A custom object for the Android versions
 */
public class AndroidVersion {

    private String codeName;
    private String versionNo;
    private String apiLevel;
    private String releaseDate;
    private String features;

    public AndroidVersion(String codeName, String versionNo, String apiLevel, String releaseDate, String features) {
        this.codeName = codeName;
        this.versionNo = versionNo;
        this.apiLevel = apiLevel;
        this.releaseDate = releaseDate;
        this.features = features;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public String getApiLevel() {
        return apiLevel;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "Code Name: " + codeName +
                " Version No: " + versionNo +
                " API Level: " + apiLevel +
                " Release data: " + releaseDate +
                " Features: " + features;
    }
}
