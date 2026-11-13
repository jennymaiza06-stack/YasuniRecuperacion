package ec.edu.espoch.yasuniexplora.objects;

public abstract class Tour {
    private String code;
    private String name;
    private Trail[] trails;
    private int trailCount;
    private double difficultyFactor;
    private CommunicationDevice device;
    private Guide guide;

    public Tour(String code, String name) {
        this.code = code;
        this.name = name;
        this.trails = new Trail[10];
        this.trailCount = 0;
        this.difficultyFactor = 1.0;
    }

    public void addTrail(Trail trail) {
        if (trailCount < trails.length) {
            trails[trailCount++] = trail;
        }
    }

    public Trail findTrailByName(String trailName) {
        for (int i = 0; i < trailCount; i++) {
            if (trails[i].getName().equalsIgnoreCase(trailName)) {
                return trails[i];
            }
        }
        return null;
    }

    public double getTotalDistance() {
        double total = 0.0;
        for (int i = 0; i < trailCount; i++) {
            if (trails[i].isEnabled()) {
                total += trails[i].getLength();
            }
        }
        return total;
    }

    public double getBaseWalkingTime() {
        double total = 0.0;
        for (int i = 0; i < trailCount; i++) {
            if (trails[i].isEnabled()) {
                total += trails[i].getEstimatedMinutes();
            }
        }
        return total;
    }

    public double getWalkingTime() {
        return getBaseWalkingTime() * difficultyFactor;
    }

    public abstract double getTotalDuration();
    public abstract String getType();

    public boolean isAssigned() {
        return device != null && guide != null;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Trail[] getTrails() {
        Trail[] active = new Trail[trailCount];
        System.arraycopy(trails, 0, active, 0, trailCount);
        return active;
    }
    public void setTrails(Trail[] trails) {
        this.trails = trails;
        this.trailCount = (trails != null) ? trails.length : 0;
    }

    public double getDifficultyFactor() { return difficultyFactor; }
    public void setDifficultyFactor(double difficultyFactor) { this.difficultyFactor = difficultyFactor; }

    public CommunicationDevice getDevice() { return device; }
    public void setDevice(CommunicationDevice device) { this.device = device; }

    public Guide getGuide() { return guide; }
    public void setGuide(Guide guide) { this.guide = guide; }
}
