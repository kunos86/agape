package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-11T20:01:37.138+0100")
@StaticMetamodel(UprawnieniaKurs.class)
public class UprawnieniaKurs_ {
	public static volatile SingularAttribute<UprawnieniaKurs, Long> id;
	public static volatile SingularAttribute<UprawnieniaKurs, Long> idKurs;
	public static volatile SingularAttribute<UprawnieniaKurs, Osoba> osoba;
	public static volatile SingularAttribute<UprawnieniaKurs, String> operator;
	public static volatile SingularAttribute<UprawnieniaKurs, Date> dataZm;
}
