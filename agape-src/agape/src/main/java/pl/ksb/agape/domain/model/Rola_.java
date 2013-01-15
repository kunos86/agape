package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.ksb.agape.domain.model.dict.RolaEnum;

@Generated(value="Dali", date="2013-01-15T13:26:12.078+0100")
@StaticMetamodel(Rola.class)
public class Rola_ {
	public static volatile SingularAttribute<Rola, Long> id;
	public static volatile SingularAttribute<Rola, Long> id_osoba;
	public static volatile SingularAttribute<Rola, RolaEnum> rola;
	public static volatile SingularAttribute<Rola, Date> dataArch;
}
