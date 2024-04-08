package insper.store.partner;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public Partner create(Partner in) {
        in.hash(calculateHash(in.password()));
        in.password(null);
        return partnerRepository.save(new PartnerModel(in)).to();
    }

    public Partner read(@NonNull String id) {
        return partnerRepository.findById(id).map(PartnerModel::to).orElse(null);
    }

    private String calculateHash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            byte[] encoded = Base64.getEncoder().encode(hash);
            return new String(encoded);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
}
