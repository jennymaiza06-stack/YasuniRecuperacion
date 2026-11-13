package ec.edu.espoch.yasuniexplora.objects;

public class RegularTour extends Tour {

    public RegularTour(String code, String name) {
        super(code, name);
    }

    @Override
    public double getTotalDuration() {
        return getWalkingTime();
    }

    @Override
    public String getType() {
        return "Regular";
    }
}
