package tn.esprit.spring.entities;

import static tn.esprit.spring.constant.Authority.*;

public enum Role {
	ROLE_EMPLOYEE(EMPLOYEE_AUTHORITIES),
	//Moderator,
	ROLE_COMPANY(COMPANY_AUTHORITIES);
	//Learner,
	//Condidate
	private String[] authorities;
    //number of authorities
    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
