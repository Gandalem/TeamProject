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
		$("#btnSearch").prop("disabled", true);
		let companyId = $("#company option:selected").val();
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
	
	//SelectBox 사원 리스트
	function department(){		
		let companyId = $("#company option:selected").val();
		let departmentId = $("#department option:selected").val();
		
		$.ajax({
			url: "/employee/userList",
			method: "GET",
			dataType: "json",
			data: {
				company: companyId,
	        	department: departmentId
			},
			success : function(res){
				 $("#username").empty();
				 $("#username").append('<option value="">선택</option>');
				for (i=0; i<res.length; i++) {
	                $("#username").append('<option value="'+res[i].idx+'">'+ res[i].name +'</option>');
				}
				$("#btnSearch").prop("disabled", false);
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
        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");
        
        //console.log("회사 : " + companyId);
        //console.log("부서 : " + departmentId);
		
		var data = {
	        name: username,
	        email: email,
	        company: { id: companyId },
	        department: { id: departmentId}
    	};
		
		//등록 시작
	   $.ajax({
		url: "/employee/emCreate",
		cache : false,
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
		method: "POST",
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify(data),
		success : function(res){
			alert("등록되었습니다.");
			location.href = "/employee";
		},
		error : function(error) {
			console.log(error);
		} 
	   });
    });
	
	//사원 리스트 조회 버튼 
	
	$("#btnSearch").click(function(){
		//조회 조건 값 저장
		let companyId = $("#company option:selected").val();
		let departmentId = $("#department option:selected").val();
		let employeeId = $("#username option:selected").val();
		
		if(employeeId == ""){
			var url = '/employee/userList';
		} else {
			var url = '/employee/emList';
		}
			
		$('#employeeTable').DataTable({
			searching: false,
			paging: false,
			ordering: true,
			info: false,
			serverSide: true,
			processing: true,
			destroy: true,
			ajax: {
				url: url,
			     type: 'GET',
			     dataSrc: '',
				 data: function(d) {
				    d.company = companyId;
				    d.department = departmentId;
				    if(employeeId != ""){
						d.idx = employeeId;
					}
				}
			},
			columns: [
				{
				    data: null,
				    render: function (data, type, row) {
						return '<input type="checkbox">';
					}
				},
				{ data: 'company.cname' },
				{ data: 'department.dname' },
				{ data: 'name' },
		     	{ data: 'email' }
		 	],
		 	columnDefs: [ {
	            orderable: false,
	            className: 'select-checkbox',
	            targets:   0
	        } ],
	        select: {
	            style:    'os',
	            selector: 'td:first-child'
	        },
	        order: [[ 1, 'asc' ]]
		  	
		});
	});


	// 전체 사원 리스트
	$('#employeeTable').DataTable({
		searching: false,
		paging: false,
		ordering: true,
		info: false,
		serverSide: true,
		processing: true,
		destroy: true,
		ajax: {
			url: '/employee/List',
		     type: 'GET',
		     dataSrc: '',
		},
		columns: [
			{
			    data: null,
			    render: function (data, type, row) {
					return '<input type="checkbox">';
				}
			},
			{ data: 'company.cname' },
			{ data: 'department.dname' },
			{ data: 'name' },
	     	{ data: 'email' }
	 	],
	 	columnDefs: [ {
		        orderable: false,
		        className: 'select-checkbox',
		        targets:   0
		    } ],
		    select: {
		        style:    'os',
		        selector: 'td:first-child'
		    },
		    order: [[ 1, 'asc' ]]
  	
	});

	
	