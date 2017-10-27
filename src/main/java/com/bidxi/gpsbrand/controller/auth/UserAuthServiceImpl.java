package com.bidxi.gpsbrand.controller.auth;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.bidxi.gpsbrand.exception.ServiceException;
import com.bidxi.gpsbrand.service.SessionService;
import com.bidxi.gpsbrand.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Path("/user")
public class UserAuthServiceImpl
{

    @Autowired
    private SessionService sessionService;
    private final Logger log = Logger.getLogger(this.getClass());
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    /**
     * Retrieves the currently logged in user.
     *
     * @return A transfer containing the username and the roles.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = null;
        try {
            Object principal = (Object) this.sessionService.getSessionFromContext(Constant.USUARIO_SESSION);;
            if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
                throw new WebApplicationException(401);
            }
            userDetails = (UserDetails) principal;
        } catch (ServiceException e) {
            this.log.error("Ocurrió un error al obtener el usuario autenticado", e);
        }
        return Response.ok(new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails))).build();
    }

    /**
     * Authenticates a user and creates an authentication token.
     *
     * @param username The name of the user.
     * @param password The password of the user.
     * @return A transfer containing the authentication token.
     */
    @Path("authenticate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(@FormParam("username") String username, @FormParam("password") String password)
    {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
         */
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return Response.ok(TokenUtils.createToken(userDetails)).build();
    }

    private Map<String, Boolean> createRoleMap(UserDetails userDetails)
    {
        Map<String, Boolean> roles = new HashMap<>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }

        return roles;
    }

}
