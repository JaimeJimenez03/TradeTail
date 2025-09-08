package app.platform.repositories.Municipality;


import app.platform.models.Municipality_group.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    List<Province> findProvinceByCcaa_Id(Long id);

}
