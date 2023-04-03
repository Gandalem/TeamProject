	
	
	var companyId = null;	
	
	//회사 목록
	var table = $('#companyTable').DataTable({
		serverSide: true,
		processing: true,
		destroy: true,
		ajax: {
	    url: '/clist/posts',
		    type: 'GET',
		    dataSrc: ''
		},
		columns: [
		    { data: 'cname' }
		],
		columnDefs: [{
		   defaultContent: '-',
		   targets: '_all',
		   className: 'dt-body-center'
		}]
	 
	});
	     
	     
	     
	     //회사 목록 클릭 이벤트
	 $('#companyTable tbody').on('click', 'tr', function() {
	    // 선택한 행에 .selected 클래스 추가
	    if ($(this).hasClass('selected')) {
	        $(this).removeClass('selected');
	    } else {
	        table.$('tr.selected').removeClass('selected');
	        $(this).addClass('selected');
	        companyId = table.row(this).data().id;
	        console.log('회사 Id', companyId);
	        $('#departmentTable').DataTable({
			serverSide: true,
			processing: true,
			destroy: true,
			ajax: {
				url: '/clist/getDepartments',
			     type: 'GET',
			     dataSrc: '',
				 data: function(d) {
				    d.companyId = companyId;
				}
			},
			columns: [
		     	{ data: 'dname' }
		 	],
		 	columnDefs: [{
			    defaultContent: '데이터가 없습니다.',
			    targets: '_all',
			    className: 'dt-body-center'
		 	}]
		  	
		});
	        $('#departmentTable').DataTable().ajax.reload();
	        
	    }
	    
	});
	
	
	//부서 목록    
	 var departmentTable = 
	 $('#departmentTable').DataTable({
		serverSide: true,
		processing: true,
		destroy: true,
		ajax: {
			url: '/clist/getDepartments_null',
		     type: 'GET',
		     dataSrc: '',
			 data: function(d) {
			    
			}
		},
		columns: [
	     	{ data: 'dname' }
	 	],
	 	columnDefs: [{
		    defaultContent: '데이터가 없습니다.',
		    targets: '_all',
		    className: 'dt-body-center'
	 	}]
	  	
	});
	
	//부서 목록 행 추가
	$("#team_submit").click(function() {
		var input = '<div style="text-align: center;"><input type="text" name="departmentName" value=""><div/>';
		$('#departmentTable > tbody:last').append(input);
	});
	
	
	//모달창 on
	$("#company_submit").click(function(){
        $("#modal").attr("style", "display:block");
		
    });
   //모달창 off 및 값 초기화
     $("#modal_close_btn").click(function(){
        $("#modal").attr("style", "display:none");
  		$("#cname").val('');
  		$("#caddr").val('');
  		$("#cnumber").val('');
  		$("#number").val('');
  		$("#caddr_detail").val('');
  		$("#name").val('');
    });
	  
	$(document).ready(function() {
	$("#company_save").click(function() {
		
		// 회사 정보 입력 값 가져오기
		var name = $('#name').val();
		var cname = $('#cname').val();
		var address = $('#caddr').val() + ' ' + $('#caddr_detail').val();
		var cnumber = $('#cnumber').val();
		var number = $('#number').val();
		var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");




		// 입력값이 빈 경우 알림창 띄우기
		if (cname.length === 0 || address.length === 0 || cnumber.length === 0 || number.length === 0) {
			alert('데이터를 입력하셔야 합니다.');
		} else {
			// ajax 요청
			$.ajax({
				url: '/clist/save',

				 beforeSend : function(xhr) {
		             xhr.setRequestHeader(header, token);
		            },

				cache : false,

				 beforeSend : function(xhr) {
		             xhr.setRequestHeader(header, token);
		            },

				beforeSend : function(xhr) {
               xhr.setRequestHeader(header, token);
            	},


				method: 'POST',
				contentType: 'application/json',
				data: JSON.stringify({
					name: name,
					cname: cname,
					address: address,
					cn: cnumber,
					number: number
				}),
				success: function(response) {
					console.log(response); // 저장된 회사 정보 출력
					$('#companyTable').DataTable().ajax.reload();
				},
				error: function(error) {
					console.log(error);
				}
			});
			
			// 회사 정보 목록에 추가하기
			//$("#postList").append("<tr>"+"<td>"+cname+"</td>"+"</tr>");
			
			// 입력값 초기화
			$("#cname, #caddr, #cnumber, #number, #caddr_detail, #name").val('');
			
			// 모달 닫기
			$("#modal").attr("style", "display:none");
				}
			});
		});
	//주소 api
	$(document).ready(function() {
		$("#caddr").click(function() {
			
			new daum.Postcode({
				oncomplete: function(data) {
				$("#caddr").val(data.address);
				$("input[name=caddr_detail]").focus();
				}
			}).open();
		});
	});
	
    // 부서 저장
    $("#save").off('click').on('click', function(){
        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");
            $.ajax({
                url: '/clist/dsave',
                cache : false,
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    dname : $('input[name=departmentName]').val(),
                    company : companyId
                }),
                success: function(response) {
                    console.log(response); // 저장된 회사 부서 정보 출력
                    $('#departmentTable').DataTable().ajax.reload();
                    
                },
                error: function(error) {
                    console.log(error);
                }
            });
        });
	

	
	
 


     
	
	  