
<%@page import="java.io.File"%>
<%@page import="wbpckg.Jcrf"%>
<%@page import="wbpckg.Visit"%>
<%@page import="wbpckg.Square"%>
<%@page import="java.util.ArrayList"%>

<%--@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : newjsp
    Created on : Apr 7, 2015, 12:22:22 AM
    Author     : xtfkpi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>jCRF</title>
    </head>

    <h1>Hello jCRF!</h1>

    <%! Jcrf jcrf = new Jcrf(); %>
       do we have db?<%=(new File("jcrf.xml")).exists()%>
       <% if (!(new File("jcrf.xml")).exists()) {
        jcrf.addTestData(); jcrf.toXml();
    }%><br>
    
    <%! int i;%>

    <% i = 0; %>

    <%  jcrf.fromXml();
        out.println(jcrf.vst);
    if (request.getParameter("btnSubmit") == null) {
            out.println("form was not submitted");
        } else {
            out.println("was submitted" + request.getParameter("vstid") + ",");
            out.println(request.getParameter("ptn") + request.getParameter("nmr")
                    + request.getParameter("dt") + request.getParameter("ttr"));
            jcrf.updAddRow(request.getParameter("vstid"), request.getParameter("ptn"),
                    request.getParameter("nmr"), "", request.getParameter("ttr"));
        }
       
    %>
    <br>records in db:<%=jcrf.vst.size()%><br>

    <table border="01" width="90%">
        <thead>
            <tr>
                <th>номер пациента</th>
                <th>номер визита</th>
                <th>фактическая дата прихода</th>
                <th>измеренная температура</th>
            </tr>
        </thead>
        <tbody>

            <% for (i = 0; i < jcrf.vst.size(); i++) {

                    out.print("<tr> <form name='jcrfform' action='index.jsp'>"
                            + "<input type='hidden' name='vstid' value='" + jcrf.vst.get(i).id + "'/>"
                            + "<td><input type='text' name='ptn' value='"
                            + jcrf.vst.get(i).getPtnS() + "'/></td>"
                            + "<td><input type='text' name='nmr' value='"
                            + jcrf.vst.get(i).getNmrS() + "'/></td>"
                            + "<td><input type='text' name='dt' value='" + jcrf.vst.get(i).getDtS()
                            + "' disabled/></td>"
                            + "<td><input type='text' name='ttr' value='"
                            + jcrf.vst.get(i).getTtrS() + "'/></td>"
                            + "<td> <input type='submit' value='Submit' name='btnSubmit' /></td>"+
                            "<td> <input type='submit' value='Delete' name='btnDelete' /></td>"+
                            "</form></tr>");
                }
            %>
        </tbody>
    </table>




</html>

