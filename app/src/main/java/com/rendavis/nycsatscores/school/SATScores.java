package com.rendavis.nycsatscores.school;

import org.apache.commons.lang3.math.NumberUtils;

public class SATScores {
    private String id;
    private String name;
    private int numSATTestTakers;
    private int averageSATCriticalReadingScore;
    private int averageSATMathScore;
    private int averageSATWritingScore;

    private SATScores() {}
    public SATScores(
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

    public static SATScores from(final SchoolSATDTO dto) {
        final SATScores satScores = new SATScores();

        satScores.id = dto.id;
        satScores.name = dto.name;
        satScores.numSATTestTakers = NumberUtils.toInt(dto.numSATTestTakers, 0);
        satScores.averageSATCriticalReadingScore =
                NumberUtils.toInt(dto.averageSATCriticalReadingScore, 0);
        satScores.averageSATMathScore = NumberUtils.toInt(dto.averageSATMathScore, 0);
        satScores.averageSATWritingScore =
                NumberUtils.toInt(dto.averageSATWritingScore, 0);

        return satScores;
    }
}
