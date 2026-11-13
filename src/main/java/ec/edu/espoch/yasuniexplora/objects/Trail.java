package ec.edu.espoch.yasuniexplora.objects;

public class Trail {
    private String name;
    private double length;
    private int estimatedMinutes;
    private boolean enabled;

    public Trail(String name, double length, int estimatedMinutes, boolean enabled) {
        this.name = name;
        this.length = length;
        this.estimatedMinutes = estimatedMinutes;
        this.enabled = enabled;
    }

    public void print() {
        System.out.println("Trail: " + name + " | Length: " + length + " km | Minutes: " + estimatedMinutes + " | Enabled: " + enabled);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }

    public int getEstimatedMinutes() { return estimatedMinutes; }
    public void setEstimatedMinutes(int estimatedMinutes) { this.estimatedMinutes = estimatedMinutes; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
