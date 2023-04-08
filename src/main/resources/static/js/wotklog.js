

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
			url: "/worklog/userList",
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
	//사원 리스트 조회 버튼 
	$("#btnSearch").click(function(){
		//조회 조건 값 저장
		let companyId = $("#company option:selected").val();
		let departmentId = $("#department option:selected").val();
		let worklogId = $("#name option:selected").val();
		
		if(employeeId == ""){
			var url = '/worklog/userList';
		} else {
			var url = '/worklog/worklogList';
		}
			
		$('#worklogTable').DataTable({
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
				    if(worklogId != ""){
						d.id = worklogId;
					}
				}
			},
			columns: [
				{
				    data: null,
				    render: function (data, type, row) {
						return '<input type="checkbox" name="check">';
					}
				},
				{ data: 'member.name' },
				{ data: 'title' },
				{ data: 'content' },
		     	{ data: 'createDate' }
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
 
	
	
	// 전체 근무일지 리스트
	var table = $('#worklogTable').DataTable({
		searching: false,
		paging: false,
		ordering: true,
		info: false,
		serverSide: true,
		processing: true,
		destroy: true,
		ajax:{
			url:'/worklog/List',
			type:'GET',
			dataSrc:'',
		},
		columns: [
			{
			    data: null,
			    render: function (data, type, row) {
					return '<input type="checkbox" name="check">';  
				}
			},
			{ data:'member.name'},
			{ data:'title'},
			{ data:'content'},
			{ data: 'createDate'}
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
	
	 // checkbox 전체 선택 이벤트
    $('#select_all').click(function(){
		var checked = $('#select_all').is(':checked');
		
		if(checked){
			$('input:checkbox').prop('checked', true);
		} else {
			$('input:checkbox').prop('checked', false);
		}
	});
	
	// 삭제 버튼 
	$('#btnDelete').click(function(){
		var rowData = new Array();
		var tdArr = new Array();
		var checkbox = $('input[name=check]:checked');
		const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");
		
		checkbox.each(function(i){
			
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			var email = td.eq(4).text();
			tdArr.push(email);
		});
				
		$.ajax({
	  	  url: "/worklog/worklogDelete",
	  	  beforeSend : function(xhr) {
	        	xhr.setRequestHeader(header, token);
	        },
	  	  method: "DELETE",
	  	  contentType: "application/json",
	  	  data: JSON.stringify(tdArr),
	  	  success : function(res){
				toastr.success('삭제되었습니다.');
				$('#work_LogsTable').DataTable().ajax.reload();
				
				//멤버 업데이트 (회사ID, 부서ID 삭제)
				
				$.ajax({
					url: "/signup/memberUpdate2",
					beforeSend : function(xhr) {
					    	xhr.setRequestHeader(header, token);
				    },
					method: "PUT",
					contentType: "application/json",
					dataType: "json",
					data: JSON.stringify(tdArr),
					success : function(res){ console("유저 정보 변경되었습니다.")},
					error : function(error){}
				});
			
			},
	  	  error : function(error){
				toastr.error('오류가 발생했습니다.');
			}
	    });
	});
	
	