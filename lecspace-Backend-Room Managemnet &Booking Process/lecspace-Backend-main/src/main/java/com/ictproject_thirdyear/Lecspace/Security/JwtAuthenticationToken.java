package com.ictproject_thirdyear.Lecspace.Security;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

    public class JwtAuthenticationToken extends org.springframework.security.authentication.AbstractAuthenticationToken
    {

        private final Object principal;
        private final Object credentials;

        public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities)
        {
            super(authorities);
            this.principal = principal;
            this.credentials = credentials;
            setAuthenticated(true);
        }

        @Override
        public Object getCredentials()
        {
            return credentials;
        }

        @Override
        public Object getPrincipal()
        {
            return principal;
        }
    }


