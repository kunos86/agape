package pl.ksb.agape.domain.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-06T11:41:28.950+0200")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> address;
	public static volatile SingularAttribute<User, Date> birthDate;
	public static volatile SingularAttribute<User, String> code;
	public static volatile SingularAttribute<User, String> community;
	public static volatile SingularAttribute<User, String> country;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> place;
	public static volatile SingularAttribute<User, String> province;
	public static volatile SingularAttribute<User, Date> registrationDate;
	public static volatile SingularAttribute<User, String> religion;
	public static volatile ListAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, String> sex;
	public static volatile SingularAttribute<User, String> status;
	public static volatile SingularAttribute<User, String> surname;
	public static volatile SingularAttribute<User, Boolean> mailConfirmation;
}
