package app.platform.services.impl.Municipality;

import app.platform.models.Municipality_group.Province;
import app.platform.repositories.Municipality.ProvinceRepository;
import app.platform.services.Municipality.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;


    @Override
    public List<Province> findAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public List<Province> findProvinceByCcaa_Id(Long id) {
        if (id != null) {
            return provinceRepository.findProvinceByCcaa_Id(id);
        }
        return null;
    }
}
