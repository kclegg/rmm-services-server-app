package rmm.deviceservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/deviceserviceplan")
public class DeviceServicePlanController {
    private final DeviceServicePlanService deviceServicePlanService;

    public DeviceServicePlanController(DeviceServicePlanService deviceServicePlanService) {
        this.deviceServicePlanService = deviceServicePlanService;
    }

    @GetMapping(value = "/getAllDeviceServicePlans")
    public ResponseEntity<List<DeviceServicePlan>> getAllDeviceServicePlans() {
        List<DeviceServicePlan> devices = deviceServicePlanService.findAllDeviceServicePlans();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
}
