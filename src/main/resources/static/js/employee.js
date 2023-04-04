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
});

	//selectBox 부서 리스트
	function company(){
		var companyId = $("#company option:selected").val();
		console.log(companyId);
		$.ajax({
		url: "/clist/getDepartments",
		method: "GET",
		dataType: "json",
		data: {
			companyId : companyId
		},
		success : function(res){
			for (i=0; i<res.length; i++) {
                $("#department").append('<option value="'+res[i].id+'">'+ res[i].dname +'</option>');
			}
		} 
	 });
	}


