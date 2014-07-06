package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-03-18T09:59:09.273+0100")
@StaticMetamodel(Lesson.class)
public class Lesson_ {
	public static volatile SingularAttribute<Lesson, Course> course;
	public static volatile SingularAttribute<Lesson, Date> creationDate;
	public static volatile SingularAttribute<Lesson, String> description;
	public static volatile CollectionAttribute<Lesson, EducationState> educationStates;
	public static volatile SingularAttribute<Lesson, Boolean> enabled;
	public static volatile SingularAttribute<Lesson, String> homework;
	public static volatile SingularAttribute<Lesson, Long> id;
	public static volatile SingularAttribute<Lesson, String> introduction;
	public static volatile SingularAttribute<Lesson, Date> modificationDate;
	public static volatile SingularAttribute<Lesson, Long> number;
	public static volatile SingularAttribute<Lesson, String> status;
}
