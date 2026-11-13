package ec.edu.espoch.yasuniexplora.daointerface;

import ec.edu.espoch.yasuniexplora.objects.Guide;

public interface GuideDAO {
    boolean save(Guide guide);
    Guide findByNationalId(String nationalId);
    Guide[] findAll();
}
