package insper.store.partner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "partnerDB")
@EqualsAndHashCode(of = "id")
@Builder @Getter @Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class PartnerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_partner")
    private String id;

    @Column(name = "tx_name")
    private String name;

    @Column(name = "tx_email")
    private String email;

    @Column(name = "tx_cnpj")
    private String cnpj;

    public PartnerModel(Partner o) {
        this.id = o.id();
        this.name = o.name();
        this.email = o.email();
        this.cnpj = o.cnpj();
    }
    
    public Partner to() {
        return Partner.builder()
            .id(id)
            .name(name)
            .email(email)
            .cnpj(cnpj)
            .build();
    }

    public PartnerModel update(PartnerIn o) {
        this.name = o.name();
        this.email = o.email();
        this.cnpj = o.cnpj();
        return this;
    }
}
