package insper.store.partner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PartnerResource implements PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping("/partners/info")
    public ResponseEntity<Map<String, String>> info() {
        return new ResponseEntity<Map<String, String>>(
            Map.ofEntries(
                Map.entry("microservice.name", PartnerApplication.class.getSimpleName()),
                Map.entry("os.arch", System.getProperty("os.arch")),
                Map.entry("os.name", System.getProperty("os.name")),
                Map.entry("os.version", System.getProperty("os.version")),
                Map.entry("file.separator", System.getProperty("file.separator")),
                Map.entry("java.class.path", System.getProperty("java.class.path")),
                Map.entry("java.home", System.getProperty("java.home")),
                Map.entry("java.vendor", System.getProperty("java.vendor")),
                Map.entry("java.vendor.url", System.getProperty("java.vendor.url")),
                Map.entry("java.version", System.getProperty("java.version")),
                Map.entry("line.separator", System.getProperty("line.separator")),
                Map.entry("path.separator", System.getProperty("path.separator")),
                Map.entry("user.dir", System.getProperty("user.dir")),
                Map.entry("user.home", System.getProperty("user.home")),
                Map.entry("user.name", System.getProperty("user.name")),
                Map.entry("jar", new java.io.File(
                    PartnerApplication.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .getPath()
                    ).toString())
            ), HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<PartnerOut> create(PartnerIn in) {
        Partner partner = PartnerParser.to(in);
        partner = partnerService.create(partner);
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(partner.id())
                .toUri())
            .body(PartnerParser.to(partner));
    }

    @Override
    public ResponseEntity<PartnerOut> update(String id, PartnerIn in) {
        try {
            return ResponseEntity.ok(PartnerParser.to(partnerService.update(id, in)));
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<PartnerOut>> read() {
        return ResponseEntity.ok(partnerService.findAll().stream().map(PartnerParser::to).collect(Collectors.toList()));
    }


    @Override
    public ResponseEntity<?> delete(String id){
        try {
            partnerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
    
}
