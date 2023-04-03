
        var date; 


        // 현재시간 등록 스크립트
        function nDate() { 
            date = setInterval(function () { 
                var today = "현재 시간 :  "; 
                var newDate = new Date(); 
                

                today += newDate.getFullYear() + "년 "; 
                today += ("0" + (newDate.getMonth() + 1)).slice(-2) + "월 ";
                today += ("0" + newDate.getDate()).slice(-2) + "일 "; 
                today += (newDate.getHours() + "시 ");
                today += (newDate.getMinutes() + "분 ");
                today += (newDate.getSeconds() + "초")
                
                $("#date").text(today); 
            }, 1000); 
        } 


        // 출근 버튼 클릭시 현재 시간 함수 등록
        $("#gw").click(function() {
            var startTime = new Date().toLocaleString();    // 현재 시간을 저장
            localStorage.setItem("startTime", startTime);   // 저장된 시간을 로컬 스토리지에 저장
        });

        // 퇴근 버튼 클릭시 현재시간 함수 등록
        $("#lw").click(function() {
            var endTime = new Date().toLocaleString();    // 현재 시간을 저장
            localStorage.setItem("endTime", endTime);   // 저장된 시간을 로컬 스토리지에 저장
        });


        // 페이지 로드 후 실행
        $(document).ready(function() {
            nDate(); // 시간 출력 함수 실행

            var startsaved = localStorage.getItem("startTime"); // 저장된 시간을 불러옴
            if (startsaved) {
            $("#sgw").text("출근한 시간 : " + startsaved); // 저장된 시간 출력
            }
        });
        $(document).ready(function() {
            nDate(); // 시간 출력 함수 실행

            var endsaved = localStorage.getItem("endTime"); // 저장된 시간을 불러옴
            if (endsaved) {
            $("#slw").text("출근한 시간 : " + endsaved); // 저장된 시간 출력
            }
        });
