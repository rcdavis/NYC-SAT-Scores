package com.rendavis.nycsatscores.school;

import com.google.gson.annotations.SerializedName;

public class SchoolSATDTO {
    @SerializedName("dbn")
    public String id;

    @SerializedName("school_name")
    public String name;

    @SerializedName("num_of_sat_test_takers")
    public String numSATTestTakers;

    @SerializedName("sat_critical_reading_avg_score")
    public String averageSATCriticalReadingScore;

    @SerializedName("sat_math_avg_score")
    public String averageSATMathScore;

    @SerializedName("sat_writing_avg_score")
    public String averageSATWritingScore;
}
