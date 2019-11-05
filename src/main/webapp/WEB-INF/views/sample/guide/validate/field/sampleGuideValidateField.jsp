<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html validator="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">필드 검증하기
                    <small>(필수값 등)</small>
                </h1>
                <hr class="my-4">
                <h4>required</h4>
                <pre class="prettyprint lang-html">&lt;my:html validator="YES"&gt;</pre>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. 필수값 검증 </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. 다양한 입력값 검증 </a>
                    <a href="#list3" class="list-group-item list-group-item-action list-group-item-light"> 3. 최소값/최대값 검증 </a>
                    <a href="#list4" class="list-group-item list-group-item-action list-group-item-light"> 4. 최소길이/최대길이 검증 </a>
                    <a href="#list5" class="list-group-item list-group-item-action list-group-item-light"> 5. 사용자 메시지 설정 </a>
                    <a href="#list6" class="list-group-item list-group-item-action list-group-item-light"> 6. 최소/최대 체크 수 검증 </a>
                    <a href="#list7" class="list-group-item list-group-item-action list-group-item-light"> 7. 검증 항목에서 제외하기 </a>
                </div>
            </div>
            <div class="card" id="list1">
                <h3 class="panel-title text-center">1. 필수값 검증</h3>
                <div class="card-body">
                    <form id="form_try1_1">
                        <input type="text" title="input1" placeholder="input1" required/>
                        <input type="text" title="input2" placeholder="input2" required/>
                        <select title="select1" required>
                            <option value="">select1</option>
                            <option value="1">1번입니다.</option>
                            <option value="2">2번입니다.</option>
                            <option value="3">3번입니다.</option>
                        </select>
                        <textarea title="textarea1" required></textarea>
                        <input type="checkbox" title="checkbox1" name="checkbox1" required/>
                        <input type="checkbox" title="checkbox1" name="checkbox1" required/>
                        <input type="radio" title="radio1" name="radio1" required/>
                        <input type="radio" title="radio1" name="radio1" required/>
                    </form>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try1_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-html">
&lt;input type="text" title="input1" placeholder="input1" required /&gt;
&lt;input type="text" title="input2" placeholder="input2" required /&gt;
&lt;select title="select1" required&gt;
    &lt;option value=""&gt;select1&lt;/option&gt;
    &lt;option value="1"&gt;1번입니다.&lt;/option&gt;
    &lt;option value="2"&gt;2번입니다.&lt;/option&gt;
    &lt;option value="3"&gt;3번입니다.&lt;/option&gt;
&lt;/select&gt;
&lt;textarea title="textarea1" required&gt;&lt;/textarea&gt;
&lt;input type="checkbox" title="checkbox1" name="checkbox1" required /&gt;
&lt;input type="checkbox" title="checkbox1" name="checkbox1" required /&gt;
&lt;input type="radio" title="radio1" name="radio1" required /&gt;
&lt;input type="radio" title="radio1" name="radio1" required /&gt;</pre>
                    <pre class="prettyprint lang-js">
if (MyValidator.validate($('#form_try1_1')) !== null) {
    return;
}
</pre>
                </div>
            </div>

            <div class="card" id="list2">
                <h3 class="panel-title text-center">2. 다양한 입력값 검증</h3>
                <div class="card-body">
                    <form id="form_try2_1">
                        <input type="email" title="이메일1" placeholder="이메일1" value="test@test.com"/>
                        <input type="text" title="이메일2" placeholder="이메일2" data-parsley-type="email" value="test@test.com"/>
                        <input type="text" title="정수1" placeholder="정수1" data-parsley-type="integer" value="3"/>
                        <input type="text" title="URL" placeholder="URL" data-parsley-type="url" value="http://google.com"/>
                    </form>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try2_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry2_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-html">
&lt;form id="form_try2_1"&gt;
    &lt;input type="email" title="이메일1" placeholder="이메일1" /&gt;
    &lt;input type="text" title="이메일2" placeholder="이메일2" data-parsley-type="email" /&gt;
    &lt;input type="text" title="정수1" placeholder="정수1" data-parsley-type="integer" /&gt;
    &lt;input type="text" title="URL" placeholder="URL" data-parsley-type="url" /&gt;
&lt;/form&gt;</pre>
                    <pre class="prettyprint lang-js">
if (MyValidator.validate($('#form_try2_1')) !== null) {
    return;
}
</pre>
                </div>
            </div>

            <div class="card" id="list3">
                <h3 class="panel-title text-center">3. 최소값/최대값 검증</h3>
                <div class="card-body">
                    <form id="form_try3_1">
                        <input type="text" title="최소최대값필드(100~500)" placeholder="100 ~ 500" data-parsley-type="integer" min="100" max="500" value="77"/>
                    </form>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try3_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry3_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-html">
&lt;form id="form_try3_1"&gt;
    &lt;input type="text" title="최소최대값필드(100~500)" placeholder="100 ~ 500" data-parsley-type="integer" min="100" max="500" /&gt;
&lt;/form&gt;</pre>
                    <pre class="prettyprint lang-js">
if (MyValidator.validate($('#form_try3_1')) !== null) {
    return;
}
</pre>
                </div>
            </div>

            <div class="card" id="list4">
                <h3 class="panel-title text-center">4. 최소길이/최대길이 검증</h3>
                <div class="card-body">
                    <form id="form_try4_1">
                        <input type="text" title="최소최대길이필드(2~5)" placeholder="2자리 ~ 5자리" data-parsley-minlength="2" maxlength="5" value="a"/>
                    </form>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try4_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry4_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-html">
&lt;form id="form_try4_1"&gt;
    &lt;input type="text" title="최소최대길이필드(2~5)" placeholder="2자리 ~ 5자리" data-parsley-minlength="2" maxlength="5" /&gt;
&lt;/form&gt;</pre>
                    <pre class="prettyprint lang-js">
if (MyValidator.validate($('#form_try4_1')) !== null) {
    return;
}
</pre>
                </div>
            </div>

            <div class="card" id="list5">
                <h3 class="panel-title text-center">5. 사용자 메시지 설정</h3>
                <div class="card-body">
                    <form id="form_try5_1">
                        <input type="text" title="입력1" placeholder="입력1" data-valid-message="사용자 메시지로 설정하였습니다." required/>
                    </form>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try5_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry5_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-html">
&lt;form id="form_try5_1"&gt;
    &lt;input type="text" title="입력1" placeholder="입력1" data-valid-message="사용자 메시지로 설정하였습니다." required /&gt;
&lt;/form&gt;</pre>
                    <pre class="prettyprint lang-js">
if (MyValidator.validate($('#form_try5_1')) !== null) {
    return;
}
</pre>
                </div>
            </div>
            <div class="card" id="list6">
                <h3 class="panel-title text-center">6. 최소/최대 체크 수 검증</h3>
                <div class="card-body">
                    <form id="form_try6_1">
                        <input type="checkbox" title="checkbox1" name="checkbox1" required data-parsley-mincheck="2" data-parsley-maxcheck="3"/>
                        <input type="checkbox" title="checkbox1" name="checkbox1" required/>
                        <input type="checkbox" title="checkbox1" name="checkbox1" required/>
                        <input type="checkbox" title="checkbox1" name="checkbox1" required/>
                        <input type="checkbox" title="checkbox1" name="checkbox1" required/>
                        <input type="checkbox" title="checkbox1" name="checkbox1" required/>
                    </form>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try6_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry6_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-html">
&lt;form id="form_try6_1"&gt;
    &lt;input type="checkbox" title="checkbox1" name="checkbox1" required data-parsley-mincheck="2" data-parsley-maxcheck="3" /&gt;
    &lt;input type="checkbox" title="checkbox1" name="checkbox1" required /&gt;
    &lt;input type="checkbox" title="checkbox1" name="checkbox1" required /&gt;
    &lt;input type="checkbox" title="checkbox1" name="checkbox1" required /&gt;
    &lt;input type="checkbox" title="checkbox1" name="checkbox1" required /&gt;
    &lt;input type="checkbox" title="checkbox1" name="checkbox1" required /&gt;
&lt;/form&gt;</pre>
                    <pre class="prettyprint lang-js">
if (MyValidator.validate($('#form_try6_1')) !== null) {
    return;
}
</pre>
                </div>
            </div>

            <div class="card" id="list7">
                <h3 class="panel-title text-center">7. 검증 항목에서 제외하기</h3>
                <div class="card-body">
                    <form id="form_try7_1">
                        <input type="text" title="input1" placeholder="input1" data-parsley-excluded="true" required/>
                        <input type="text" title="input2" placeholder="input2" data-parsley-excluded="true" required/>
                        <select title="select1" data-parsley-excluded="true" required>
                            <option value="">select1</option>
                            <option value="1">1번입니다.</option>
                            <option value="2">2번입니다.</option>
                            <option value="3">3번입니다.</option>
                        </select>
                        <textarea title="textarea1" data-parsley-excluded="true" required></textarea>
                        <input type="checkbox" title="checkbox1" name="checkbox1" data-parsley-excluded="true" required/>
                        <input type="checkbox" title="checkbox1" name="checkbox1" data-parsley-excluded="true" required/>
                        <input type="radio" title="radio1" name="radio1" data-parsley-excluded="true" required/>
                        <input type="radio" title="radio1" name="radio1" data-parsley-excluded="true" required/>
                    </form>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try7_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry7_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-html">
&lt;input type="text" title="input1" placeholder="input1" data-parsley-excluded="true" required /&gt;
&lt;input type="text" title="input2" placeholder="input2" data-parsley-excluded="true" required /&gt;
&lt;select title="select1" data-parsley-excluded="true" required&gt;
    &lt;option value=""&gt;select1&lt;/option&gt;
    &lt;option value="1"&gt;1번입니다.&lt;/option&gt;
    &lt;option value="2"&gt;2번입니다.&lt;/option&gt;
    &lt;option value="3"&gt;3번입니다.&lt;/option&gt;
&lt;/select&gt;
&lt;textarea title="textarea1" data-parsley-excluded="true" required&gt;&lt;/textarea&gt;
&lt;input type="checkbox" title="checkbox1" name="checkbox1" data-parsley-excluded="true" required /&gt;
&lt;input type="checkbox" title="checkbox1" name="checkbox1" data-parsley-excluded="true" required /&gt;
&lt;input type="radio" title="radio1" name="radio1" data-parsley-excluded="true" required /&gt;
&lt;input type="radio" title="radio1" name="radio1" data-parsley-excluded="true" required /&gt;</pre>
                    <pre class="prettyprint lang-js">
if (MyValidator.validate($('#form_try7_1')) !== null) {
    return;
}
</pre>
                </div>
            </div>
        </div>
    </main>
    <my:footer/>
    <script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
    <script data-for="ready">
        jQuery(($) => {
        });
    </script>
    <script>
        function try1_1() {
            if (MyValidator.validate($('#form_try1_1')) !== null) {
                return;
            }
            $('#resultTry1_1').val("Pass");
        }

        function try2_1() {
            if (MyValidator.validate($('#form_try2_1')) !== null) {
                return;
            }
            $('#resultTry2_1').val("Pass");
        }

        function try3_1() {
            if (MyValidator.validate($('#form_try3_1')) !== null) {
                return;
            }
            $('#resultTry3_1').val("Pass");
        }

        function try4_1() {
            if (MyValidator.validate($('#form_try4_1')) !== null) {
                return;
            }
            $('#resultTry4_1').val("Pass");
        }

        function try5_1() {
            if (MyValidator.validate($('#form_try5_1')) !== null) {
                return;
            }
            $('#resultTry5_1').val("Pass");
        }

        function try6_1() {
            if (MyValidator.validate($('#form_try6_1')) !== null) {
                return;
            }
            $('#resultTry6_1').val("Pass");
        }

        function try7_1() {
            if (MyValidator.validate($('#form_try7_1')) !== null) {
                return;
            }
            $('#resultTry7_1').val("Pass");
        }
    </script>
</my:html>
