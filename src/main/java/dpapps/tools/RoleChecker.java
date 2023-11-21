package dpapps.tools;

import dpapps.constants.RoleConstants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleChecker {

    public List<String> roles = new ArrayList<>();

    public RoleChecker() {
        this.roles.add(RoleConstants.ROLE_ADMIN);
        this.roles.add(RoleConstants.ROLE_DESIGNER);
        this.roles.add(RoleConstants.ROLE_OPERATOR);

    }

    public boolean isRoleValid(String role) {
        if (this.roles.contains(role)) {
            return true;
        }
        return false;
    }

}
