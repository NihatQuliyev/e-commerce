package delivery.management.system.helper;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.model.entity.Driver;
import delivery.management.system.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DriverServiceHelper {

    private final DriverRepository driverRepository;
    private final ExceptionService exceptionService;

    public Driver driverSelection() {
        List<Driver> drivers = driverRepository.findAllByBusy(false);
        Driver driver = drivers.parallelStream()
                .findAny()
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse("Driver not found", HttpStatus.NOT_FOUND)));
        driver.setBusy(true);
        driverRepository.save(driver);
        return driver;
    }
}
