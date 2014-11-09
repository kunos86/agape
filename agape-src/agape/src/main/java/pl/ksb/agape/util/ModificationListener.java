package pl.ksb.agape.util;

import java.util.Calendar;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.apache.shiro.SecurityUtils;
import pl.ksb.agape.domain.dao.IModificationLoggable;

public class ModificationListener {

	@PrePersist
	void onPrePersist(Object o) {
		if (o instanceof IModificationLoggable) {
			addDateAndUser(o);
		}
	}

	@PreUpdate
	void onPreUpdate(Object o) {
		if (o instanceof IModificationLoggable) {
			addDateAndUser(o);
		}
	}

	private void addDateAndUser(Object o) {
		((IModificationLoggable) o).setModificationDate(Calendar.getInstance()
				.getTime());
		((IModificationLoggable) o).setModificationUserId((Long) SecurityUtils
				.getSubject().getSession().getAttribute("USER_ID"));
	}

}
