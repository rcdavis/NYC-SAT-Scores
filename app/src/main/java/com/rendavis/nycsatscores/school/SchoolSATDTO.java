package com.rendavis.nycsatscores.school;

import com.google.gson.annotations.SerializedName;

public class SchoolSATDTO {
    @SerializedName("dbn")
    private final String id;

    @SerializedName("school_name")
    private final String name;

    @SerializedName("num_of_sat_test_takers")
    private final int numSATTestTakers;

    @SerializedName("sat_critical_reading_avg_score")
    private final int averageSATCriticalReadingScore;

    @SerializedName("sat_math_avg_score")
    private final int averageSATMathScore;

    @SerializedName("sat_writing_avg_score")
    private final int averageSATWritingScore;

    public SchoolSATDTO(
        String id, String name, int numSATTestTakers, int averageSATCriticalReadingScore,
        int averageSATMathScore, int averageSATWritingScore
    ) {
        this.id = id;
        this.name = name;
        this.numSATTestTakers = numSATTestTakers;
        this.averageSATCriticalReadingScore = averageSATCriticalReadingScore;
        this.averageSATMathScore = averageSATMathScore;
        this.averageSATWritingScore = averageSATWritingScore;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumSATTestTakers() {
        return numSATTestTakers;
    }

    public int getAverageSATCriticalReadingScore() {
        return averageSATCriticalReadingScore;
    }

    public int getAverageSATMathScore() {
        return averageSATMathScore;
    }

    public int getAverageSATWritingScore() {
        return averageSATWritingScore;
    }
}
