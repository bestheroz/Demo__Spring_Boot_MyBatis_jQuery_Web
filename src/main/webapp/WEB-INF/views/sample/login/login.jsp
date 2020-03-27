<%@ page session="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html cookie="YES" validator="YES">
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <form class="form-signin">
                <h1 class="h3 mt-5 mb-3 font-weight-normal">Bestheroz's Spring-Boot-Mybatis-jQuery Web Project
                    <small>ver.200327</small>
                </h1>
                <label for="memberId" class="sr-only">아이디</label>
                <input type="email" class="form-control" id="memberId" title="아이디" placeholder="아이디를 입력하세요." maxlength="9" required autofocus>
                <label for="memberPw" class="sr-only">Password</label>
                <input type="password" class="form-control" id="memberPw" title="비밀번호" placeholder="비밀번호를 입력하세요." maxlength="20" required>
                <div class="checkbox mb-3">
                    <button type="button" class="btn btn-default" onclick="clickSaveId();">
                        <i class="far fa-check-square" id="save_id" title="ID 저장"></i>
                        ID 저장
                    </button>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="button" onclick="proc();"><i class="fas fa-sign-in-alt"></i>
                    로그인
                </button>
                <div class="col-offset-2" style="margin-top: 10px;">
                    <p class="text-muted text-center" style="font-size: 16px;">
                        테스트 [ ID / Password ] => [
                        <strong>1 / 1</strong>
                        ] 입니다.
                    </p>
                </div>
                <p class="mt-5 mb-3 text-muted">&copy; 2016-2019</p>
            </form>
        </div>
    </main>
    <my:footer/>
    <script data-for="ready">
        jQuery(($) => {
            $('#memberPw, #memberId').on('keydown', (event) => {
                if (event.key === 'Enter') {
                    proc();
                }
            });
            const savedLoginId = MyCookie.getCookie("savedLoginId");
            if (savedLoginId !== null) {
                $('#memberId').val(savedLoginId);
                $('#save_id').removeClass('fa-square').addClass('fa-check-square');
            }
        });
    </script>
    <script>
        async function proc() {
            if (MyValidator.validate($('form.form-horizontal'), true) !== null) {
                return;
            }
            const param = {
                memberId: $('#memberId').val(),
                memberPw: CryptoJS.SHA512($('#memberPw').val()).toString()
            };
            const response = await MyAjax.execute('${CONTEXT_PATH}/sample/login/loginProcess.json', param, {
                autoResultFunctionTF: false,
                type: "POST"
            });
            if (!_.startsWith(response.responseCode, 'S')) {
                alert(response.responseMessage);
                return;
            }
            if ($('#save_id').hasClass('fa-check-square')) {
                MyCookie.setCookie("savedLoginId", $('#memberId').val(), 30);
            } else {
                MyCookie.removeCookie("savedLoginId");
            }
            window.location.reload();
        }

        function clickSaveId() {
            if ($('#save_id').hasClass('fa-check-square')) {
                $('#save_id').addClass('fa-square').removeClass('fa-check-square');
            } else {
                $('#save_id').removeClass('fa-square').addClass('fa-check-square');
            }
        }
    </script>
    <script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js"></script>

</my:html>
