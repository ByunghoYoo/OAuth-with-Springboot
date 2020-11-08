// var main = {...} 선언 이유
// var init = function() {...} 이렇게 작성하게 되면, 나중에 로딩된 다른 js 의 init 으로 덮여쓰여질 수 있으므로
// 여러 사람이 참여하는 프로젝트에서는 중복된 함수 이름은 자주 발생할 수 있음
// 이런 문제를 피하기 위해 index.js 만의 유효범위(scope)를 만들어 사용

// JSON.stringify(data) : Json 객체를 String 객체로 변환
// type: 'PUT' : 여러 HTTP Method 중 PUT 매소드를 선택
//               PostsApiController 에 있는 API 에서 이미 @PutMapping 으로 선언했기 때문에 PUT 을 사용해야 함 (REST 규약 의거)
// REST 에서 CRUD 는 다음과 같이 HTTP Method 에 매핑
// 생성(Create) - POST
// 읽기(Read) - GET
// 수정(Update) - PUT
// 삭제(Delete) - DELETE

var main = {
    init : function() {
        var _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });

        $('#btn-update').on('click', function() {
            _this.update();
        });

        $('#btn-delete').on('click', function() {
            _this.delete();
        });
    },
    save : function() {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=urf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    update : function() {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function() {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();