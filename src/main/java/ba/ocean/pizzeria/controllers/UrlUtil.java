package ba.ocean.pizzeria.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

/**
 * @author 		Chris Schaefer, Clarence Ho, Rob Harrop
 * @since		05-2015
 * 
 * Utility class from book 'Pro Spring' (Apress)
 */

public class UrlUtil {
    public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();

        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }

        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
            //
        }

        return pathSegment;
    }
}
