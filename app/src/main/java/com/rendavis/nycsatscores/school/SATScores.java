package com.rendavis.nycsatscores.school;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final SATScores satScores = (SATScores) o;
        return numSATTestTakers == satScores.numSATTestTakers &&
                averageSATCriticalReadingScore == satScores.averageSATCriticalReadingScore &&
                averageSATMathScore == satScores.averageSATMathScore &&
                averageSATWritingScore == satScores.averageSATWritingScore &&
                Objects.equals(id, satScores.id) && Objects.equals(name, satScores.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numSATTestTakers, averageSATCriticalReadingScore,
                averageSATMathScore, averageSATWritingScore);
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
