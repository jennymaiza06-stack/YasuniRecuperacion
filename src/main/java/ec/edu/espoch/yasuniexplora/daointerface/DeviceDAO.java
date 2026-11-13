package ec.edu.espoch.yasuniexplora.daointerface;

import ec.edu.espoch.yasuniexplora.objects.CommunicationDevice;

public interface DeviceDAO {
    boolean save(CommunicationDevice device);
    CommunicationDevice findByCode(String code);
    CommunicationDevice[] findAll();
}
