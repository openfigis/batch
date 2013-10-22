package org.fao.fi.figis.batch;

import static org.junit.Assert.assertNotNull;

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
@AdditionalClasses(FlodClientGet.class)
@ActivatedAlternatives({ FigisDataBaseProducer.class })
public class BatchUpgradeSpeciesUrlTest {

	@Inject
	BatchUpgradeSpeciesUrl batch;

	@Inject
	FigisDao figisDao;

	@Test
	public void testFillSemanticUrlInSpecies() {
		String alpha3 = "TUN";
		Long id = 4324324l;

		FicItem f = new FicItem();
		f.setFicItem(id);
		f.setAlpha3code(alpha3);
		figisDao.persist(f);
		batch.fillSemanticUrlInSpecies();
		FicItem found = (FicItem) figisDao.find(FicItem.class, id);
		assertNotNull(found.getSemanticId());
		figisDao.remove(f);

	}
}
