
<%@page import="java.util.ArrayList"%>
<%@page import="pt.inesc.rectify.RectifyLogger"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="pt.inesc.rectify.hibernate.KbDbStatement"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	List<String> checkedIds = Arrays.asList(request.getParameterValues("kbDbOps"));
	ArrayList<KbDbStatement> currentKbDbOps = new ArrayList<>();
	int i = 0;
	for (KbDbStatement op : Rectify.getInstance().getCurrentKbDbStatements()) {
		i++;

		if (checkedIds.contains(i + "")) {

			// 			Rectify.currentKbDbOps.add(op);
			currentKbDbOps.add(op);

		}

	}

	Transaction t = Rectify.getInstance().getHibSession().beginTransaction();
	Rectify.getInstance().getHibSession().save(Rectify.getInstance().getCurrentKbHttpRequest());

	for (i = 0; i < currentKbDbOps.size(); i++) {

		Rectify.getInstance().getHibSession().save(currentKbDbOps.get(i));

		Rectify.getInstance().getHibSession().flush();
		Rectify.getInstance().getHibSession().clear();

	}
	t.commit();

	RectifyLogger.info("Added a new entry to the Knowledge Base");

	response.sendRedirect("/rectify/?p=knowledge_base");
%>