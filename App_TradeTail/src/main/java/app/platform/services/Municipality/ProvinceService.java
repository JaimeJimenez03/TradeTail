package app.platform.services.Municipality;

import app.platform.models.Municipality_group.Province;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  ProvinceService {

    List<Province> findAllProvinces();

    List<Province> findProvinceByCcaa_Id(Long id);

}
