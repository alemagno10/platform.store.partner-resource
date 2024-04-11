package insper.store.partner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public Partner create(Partner in) {
        return partnerRepository.save(new PartnerModel(in)).to();
    }

    public Partner read(@NonNull String id) {
        return partnerRepository.findById(id).map(PartnerModel::to).orElseThrow(() -> new IllegalArgumentException("Partner not found"));
    }

    public List<PartnerModel> findAll() {
        List<PartnerModel> list = new ArrayList<>();
        partnerRepository.findAll().forEach(list::add);
        return list;
    }

    public Partner update(@NonNull String id, PartnerIn in) {
        PartnerModel partner = partnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Partner not found"));
        partner.update(in);
        return partnerRepository.save(partner).to();
    }

    public void delete(@NonNull String id) {
        if(partnerRepository.existsById(id)){
            partnerRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Partner not found");
        }
    }
}
