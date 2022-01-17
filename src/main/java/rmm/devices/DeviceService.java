package rmm.devices;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> findAllDevices() {
        return deviceRepository.findAll();
    }

    public boolean deviceDoesNotExist(String deviceId) {
        return !deviceRepository.existsById(deviceId);
    }

    public Device saveNewDevice(Device device) {
        return deviceRepository.save(device);
    }

}
