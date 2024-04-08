package insper.store.partner;

public class PartnerParser {

    public static Partner to(PartnerIn in) {
        return Partner.builder()
            .email(in.email())
            .name(in.name())
            .password(in.password())
            .build();
    }

    public static PartnerOut to(Partner partner) {
        return PartnerOut.builder()
            .id(partner.id())
            .email(partner.email())
            .name(partner.name())
            .build();
    }
    
}
