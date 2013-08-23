package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-08-22T21:41:20.686+0200")
@StaticMetamodel(Course.class)
public class Course_ {
	public static volatile SingularAttribute<Course, Long> id;
	public static volatile SingularAttribute<Course, Long> number;
	public static volatile SingularAttribute<Course, String> title;
	public static volatile SingularAttribute<Course, String> description;
	public static volatile SingularAttribute<Course, Boolean> enabled;
	public static volatile SingularAttribute<Course, Date> creationDate;
	public static volatile SingularAttribute<Course, Date> modificationDate;
	public static volatile SingularAttribute<Course, String> status;
	public static volatile SingularAttribute<Course, byte[]> image;
	public static volatile CollectionAttribute<Course, Lesson> lessons;
}
