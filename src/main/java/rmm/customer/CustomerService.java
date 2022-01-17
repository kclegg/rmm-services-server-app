package rmm.customer;

import rmm.devices.Device;
import org.springframework.stereotype.Service;
import rmm.deviceservices.DeviceServicePlan;
import rmm.exceptions.NotFoundException;

import java.util.List;
import java.util.Set;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("No Customer found by Customer ID: " + customerId));
    }

    public List<Device> findAllDevicesByCustomerId(String customerId) {
        return findCustomerById(customerId).getDevices();
    }

    public Set<DeviceServicePlan> findAllDeviceServicePlansByCustomerId(String customerId) {
        return findCustomerById(customerId).getServices();
    }

    public Customer updateExistingDevice(Customer customer, Device newDevice) {
        List<Device> devices = customer.getDevices();
        List<String> deviceIds = customer.getDeviceIds();

        if(!deviceIds.contains(newDevice.getId())) {
            throw new NotFoundException("Customer " + customer.getId() + " doesn't contain existing device by id: " + newDevice.getId());
        }

        devices.forEach(device -> {
            if(device.getId().equals(newDevice.getId())) {
                device.setName(newDevice.getName());
                device.setType(newDevice.getType());
            }
        });
        customer.setDevices(devices);

        return customerRepository.save(customer);
    }

    public Customer deleteCustomerDevice(Customer customer, String deviceId) {
        List<Device> devices = customer.getDevices();

        devices.removeIf(device -> device.getId().equals(deviceId));
        customer.setDevices(devices);

        return customerRepository.save(customer);
    }

    public Customer saveNewDevice(Customer customer, Device device) {
        List<Device> devices = customer.getDevices();
        devices.add(device);
        customer.setDevices(devices);

        return customerRepository.save(customer);
    }

}
