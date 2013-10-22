package org.fao.fi.figis.batch;

import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.fao.fi.figis.dao.FigisDao;
import org.fao.fi.figis.dao.config.FigisDataBaseProducer;
import org.fao.fi.figis.domain.ref.FicItem;
import org.flod.client.FlodClientGet;
import org.jglue.cdiunit.ActivatedAlternatives;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * ALTER TABLE fic_item ADD "SEMANTIC_ID" VARCHAR2(400 BYTE); COMMENT ON COLUMN
 * "FIGIS"."FIC_ITEM"."SEMANTIC_ID" IS 'Semantic URI identifier ';
 * 
 * 
 * @author Erik van Ingen
 * 
 */

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
			assertFalse(StringUtils.isBlank(ficItem.getSemanticId()));
		}

	}
}
