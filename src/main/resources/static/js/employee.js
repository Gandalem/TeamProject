// 등록 허용 여부 
var join = "ban";




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
			 $("#departmentModal").empty();
			 $("#departmentModal").append('<option value="">선택</option>');
			for (i=0; i<res.length; i++) {
	            $("#departmentModal").append('<option value="'+res[i].id+'">'+ res[i].dname +'</option>');
			}
		} 
	 });
	}
	
	
	// 모달 등록 버튼 이벤트
	 $("#btnSave").click(function(){
		if ( !$.trim($('#name').val()) ) {
	        toastr.warning('이름를 확인해 주세요.');
	        $('#name').focus();
	        return false;
	    } 
		 
		if ( !$.trim($('#email').val()) ) {
	        toastr.warning('이메일를 확인해 주세요.');
	        $('#email').focus();
	        return false;
	    }
	    
	    if (join == "ban") {
			toastr.warning('가입 확인을 해주세요.');
			$('#check-email').focus();
			return false;	
		}
		 
		// 등록할 값 추출 
        let companyId = $("#companyModal option:selected").val();
        let departmentId = $("#departmentModal option:selected").val();
        let username = $('input[name=name]').val(); 
        let email = $("#email").val();
        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");
        
        // 등록 데이터
		var data = {
	        name: username,
	        email: email,
	        company: { id: companyId },
	        department: { id: departmentId}
    	};
        
       	//사원 테이블에 중복된 이메일 검증
       	$.ajax({
		  url: "/employee/emailCheck",
		  cache : false,
          beforeSend : function(xhr) {
              xhr.setRequestHeader(header, token);
          },
		  method: "POST",
		  contentType: "application/json",
		  dataType: "json",
		  data: JSON.stringify({ email: $("#email").val() }),
		  success : function(res){
			  //등록 시작
			   $.ajax({
				url: "/employee/emCreate",
				cache : false,
		        beforeSend : function(xhr) {
		            xhr.setRequestHeader(header, token);
		        },
				method: "PUT",
				contentType: "application/json",
				dataType: "json",
				data: JSON.stringify(data),
				success : function(res){
					toastr.success('등록되었습니다.');
					table.ajax.reload();
					$('#exampleModal').modal("hide");
					
					//email을 기반으로 멤버 테이블 수정(회사, 부서 추가)
					/*
					$.ajax({
						url: "",
						beforeSend : function(xhr) {
			            	xhr.setRequestHeader(header, token);
				        },
						method: "POST",
						contentType: "application/json",
						dataType: "json",
						data: JSON.stringify(data),
						success : function(res){},
						error : function(error) {}
					}); */
					
				},
				error : function(error) {
					toastr.error("등록에 실패하였습니다.")
				} 
			   });
		  },
		  error : function(xhr){
			   if(xhr.status === 409) {
				   toastr.error('이미 등록된 사원입니다.');
			   }
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
						return '<input type="checkbox" name="check">';
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
	var table = $('#employeeTable').DataTable({
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
					return '<input type="checkbox" name="check">';
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

	
	//환민 ver. email checked 
	$("#check-email").click(function() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        
        var email = $("#email").val();
        $.ajax({
            type : "POST", // 요청 메서드를 POST로 변경
            url : "/signup/checkEmail",
            data : {	
                "email" : email
            },
            beforeSend : function(xhr){
                /* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
                xhr.setRequestHeader(header, token);
            },
            success : function(result) {
                if (result === "duplicate") {
                    $("#email-error").html("");
                    toastr.success('가입된 이메일입니다.');
                    join = "permit";
                } else {
                    $("#email-error").html("");
                    toastr.warning('가입되지 않은 이메일입니다.');
                    join = "ban";
                }
            },
            error : function(e) {
                console.log("이메일 중복 확인 과정에서 에러가 발생했습니다: " + e);
            }
        });
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
	
	//체크박스
	//var checked = $('input[name=check]:checked');
	

	//$("#btnDelete").prop("disabled", false);
	
	
	
	// 삭제 버튼 
	$('#btnDelete').click(function(){
		var rowData = new Array();
		var tdArr = new Array();
		var checkbox = $('input[name=check]:checked');
		
		checkbox.each(function(i){
			
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			rowData.push(tr.text());
			
			var email = td.eq(4).text();
			tdArr.push(email);
		});
		
		console.log("삭제할 데이터 행 : " + tdArr);
		
		
		
		
	});
        
        
        
        
        