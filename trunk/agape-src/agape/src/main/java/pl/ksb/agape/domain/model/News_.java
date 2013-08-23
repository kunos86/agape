package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-08-22T21:41:20.949+0200")
@StaticMetamodel(News.class)
public class News_ {
	public static volatile SingularAttribute<News, Long> id;
	public static volatile SingularAttribute<News, String> content;
	public static volatile SingularAttribute<News, Date> modificationDate;
}
