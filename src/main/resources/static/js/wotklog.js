

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
	function department() {
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
			success: function(res) {
				$("#username").empty();
				$("#username").append('<option value="">선택</option>');
				for (i = 0; i < res.length; i++) {
					$("#username").append('<option value="' + res[i].id + '">' + res[i].name + '</option>');
				}
				$("#btnSearch").prop("disabled", false);
			}
		});
	}

	$("#btnSearch").click(function() {
    let companyId = $("#company option:selected").val();
    let departmentId = $("#department option:selected").val();
    let memberId = $("#username option:selected").val();

    var url = "/worklog/worklogList";

    $("#worklogTable").DataTable({
        searching: false,
        paging: false,
        ordering: true,
        info: false,
        serverSide: true,
        processing: true,
        destroy: true,
        ajax: {
            url: url,
            type: "GET",
            dataSrc: "data",
            data: function(d) {
                d.company = companyId;
                d.department = departmentId;
                d.memberId = memberId;
            },
        },
        columns: [
            {
                data: null,
                render: function(data, type, row) {
                    return '<input type="checkbox" name="check">';
                },
            },
            { data: "memberName" },
            { data: "title" },
            { data: "content" },
            { data: "createDate" },
        ],
        columnDefs: [
            {
                orderable: false,
                className: "select-checkbox",
                targets: 0,
            },
        ],
        select: {
            style: "os",
            selector: "td:first-child",
        },
        order: [[1, "asc"]],
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
        { data: 'memberName'},
        { data: 'title',
        	render: function(data, type, row) {
    			return '<a href="/worklog/worklog_detail/' + row.id + '">' + data + '</a>';


        	}
        },
        { data: 'content'},
        { 
            data: 'createDate',
            render: function (data, type, row) {
                var date = new Date(data);
                var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2) + ' ' + ('0' + date.getHours()).slice(-2) + ':' + ('0' + date.getMinutes()).slice(-2);
                return formattedDate;
            }
        }
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
	
	// 체크박스가 체크되면 삭제 버튼 활성화
	$(document).on('change', 'input[name="check"]', function() {
	    var checkbox = $('input[name=check]:checked');
	    if (checkbox.length > 0) {
	        $('button.btn-warning').prop('disabled', false);
	    } else {
	        $('button.btn-warning').prop('disabled', true);
	    }
	});
	
	// 삭제 버튼 클릭 시 처리
	$('#btnDelete').click(function() {
    var rowData = new Array();
    var tdArr = new Array();
    var checkbox = $('input[name=check]:checked');
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");

    checkbox.each(function(i) {
        var tr = checkbox.parent().parent().eq(i);
        var workLogId = table.row(tr).data().id;
        tdArr.push(workLogId);
    });

	    $.ajax({
	        url: "/worklog/worklogDelete",
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader(header, token);
	        },
	        method: "DELETE",
	        contentType: "application/json",
	        data: JSON.stringify(tdArr),
	        success: function(res) {
	   			 if (res.status === 'success') {
	        		toastr.success('삭제되었습니다.');
	       			 window.location.href = "/worklog";
	   			 } else {
			        toastr.error('오류가 발생했습니다.');
			    }
				},
				error: function(error) {
				    toastr.error('오류가 발생했습니다.');
				}
			
		});
	});


	
	