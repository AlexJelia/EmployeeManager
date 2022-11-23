<%@ page import="alex.jelia.empmanager.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/list_style.css">
    <title>Resumes List</title>
</head>
<body>
<jsp:include page="prepared/header.jsp"/>
<div class="scrollable-panel">
    <div class="table-wrapper">
        <div class="add-resume">
            <a class="no-underline-anchor" href="resume?action=add">
                <img src="img/add.png" alt="">
            </a>
            <a class="text-anchor" href="resume?action=add">
                <p class="add-resume-title">Add Resume</p>
            </a>
        </div>
        <div class="resumes-list">
            <table>
                <tr class="t-header">
                    <th class="name-column">Name</th>
                    <th class="info-column">Contacts</th>
                    <th class="img-column">Edit</th>
                    <th class="img-column">Delete</th>
                </tr>
                <c:forEach items="${resumes}" var="resume">
                    <jsp:useBean id="resume" type="alex.jelia.empmanager.webapp.model.Resume"/>
                    <tr class="t-body">
                        <td class="name-column">
                            <a class="contact-link"
                               href="resume?uuid=${resume.uuid}&action=view&theme=${theme}">${resume.fullName}</a>
                        </td>
                        <td class="info-column">
                            <%=ContactType.MAIL.toHtml(resume.getContact(ContactType.MAIL))%>
                        </td>
                        <td class="img-column">
                            <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=edit&theme=${theme}">
                                <img src="img/pencil.png" alt="">
                            </a>
                        </td>
                        <td class="img-column">
                            <a class="no-underline-anchor"
                               href="resume?uuid=${resume.uuid}&action=delete&theme=${theme}">
                                <img src="img/delete.png" alt="">
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<jsp:include page="prepared/footer.jsp"/>
</body>
</html>