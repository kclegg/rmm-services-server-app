package rmm.devices;

import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public boolean deviceDoesNotExist(String deviceId) {
        return !deviceRepository.existsById(deviceId);
    }

    public Device saveNewDevice(Device device) {
        return deviceRepository.save(device);
    }

}
