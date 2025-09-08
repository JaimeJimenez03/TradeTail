package app.platform.repositories.Municipality;

import app.platform.models.Municipality_group.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality,Long> {

    List<Municipality> findMunicipalitiesByProvince_Id(Long id);
}
