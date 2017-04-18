<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: willian
  Date: 2017/3/29
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        table{
            border-collapse:collapse
        }
        td{
            width:100px;
            height:40px;
        }
        th{
            height:40px;
            width:80px;
        }
    </style>
    <title>个人简历提交</title>
</head>
<body>

<center>
    <h2>${student.name},您是第${times}位访问者</h2>  
</center>
<center>
        <table border="1">
                <c:if test="${student == null}">学生填写信息为空</c:if>
                <c:if test="${student != null}">
                    <tr>
                        <th >姓名:</th>
                        <td ><input style="border:none;height: 100%" type="text" name="student.name" value="${student.name}"/></td>
                        <th>性别:</th>
                        <td ><input type="text" name="student.sex" value="${student.sex}"/></td>
                        <th>出生年月:</th>
                        <td>1996.01.18</td>
                        <td rowspan="4" style="width:150px" ></td>
                    </tr>
                    <tr>
                        <th >民族:</th>
                        <td ><input type="text" name="student.nation" value="${student.nation}"/></td>
                        <th>政治面貌:</th>
                        <td><input type="text" name="student.zhengzhi" value="${student.zhengzhi}"/></td>
                        <th>身高:</th>
                        <td><input type="text" name="student.height" value="${student.height}"/></td>
                    </tr>
                    <tr>
                        <th>学制:</th>
                        <td><input type="text" name="student.studyTime" value="${student.studyTime}"/></td>
                        <th>学历:</th>
                        <td><input type="text" name="student.studyLevel" value="${student.studyLevel}"/></td>
                        <th>户籍:</th>
                        <td><input type="text" name="name" value="${student.home}"/></td>
                    </tr>
                    <tr>
                        <th>专业:</th>
                        <td><input type="text" name="student.subject" value="${student.subject}"/></td>
                        <th colspan="2" >毕业院校:</th>
                        <td colspan="2"><input type="text" name="name"/></td>
                    </tr>
                    <tr>
                        <th colspan="7">技能特长或爱好</th>
                    </tr>
                    <tr>
                        <th>外语等级:</th>
                        <td colspan="2"><input type="text" name="name"/></td>
                        <th>计算机:</th>
                        <td colspan="3"><input type="text" name="name"/></td>
                    </tr>
                    <tr>
                        <th colspan="7">个人履历</th>
                    </tr>
                    <tr>
                        <th>时间:</th>
                        <th colspan="2">单位:</th>
                        <th colspan="4">经历:</th>
                    </tr>
                    <tr>
                        <th>20&nbsp;年&nbsp;月&nbsp;</th>
                        <td colspan="2"></td>
                        <td colspan="4"></td>
                    </tr>
                    <tr>
                        <th>20&nbsp;年&nbsp;月&nbsp;</th>
                        <td colspan="2"></td>
                        <td colspan="4"></td>
                    </tr>
                    <tr>
                        <th>20&nbsp;年&nbsp;月&nbsp;</th>
                        <td colspan="2"></td>
                        <td colspan="4"></td>
                    </tr>
                    <tr>
                        <th colspan="7">联系方式:</th>
                    </tr>
                    <tr>
                        <th>通讯地址:</th>
                        <td colspan="3"></td>
                        <th>联系电话:</th>
                        <td colspan="2"></td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td colspan="3"></td>
                        <th>邮编:</th>
                        <td colspan="2"></td>
                    </tr>
                    <tr>
                        <th colspan="7">自我评价:</th>
                    </tr>
                    <tr style="height: 200px">
                        <td colspan="7"></td>
                    </tr>
                </c:if>


        </table>
</center>
</body>
</html>

