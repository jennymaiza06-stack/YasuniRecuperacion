package ec.edu.espoch.yasuniexplora.datasource;

import ec.edu.espoch.yasuniexplora.daointerface.DeviceDAO;
import ec.edu.espoch.yasuniexplora.objects.CommunicationDevice;

public class DeviceDAOImpl implements DeviceDAO {
    private CommunicationDevice[] devices;
    private int count;

    public DeviceDAOImpl() {
        this.devices = new CommunicationDevice[20];
        this.count = 0;
    }

    public DeviceDAOImpl(int capacity) {
        this.devices = new CommunicationDevice[capacity];
        this.count = 0;
    }

    @Override
    public boolean save(CommunicationDevice device) {
        if (device == null || count >= devices.length || findByCode(device.getCode()) != null) {
            return false;
        }
        devices[count++] = device;
        return true;
    }

    @Override
    public CommunicationDevice findByCode(String code) {
        for (int i = 0; i < count; i++) {
            if (devices[i].getCode().equalsIgnoreCase(code)) {
                return devices[i];
            }
        }
        return null;
    }

    @Override
    public CommunicationDevice[] findAll() {
        CommunicationDevice[] result = new CommunicationDevice[count];
        System.arraycopy(devices, 0, result, 0, count);
        return result;
    }
}
