package rmm.devices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/device")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping(value = "/getAllDevices")
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.findAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
}
