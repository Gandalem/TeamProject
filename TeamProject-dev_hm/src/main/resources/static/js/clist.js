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
	//+버튼 클릭시 부서 빈테이블 추가
	$(document).ready(function() {
	var i=1; // 변수설정은 함수의 바깥에 설정!
  		$("#team_submit").click(function() {
    
   		$(".team_board").append("<input type='text' id='department-name' name='departmentName'>");
    
    	i++; // 함수 내 하단에 증가문 설정
    

  		});
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
	
	function getDepartmentsAndSaveCompany(row) {
    const companyId = $(row).data('id');


//	$(document).ready(function() {
	  // 게시글 목록 조회
//	  $.ajax({
//	    url: '/posts',
//	    method: 'GET',
//	    success: function(company) {
	      // 게시글 목록 출력
//	      for (let i = 0; i < company.length; i++) {
//	        $('#postList').append('<tr>'+'<td>' + company[i].cname + '</td>'+'</tr>');
//	      }
//	    }
//	  });
//	});
	
	$(document).ready(function() {
	    $('#create-department-form').submit(function(event) {
	        event.preventDefault();
	        var companyId = $('#company-id').val();
	        var departmentName = $('#department-name').val();
	        $.ajax({
	            type: 'POST',
	            url: '/clist/' + companyId + '/departments',
	            data: JSON.stringify({ 'dname': departmentName }),
	            contentType: 'application/json',
	            success: function(data) {
	                $('#result').html('Department created: ' + data.dname);
	            },
	            error: function(xhr, status, error) {
	                $('#result').html('Error: ' + error);
	            }
	        });
	    });
	});
	

    // 새로운 요청 보내기
    // 부서 정보 가져오기
     $.ajax({
        url: '/clist/getDepartments',
        cache : false,
        type: 'GET',
        data: { companyId: companyId },
        success: function(response) {
            const teamBoard = $('.company_team .team_board');
            let html = '';
            $.each(response, function(index, department) {
                html += '<div class="department_row">' + department.dname + '</div>';
            });
            teamBoard.html(html);
        },
        error: function(xhr, status, error) {
            console.log("등록된 부서가 없습니다"+error);
        }
    });


    // 회사 저장
    $("#save").off('click').on('click', function(){
        const dname = $('#department-name').val();
        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");

        if(dname.length === 0) {
            alert('데이터를 입력하셔야 합니다.');
        } else {
            $.ajax({
                url: '/clist/dsave',
                cache : false,
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    dname : dname,
                    company : companyId
                }),
                success: function(response) {
                    console.log(response); // 저장된 회사 부서 정보 출력
                },
                error: function(error) {
                    console.log(error);
                }
            });
        }
    });
}


/*
let currentRequest = null; // 현재 보낸 요청

function getDepartmentsAndSaveCompany(element) {
    const companyId = $(element).data('id');
    
    // 이전에 보낸 요청이 있다면 취소
    if (currentRequest) {
        currentRequest.abort();
    }
    
    // 새로운 요청 보내기
    currentRequest = $.ajax({
        url: '/clist/getDepartments',
        type: 'GET',
        data: { companyId: companyId },
        success: function(response) {
            const teamBoard = $('.company_team .team_board');
            teamBoard.empty();
            $.each(response, function(index, department) {
                const departmentRow = '<div class="department_row">' + department.dname + '</div>';
                teamBoard.append(departmentRow);
            });
        },
        error: function(xhr, status, error) {
            console.log(error);
        }
    });
}

// 회사 저장
$(document).ready(function() {
    $("#save").click(function(){
        const dname = $('#department-name').val();
        const companyId = $('#data-id').val();
        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");

        if(dname.length === 0) {
            alert('데이터를 입력하셔야 합니다.');
        } else {
            $.ajax({
                url: '/clist/dsave',
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    dname : dname,
                    company : companyId // 클릭한 element에서 data-id 값을 가져옴
                }),
                success: function(response) {
                    console.log(response); // 저장된 회사 부서 정보 출력
                },
                error: function(error) {
                    console.log(error);
                }
            });
        }
    });
});

*/
	
	
	
	  