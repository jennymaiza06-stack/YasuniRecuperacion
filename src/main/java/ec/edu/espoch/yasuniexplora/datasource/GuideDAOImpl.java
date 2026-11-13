package ec.edu.espoch.yasuniexplora.datasource;

import ec.edu.espoch.yasuniexplora.daointerface.GuideDAO;
import ec.edu.espoch.yasuniexplora.objects.Guide;

public class GuideDAOImpl implements GuideDAO {
    private Guide[] guides;
    private int count;

    public GuideDAOImpl() {
        this.guides = new Guide[20];
        this.count = 0;
    }

    public GuideDAOImpl(int capacity) {
        this.guides = new Guide[capacity];
        this.count = 0;
    }

    @Override
    public boolean save(Guide guide) {
        if (guide == null || count >= guides.length || findByNationalId(guide.getNationalId()) != null) {
            return false;
        }
        guides[count++] = guide;
        return true;
    }

    @Override
    public Guide findByNationalId(String nationalId) {
        for (int i = 0; i < count; i++) {
            if (guides[i].getNationalId().equalsIgnoreCase(nationalId)) {
                return guides[i];
            }
        }
        return null;
    }

    @Override
    public Guide[] findAll() {
        Guide[] result = new Guide[count];
        System.arraycopy(guides, 0, result, 0, count);
        return result;
    }
}
