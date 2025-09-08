package app.platform.services.Municipality;

import app.platform.models.Municipality_group.CCAA;
import app.platform.models.Municipality_group.Municipality;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CCAAService {

    public List<CCAA> findAllCCAA();

}
