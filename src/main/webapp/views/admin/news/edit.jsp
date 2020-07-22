<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<c:url var="urlAPI" value="/api-admin-news"/>
	<c:url var="NewsUrl" value="/admin-news"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
							chủ</a></li>
					<li class="active">
						<c:if test="${empty model.id }">Thêm bài viết</c:if>
						<c:if test="${not empty model.id }">Chỉnh sửa bài viết</c:if>
					</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<c:if test="${not empty messageRespond }">
						<div class="alert alert-${alert }">
						  	${messageRespond }
						</div>
					</c:if>
					
					<form  id="formSubmit" >
						<div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                <div class="col-sm-9">                                 
                                    <select class="form-control" id="categoryCode" name="categoryCode">
                                    	<c:if test="${empty model.categoryCode }">
                                    		<option value="">---Chọn thể loại---</option>
									    	<c:forEach var="category" items="${categories}">
										    	<option value="${category.code }">${category.name }</option>
										    </c:forEach>
                                    	</c:if>
                                    	<c:if test="${not empty model.categoryCode }">
                                    		<option value="">---Chọn thể loại---</option>
									    	<c:forEach var="category" items="${categories}">
										    	<option value="${category.code }" <c:if test="${category.code== model.categoryCode }">selected="selected"</c:if> >
										    		${category.name }
										    	</option>
										    </c:forEach>
                                    	</c:if>
									    
									 </select>
                                </div>
                         </div>
                         <br/>
                         <br/>
                         <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                                <div class="col-sm-9">                                 
                                    <input type="text" class="form-control" id="title" name="title" value="${model.title }">
                                </div>
                         </div>
                         <br/>
                         <br/>
                         <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                                <div class="col-sm-9">                                 
                                    <input type="text" class="form-control" id="thumbnail" name="thumbnail" value="">
                                </div>
                         </div>
                         <br/>
                         <br/>
                         <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                <div class="col-sm-9">                                 
                                    <input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${model.shortDescription }">
                                </div>
                         </div>
                         <br/>
                         <br/>
                         <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                <div class="col-sm-9">                                 
                                    <textarea rows="" cols="" id="content" name="content" style="width: 820px;height: 175px">${model.content }</textarea>
                                </div>
                         </div>
                         <div class="form-group">
	                         <div class="col-sm-12">    
	                         	<c:if test="${empty model.id }"><input type="button" class="btn btn-primary btn-white btn-bold" id="btnAddOrUpdate" value="Thêm bài viết" ></c:if>                             
	                            <c:if test="${not empty model.id }"><input type="button" class="btn btn-primary btn-white btn-bold" id="btnAddOrUpdate" value="Cập nhật bài viết" ></c:if>                             
	                          </div>   
	                      </div>
	                      <input type="hidden" value="${model.id }" id="id" name="id">
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var editor='';
		$(document).ready(function(){
			editor= CKEDITOR.replace( 'content' );
		})
	
		$('#btnAddOrUpdate').click(function(e){
			e.preventDefault();
			var data ={};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i,v){
				data[""+v.name+""]= v.value;
			});
			data['content'] = editor.getData();
			var id = $('#id').val();
			
			if (id=="") {
				addNews(data);
			} else {
				updateNews(data);
			}
		});

		function addNews(data) {
			$.ajax({
				url : '${urlAPI}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${NewsUrl}?type=edit&id="+result.id+"&message=insert_success";
				},
				error : function(error) {
					console.log(error);
					window.location.href = "${NewsUrl}?page=1&maxPageItem=2&type=list&message=error_system";
				}
			});
		}
		function updateNews(data) {
			$.ajax({
				url : '${urlAPI}',
				type : 'PUT',
				contentType : "application/json",
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "${NewsUrl}?type=edit&id="+result.id+"&message=update_success";
				},
				error : function(error) {
					console.log(error);
					window.location.href = "${NewsUrl}?page=1&maxPageItem=2&type=list&message=error_system";
				}
			});
		}
	</script>
</body>
</html>