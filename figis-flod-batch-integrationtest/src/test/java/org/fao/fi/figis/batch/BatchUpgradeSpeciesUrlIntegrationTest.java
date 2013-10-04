package org.fao.fi.figis.batch;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.fao.fi.figis.dao.FigisDao;
import org.fao.fi.figis.dao.config.FigisDataBaseProducer;
import org.fao.fi.figis.domain.ref.FicItem;
import org.flod.client.FlodClientGet;
import org.jglue.cdiunit.ActivatedAlternatives;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@ActivatedAlternatives(FigisDataBaseProducer.class)
@AdditionalClasses(FlodClientGet.class)
public class BatchUpgradeSpeciesUrlIntegrationTest {

	@Inject
	BatchUpgradeSpeciesUrl batch;

	@Inject
	FigisDao figisDao;

	@Test
	public void testFillSemanticUrlInSpecies() {
		batch.fillSemanticUrlInSpecies();

		List<?> list = figisDao.loadObjects(FicItem.class);
		for (Object object : list) {
			FicItem ficItem = (FicItem) object;
			assertNotNull(ficItem.getSemanticId());
		}

	}
}
