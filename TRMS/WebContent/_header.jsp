<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<div style="background: #E0E0E0; height: 55px; padding: 5px;">
  <div style="float: left">
     <h1>in _header My Site</h1>
  </div>
 
  <div style="float: right; padding: 10px; text-align: right;">
 
     <!-- User store in session with attribute: loggedInEmployee -->
     Hello <b>${loggedInEmployee.userName}</b>
   <br/>
     Search <input name="search">
 
  </div>
 
</div>