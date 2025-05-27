package de.unibayreuth.se.campuscoffee;

import de.unibayreuth.se.campuscoffee.domain.model.Pos;
import de.unibayreuth.se.campuscoffee.domain.ports.PosService;
import de.unibayreuth.se.campuscoffee.domain.tests.TestFixtures;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Load initial data into the list via the list service from the business layer.
 */
@Component
@RequiredArgsConstructor
@Slf4j
@Profile("dev")
class LoadInitialData implements InitializingBean {
    private final PosService posService;

    @Override
    public void afterPropertiesSet() {
        log.info("Deleting existing data...");
        posService.clear();
        // TODO: Delete users.
        log.info("Loading initial data...");
        List<Pos> posList = TestFixtures.createPos(posService);
        log.info("Loaded {} POS.", posList.size());
        // TODO: Load users.
        log.info("Initial data loaded successfully.");
    }
}