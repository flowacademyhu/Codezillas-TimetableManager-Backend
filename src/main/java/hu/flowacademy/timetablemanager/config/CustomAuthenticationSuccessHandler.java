package hu.flowacademy.timetablemanager.config;

import net.minidev.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String token = session.getId();
        JSONObject responseObj = getResponseData(token);

        out.print(responseObj.toString());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private JSONObject getResponseData(String token) {


        String roles = getRoles();
        Map<String, String> resultData = new HashMap<>();
        resultData.put("token", token);
        resultData.put("roles", roles);
        return new JSONObject(resultData);
    }

    private String getRoles() {
        StringBuilder roles = new StringBuilder();
        try {
            Collection<GrantedAuthority> authorities = new HashSet<>();
            authorities.addAll(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            for (GrantedAuthority role : authorities) {
                if (!roles.toString().equals("")) roles.append(",");
                roles.append(role.toString());
            }
        } catch (Exception e) {
            throw e;
        }
        return roles.toString();
    }
}