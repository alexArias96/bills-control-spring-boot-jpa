package com.bolsadeideas.springboot.app.auth.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//Logica para notificar al usuario que se ha logeado satisfactoriamente
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
        FlashMap flashMap = new FlashMap();

        //Adding message
        flashMap.put("success", "Hi "+ authentication.getName() +", You login successfully!");
        flashMapManager.saveOutputFlashMap(flashMap, request, response);

        if (authentication != null){
            logger.info("The user '" +authentication.getName()+ "' logged in successfully");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
