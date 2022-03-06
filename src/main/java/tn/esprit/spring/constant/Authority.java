package tn.esprit.spring.constant;

public class Authority {
    public static final String[] EMPLOYEE_AUTHORITIES = { "user:read" };
    //public static final String[] HR_AUTHORITIES = { "user:read", "user:update" };
    //public static final String[] MANAGER_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] COMPANY_AUTHORITIES = { "user:read", "user:create", "user:update", "user:delete" };
    //public static final String[] SUPER_ADMIN_AUTHORITIES = { "user:read", "user:create", "user:update", "user:delete" };
}
