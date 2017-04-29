
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
	for (KbDbOp op : Rectify.getInstance().getCurrentKbDbOps()) {
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

	Rectify.getInstance().getHibSession().save(Rectify.getInstance().getCurrentKbHttpResponse());
	t.commit();

	RectifyLogger.info("Added a new entry to the Knowledge Base");

	response.sendRedirect("/rectify/?p=knowledge_base");
%>