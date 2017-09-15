<%@page import="pt.inesc.rectify.utils.RectifyUtils"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.Map"%>
<%


Map<String, String[]> parameters = request.getParameterMap();


for(String name : parameters.keySet()){
	RectifyUtils.updateConfigurationEntry(name, parameters.get(name)[0]);
}


response.sendRedirect("/rectify");
%>
