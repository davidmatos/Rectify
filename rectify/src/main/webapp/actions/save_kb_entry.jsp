
<%@page import="java.util.ArrayList"%>
<%@page import="pt.inesc.rectify.RectifyLogger"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="pt.inesc.rectify.hibernate.KbDbOp"%>
<%@page import="pt.inesc.rectify.Rectify"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	List<String> checkedIds = Arrays.asList(request.getParameterValues("kbDbOps"));
	ArrayList<KbDbOp> currentKbDbOps = new ArrayList<>();
	int i = 0;
	for (KbDbOp op : Rectify.currentKbDbOps) {
		i++;

		if (checkedIds.contains(i + "")) {

			// 			Rectify.currentKbDbOps.add(op);
			currentKbDbOps.add(op);

		}

	}

	Transaction t = Rectify.hibSession.beginTransaction();
	Rectify.hibSession.save(Rectify.currentKbHttpRequest);

	for (i = 0; i < currentKbDbOps.size(); i++) {

		Rectify.hibSession.save(currentKbDbOps.get(i));

		Rectify.hibSession.flush();
		Rectify.hibSession.clear();

	}

	Rectify.hibSession.save(Rectify.currentKbHttpResponse);
	t.commit();

	RectifyLogger.info("Added a new entry to the Knowledge Base");

	response.sendRedirect("/rectify/?p=knowledge_base");
%>