$(document).ready(function() {
	
	//SelectBox 회사 리스트
 	$.ajax({
		url: "/clist/posts",
		method: "GET",
		dataType: "json",
		success : function(res){
			
			for (i=0; i<res.length; i++) {
                $("#company").append('<option value="'+res[i].id+'">'+ res[i].cname +'</option>');
			}
		} 
	 });
	 
	 //모달 SelectBox 회사 리스트
	 $.ajax({
		url: "/clist/posts",
		method: "GET",
		dataType: "json",
		success : function(res){
			
			for (i=0; i<res.length; i++) {
                $("#companyModal").append('<option value="'+res[i].id+'">'+ res[i].cname +'</option>');
			}
		} 
	 });
});

	//selectBox 부서 리스트
	function company(){
		var companyId = $("#company option:selected").val();
		$.ajax({
		url: "/clist/getDepartments",
		method: "GET",
		dataType: "json",
		data: {
			companyId : companyId
		},
		success : function(res){
			 $("#department").empty();
			 $("#department").append('<option value="">선택</option>');
			for (i=0; i<res.length; i++) {
                $("#department").append('<option value="'+res[i].id+'">'+ res[i].dname +'</option>');
			}
		} 
	 });
	}
	
	// 모달 SelectBox 부서 리스트
	function Modal1(){
		var companyId = $("#companyModal option:selected").val();
		$.ajax({
		url: "/clist/getDepartments",
		method: "GET",
		dataType: "json",
		data: {
			companyId : companyId
		},
		success : function(res){
			 $("#department").empty();
			 $("#department").append('<option value="">선택</option>');
			for (i=0; i<res.length; i++) {
	            $("#departmentModal").append('<option value="'+res[i].id+'">'+ res[i].dname +'</option>');
			}
		} 
	 });
	}
	
	
	// 모달 등록 버튼 이벤트
	 $("#btnSave").click(function(){
		// 등록할 값 추출 
        let companyId = $("#companyModal option:selected").val();
        let departmentId = $("#departmentModal option:selected").val();
        let username = $('input[name=name]').val(); 
        let email = $("#email").val();
		
		var data = {"name" : username, "email":email, "company": companyId, "department": departmentId};
		
		//등록 시작
	   $.ajax({
		url: "/employee/emCreate",
		method: "POST",
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		data: JSON.stringify(data),
		done : function(data){
			console.log(data);
		},
		fail : function(error) {
			console.log(error);
		} 
	   });
    });
	


