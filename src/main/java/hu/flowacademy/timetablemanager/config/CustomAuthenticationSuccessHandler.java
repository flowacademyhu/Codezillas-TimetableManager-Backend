package hu.flowacademy.timetablemanager.config;

<<<<<<< Updated upstream
=======
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.service.authentication.CustomUserDetailsService;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> Stashed changes
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
<<<<<<< Updated upstream
        // This is actually not an error, but an OK message. It is sent to avoid redirects.
        response.sendError(HttpServletResponse.SC_OK);
=======
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
        Map<String, String> resultData = new HashMap<>();
        resultData.put("token", token);
        resultData.put("roles", getRoles());
        resultData.put("userId", getUserIdInString());
//        resultData.put("user", getCurrentUserInJsonString()); // resend userDTO in json
        return new JSONObject(resultData);
    }

    private String getCurrentUserInJsonString() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = customUDS.findByEmail(userEmail);
        UserDTO responseDTO = customUDS.toDto(currentUser);
        responseDTO.setPassword(""); // clear password field!
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(responseDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    private String getUserIdInString() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = customUDS.findByEmail(userEmail);
        return String.valueOf(currentUser.getId());
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
>>>>>>> Stashed changes
    }
}