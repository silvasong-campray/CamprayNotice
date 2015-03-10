<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
                   <c:forEach var="map" items="${infoMap}">
                    	<div class="row">
						<!-- BEGIN LEFT SIDEBAR -->
						<div class="col-md-9 col-sm-9 blog-posts">
							<div class="row">

								<div class="col-md-8 col-sm-8">
									<h2>${map.value[0]}</h2>
									${map.value[1]}



								</div>
							</div>
						</div>
						<!-- END RIGHT SIDEBAR -->
					  </div>
					  <hr class="blog-post-sep">
                    </c:forEach>
					
				