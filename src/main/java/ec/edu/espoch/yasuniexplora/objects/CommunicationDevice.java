package ec.edu.espoch.yasuniexplora.objects;

public class CommunicationDevice {
    private String code;
    private String description;
    private boolean available;

    public CommunicationDevice(String code, String description, boolean available) {
        this.code = code;
        this.description = description;
        this.available = available;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
