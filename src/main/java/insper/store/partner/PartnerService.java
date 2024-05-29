package insper.store.partner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@SuppressWarnings("unused")
@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    // @CachePut(value = "partner", key = "#result.id")
    public Partner create(Partner in) {
        Partner partner = partnerRepository.save(new PartnerModel(in)).to();
        return partner;
        // return partnerRepository.save(new PartnerModel(in)).to();
    }

    // @Cacheable(value = "partner", key = "#id")
    public Partner read(@NonNull String id) {
        return partnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Partner not found")).to();
    }

    // @Cacheable(value = "allpartners")
    public List<PartnerModel> findAll() {
        List<PartnerModel> list = new ArrayList<>();
        partnerRepository.findAll().forEach(list::add);
        return list;
    }

    // @CachePut(value = "partner", key = "#id")
    public Partner update(@NonNull String id, PartnerIn in) {
        PartnerModel partner = partnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Partner not found"));
        partner.update(in);
        return partnerRepository.save(partner).to();
    }

    // @CacheEvict(value = "partner", key = "#id")
    public void delete(@NonNull String id) {
        if(partnerRepository.existsById(id)){
            partnerRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Partner not found");
        }
    }
}
