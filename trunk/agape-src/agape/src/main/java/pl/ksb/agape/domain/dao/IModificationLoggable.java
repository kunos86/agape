package pl.ksb.agape.domain.dao;

import java.util.Date;


/**
 * Encja z tym interfejsem automatycznie doda datę i użytkownika modyfikacji zmian
 * @author piotr
 *
 */
public interface IModificationLoggable {

	void setModificationDate(Date modificationDate);
	void setModificationUserId(Long modificationUserId);
}
