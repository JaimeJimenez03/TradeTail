package app.platform.repositories.Municipality;

import app.platform.models.Municipality_group.CCAA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CCAARepository extends JpaRepository<CCAA,Long> {


}
