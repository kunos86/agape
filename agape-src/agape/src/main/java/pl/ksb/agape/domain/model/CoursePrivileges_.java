package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-08-22T21:41:20.724+0200")
@StaticMetamodel(CoursePrivileges.class)
public class CoursePrivileges_ {
	public static volatile SingularAttribute<CoursePrivileges, Long> id;
	public static volatile SingularAttribute<CoursePrivileges, Course> course;
	public static volatile SingularAttribute<CoursePrivileges, User> user;
	public static volatile SingularAttribute<CoursePrivileges, String> modificationOperator;
	public static volatile SingularAttribute<CoursePrivileges, Date> modificationDate;
}
