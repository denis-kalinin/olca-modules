package org.openlca.io.olca;

import org.openlca.core.database.ActorDao;
import org.openlca.core.database.CategoryDao;
import org.openlca.core.database.IDatabase;
import org.openlca.core.model.Actor;
import org.openlca.core.model.descriptors.ActorDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ActorImport {

	private Logger log = LoggerFactory.getLogger(getClass());

	private ActorDao sourceDao;
	private ActorDao destDao;
	private CategoryDao destCategoryDao;
	private Sequence seq;

	ActorImport(IDatabase source, IDatabase dest, Sequence seq) {
		this.sourceDao = new ActorDao(source);
		this.destDao = new ActorDao(dest);
		this.destCategoryDao = new CategoryDao(dest);
		this.seq = seq;
	}

	public void run() {
		log.trace("import actors");
		try {
			for (ActorDescriptor descriptor : sourceDao.getDescriptors()) {
				if (seq.contains(seq.ACTOR, descriptor.getRefId()))
					continue;
				createActor(descriptor);
			}
		} catch (Exception e) {
			log.error("Actor import failed", e);
		}
	}

	private void createActor(ActorDescriptor descriptor) {
		Actor srcActor = sourceDao.getForId(descriptor.getId());
		Actor destActor = srcActor.clone();
		destActor.setRefId(srcActor.getRefId());
		if (srcActor.getCategory() != null) {
			long catId = seq.get(seq.CATEGORY, srcActor.getCategory().getRefId());
			destActor.setCategory(destCategoryDao.getForId(catId));
		}
		destActor = destDao.insert(destActor);
		seq.put(seq.ACTOR, srcActor.getRefId(), destActor.getId());
	}
}