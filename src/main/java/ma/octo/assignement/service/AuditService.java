package ma.octo.assignement.service;

import ma.octo.assignement.domain.Audit;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuditService {

    Logger LOGGER = LoggerFactory.getLogger(AuditService.class);

    @Autowired
    private AuditRepository auditRepository;

    public void auditVirement(String message) {

        LOGGER.info("Audit de l'événement {}", EventType.VIREMENT);

        Audit audit = new Audit();
        audit.setEventType(EventType.VIREMENT);
        audit.setMessage(message);
        auditRepository.save(audit);
    }


    public void auditVersement(String message) {

        LOGGER.info("Audit de l'événement {}", EventType.VERSEMENT);

        Audit audit = new Audit();
        audit.setEventType(EventType.VERSEMENT);
        audit.setMessage(message);
        auditRepository.save(audit);
    }
}
