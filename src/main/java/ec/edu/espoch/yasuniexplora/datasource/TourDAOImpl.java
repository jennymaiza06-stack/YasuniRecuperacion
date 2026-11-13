package ec.edu.espoch.yasuniexplora.datasource;

import ec.edu.espoch.yasuniexplora.daointerface.TourDAO;
import ec.edu.espoch.yasuniexplora.objects.Tour;

public class TourDAOImpl implements TourDAO {
    private Tour[] tours;
    private int count;

    public TourDAOImpl() {
        this.tours = new Tour[20];
        this.count = 0;
    }

    public TourDAOImpl(int capacity) {
        this.tours = new Tour[capacity];
        this.count = 0;
    }

    @Override
    public boolean save(Tour tour) {
        if (tour == null || count >= tours.length || findByCode(tour.getCode()) != null) {
            return false;
        }
        tours[count++] = tour;
        return true;
    }

    @Override
    public Tour findByCode(String code) {
        for (int i = 0; i < count; i++) {
            if (tours[i].getCode().equalsIgnoreCase(code)) {
                return tours[i];
            }
        }
        return null;
    }

    @Override
    public Tour[] findAll() {
        Tour[] result = new Tour[count];
        System.arraycopy(tours, 0, result, 0, count);
        return result;
    }
}
