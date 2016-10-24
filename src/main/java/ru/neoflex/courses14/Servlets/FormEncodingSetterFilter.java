package ru.neoflex.courses14.Servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * FormEncodingSetterFilter
 *
 * @author Eugene Matyushkin
 */
@WebFilter(filterName = "FormEncodingSetterFilter",urlPatterns = "/*")
public class FormEncodingSetterFilter implements Filter {

    private static final String FILTERABLE_CONTENT_TYPE="application/x-www-form-urlencoded";

    private static final String ENCODING_DEFAULT = "UTF-8";

    private static final String ENCODING_INIT_PARAM_NAME = "encoding";

    private String encoding;

    public void destroy(){
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException {
        String contentType = req.getContentType();
        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE))
            req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException{
        encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
    }
}
