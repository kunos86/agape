package pl.ksb.agape;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.osgi.service.useradmin.User;


public class Authenticator  extends AuthorizingRealm {

	 public Authenticator()
	    {
	        super();
	        setName("Authenticator");
	    }
	 
	 
	 
	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
	    	System.out.println("AuthenticationInfo!!!!!!!!!!!!!!!!!");
	    	UsernamePasswordToken token = (UsernamePasswordToken) at;
	    	pl.ksb.agape.domain.model.User user = new pl.ksb.agape.domain.model.User();
	    	
//	    	assertRealmsConfigured();
//	    	 SimpleAccount account = new SimpleAccount();
//
//	            if (account != null) {
//	                SimplePrincipalCollection principals = new SimplePrincipalCollection();
//	                principals.add(osoba , getName());
//
//	                account.setPrincipals(principals);
//	            }
//
//	            return account;
	            
////	        User user = userDao.findUser(token.getUsername());
////	        if(user == null)
////	            throw new AuthenticationException("User with username: " + token.getUsername() + " does not exist.");
//	        
//	        Subject currentUser  = SecurityUtils.getSubject();
//	        currentUser.login(token);
//	        
//	        
	        user.setEmail(token.getUsername());
	        user.setPassword(token.getPassword().toString());
	 
	        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	    }
	 
	    @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
	    	System.out.println("AuthorizationInfo");
	        User user = (User)getAvailablePrincipal(pc);
	        Set roleNames = new HashSet();
	        Set permissions = new HashSet();
	 
//	        for(Role role : user.getRoles()) {
//	            roleNames.add(role.getName());
//	            for(Permission permission : role.getPermissions()) {
//	                permissions.add(permission.getName());
//	            }
//	        }
	 
	        roleNames.add("STUDENT");
	        permissions.add("STUDENT");
	        SimpleAuthorizationInfo simpleAuthInfo = new SimpleAuthorizationInfo(roleNames);
	        simpleAuthInfo.setStringPermissions(permissions);
	        return simpleAuthInfo;
	    }


	    
}
