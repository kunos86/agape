package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-01-15T13:26:11.971+0100")
@StaticMetamodel(News.class)
public class News_ {
	public static volatile SingularAttribute<News, Long> id;
	public static volatile SingularAttribute<News, String> tresc;
	public static volatile SingularAttribute<News, Date> dataArch;
}
