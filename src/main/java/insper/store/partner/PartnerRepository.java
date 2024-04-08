package insper.store.partner;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends CrudRepository<PartnerModel, String> {
    Optional<PartnerModel> findByEmailAndCnpj(String email, String cnpj);
}
