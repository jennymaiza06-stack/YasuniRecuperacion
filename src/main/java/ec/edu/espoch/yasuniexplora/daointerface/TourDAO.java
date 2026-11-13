package ec.edu.espoch.yasuniexplora.daointerface;

import ec.edu.espoch.yasuniexplora.objects.Tour;

public interface TourDAO {
    boolean save(Tour tour);
    Tour findByCode(String code);
    Tour[] findAll();
}
