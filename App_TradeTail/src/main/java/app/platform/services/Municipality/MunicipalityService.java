package app.platform.services.Municipality;

import app.platform.models.Municipality_group.Municipality;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MunicipalityService {

    List<Municipality> getAll();

    List<Municipality> findMunicipalitiesByProvince_Id(Long id);
}
