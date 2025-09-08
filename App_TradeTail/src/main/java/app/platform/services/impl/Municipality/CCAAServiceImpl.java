package app.platform.services.impl.Municipality;

import app.platform.models.Municipality_group.CCAA;
import app.platform.repositories.Municipality.CCAARepository;
import app.platform.services.Municipality.CCAAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CCAAServiceImpl implements CCAAService {

    @Autowired
    CCAARepository ccaaRepository;

    @Override
    public List<CCAA> findAllCCAA() {
        return ccaaRepository.findAll();
    }
}
