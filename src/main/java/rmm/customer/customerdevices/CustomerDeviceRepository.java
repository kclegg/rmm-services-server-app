package rmm.customer.customerdevices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDeviceRepository extends JpaRepository<CustomerDevice, String> {
}
