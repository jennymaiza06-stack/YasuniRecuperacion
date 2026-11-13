package ec.edu.espoch.yasuniexplora.objects;

import ec.edu.espoch.yasuniexplora.daointerface.DeviceDAO;
import ec.edu.espoch.yasuniexplora.daointerface.GuideDAO;
import ec.edu.espoch.yasuniexplora.daointerface.TourDAO;
import java.util.Scanner;

public class TourPlanner {
    private TourDAO tourDAO;
    private DeviceDAO deviceDAO;
    private GuideDAO guideDAO;
    private OrderCriterion criterion;
    private Scanner keyboard;

    public TourPlanner(TourDAO tourDAO, DeviceDAO deviceDAO, GuideDAO guideDAO) {
        this.tourDAO = tourDAO;
        this.deviceDAO = deviceDAO;
        this.guideDAO = guideDAO;
        this.criterion = OrderCriterion.DISTANCE;
        this.keyboard = new Scanner(System.in);
    }

    public OrderCriterion getCriterion() {
        return criterion;
    }

    public void setCriterion(OrderCriterion criterion) {
        this.criterion = criterion;
    }

    public Tour[] orderTours() {
        Tour[] tours = tourDAO.findAll();
        for (int i = 0; i < tours.length - 1; i++) {
            for (int j = 0; j < tours.length - i - 1; j++) {
                boolean swap = false;
                if (criterion == OrderCriterion.DISTANCE) {
                    swap = tours[j].getTotalDistance() > tours[j + 1].getTotalDistance();
                } else if (criterion == OrderCriterion.DURATION) {
                    swap = tours[j].getTotalDuration() > tours[j + 1].getTotalDuration();
                }
                if (swap) {
                    Tour temp = tours[j];
                    tours[j] = tours[j + 1];
                    tours[j + 1] = temp;
                }
            }
        }
        return tours;
    }

    public boolean assignResources(String tourCode, String deviceCode, String guideId) {
        Tour tour = tourDAO.findByCode(tourCode);
        CommunicationDevice device = deviceDAO.findByCode(deviceCode);
        Guide guide = guideDAO.findByNationalId(guideId);

        if (tour == null || device == null || guide == null) {
            return false;
        }
        if (tour.isAssigned() || !device.isAvailable() || !guide.isAvailable()) {
            return false;
        }

        tour.setDevice(device);
        tour.setGuide(guide);
        device.setAvailable(false);
        guide.setAvailable(false);
        return true;
    }

    public double simulateDifficulty(double currentFactor) {
        System.out.print("Ingrese el porcentaje de incremento de dificultad (ej. 15 para 15%): ");
        double percentage = keyboard.nextDouble();
        return currentFactor * (1.0 + (percentage / 100.0));
    }

    public boolean closeTrail(Tour tour, String trailName) {
        if (tour == null) return false;
        Trail trail = tour.findTrailByName(trailName);
        if (trail != null && trail.isEnabled()) {
            trail.setEnabled(false);
            return true;
        }
        return false;
    }
}
