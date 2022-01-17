package rmm.deviceservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceServicePlanRepository extends JpaRepository<DeviceServicePlan, String> {
}
