package org.fao.fi.figis.batch;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.fao.fi.figis.dao.FigisDao;
import org.fao.fi.figis.domain.ref.FicItem;
import org.flod.client.FlodClientGet;
import org.flod.service.dto.FetchURIresponse;
import org.flod.service.dto.FlodService;

@ApplicationScoped
public class BatchUpgradeSpeciesUrl {

	public static String CODELIST = "asfis";

	FlodClientGet FlodClientGet;

	@Inject
	private FlodService flodService;

	@Inject
	private FigisDao figisDao;

	public void fillSemanticUrlInSpecies() {
		List<?> list = figisDao.loadObjects(FicItem.class);
		for (Object object : list) {
			FicItem ficItem = (FicItem) object;
			FetchURIresponse uri = flodService.fetchURI(CODELIST, ficItem.getAlpha3code());
			ficItem.setSemanticId(uri.getURI().toString());
			figisDao.merge(ficItem);
		}
	}
}
