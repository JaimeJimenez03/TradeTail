package app.platform.services.impl.Municipality;

import app.platform.models.Municipality_group.Municipality;
import app.platform.repositories.Municipality.MunicipalityRepository;
import app.platform.services.Municipality.MunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipalityServiceImpl  implements MunicipalityService {

    @Autowired
    MunicipalityRepository municipalityRepository;

    @Override
    public List<Municipality> getAll() {
        return municipalityRepository.findAll();
    }

    @Override
    public List<Municipality> findMunicipalitiesByProvince_Id(Long id) {
        if (id != null) {
            return municipalityRepository.findMunicipalitiesByProvince_Id(id);

        }
        return null;
    }
}
