package org.pollbox.poll.auth;

import java.util.Arrays;
import java.util.List;

public enum Role {
    DEFAULT("ROLE_OWNER", "Owner. All permissions granted"),
    OWNER("ROLE_OWNER", "Owner. All permissions granted"),
    SUPERVISOR("ROLE_SUPERVISOR", "Supervisor. Permissions to create, edit and delete projects and users"),
    OPERATOR("ROLE_OPERATOR", "Operator. Permissions to view projects, create end edit polls"),
    USER("ROLE_USER", "User. Limited permissions to view some projects end polls");

    private String code;
    private String description;
    
    private Role(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public static List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
