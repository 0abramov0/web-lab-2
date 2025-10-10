package filters;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoundFilter implements Filter {
    private String[] expectedParameters;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String paramNamesStr = filterConfig.getInitParameter("paramNames");
        expectedParameters = paramNamesStr.split(",");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        for (String parameterName : expectedParameters) {
            List<String> roundedValues = new ArrayList<>();
            String[] values = request.getParameterValues(parameterName);
            if(values == null) continue;
            for (String value : values) {
                roundedValues.add(roundFloatValues(value));
            }
            request.setAttribute(parameterName, roundedValues.toArray(new String[0]));
        }
        chain.doFilter(request, response);
    }

    private String roundFloatValues(String num) {
        num = num.replaceAll(",", ".");
        Pattern isNumberPattern = Pattern.compile("^-?\\d+([.,]\\d+)?$");
        Matcher matcher = isNumberPattern.matcher(num);
        if (!matcher.matches()) {
            return num;
        }
        Pattern isRoundedPattern = Pattern.compile("^-?\\d+([.,]\\d{0,3})?$");
        matcher = isRoundedPattern.matcher(num);
        if (matcher.matches()) {
            return num;
        }
        Pattern roundPattern = Pattern.compile("^(-?\\d+([.,]\\d{0,3}))\\d+$");
        matcher = roundPattern.matcher(num);
        return matcher.replaceAll("$1");
    }
}
