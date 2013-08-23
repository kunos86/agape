package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-08-15T16:50:30.750+0200")
@StaticMetamodel(EducationState.class)
public class EducationState_ {
	public static volatile SingularAttribute<EducationState, Long> id;
	public static volatile SingularAttribute<EducationState, User> student;
	public static volatile SingularAttribute<EducationState, Lesson> lesson;
	public static volatile SingularAttribute<EducationState, Date> sharedDate;
	public static volatile SingularAttribute<EducationState, Date> sentDate;
	public static volatile SingularAttribute<EducationState, Date> checkedDate;
	public static volatile SingularAttribute<EducationState, String> comment;
	public static volatile SingularAttribute<EducationState, Date> readedDate;
	public static volatile SingularAttribute<EducationState, Boolean> readedCommentDate;
}
