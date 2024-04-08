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
        return partnerRepository.findById(id).map(PartnerModel::to).orElse(null);
    }

    public List<PartnerModel> findAll() {
        List<PartnerModel> list = new ArrayList<>();
        partnerRepository.findAll().forEach(list::add);
        return list;
    }
}
