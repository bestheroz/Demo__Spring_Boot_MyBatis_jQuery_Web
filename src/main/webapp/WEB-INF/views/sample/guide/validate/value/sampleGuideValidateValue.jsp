<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html validator="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-5">값 검증하기
                    <small>(숫자값, 이메일값, URL값, IP값 등)</small>
                </h1>
                <hr class="my-4">
                <h4>required</h4>
                <pre class="prettyprint lang-html">&lt;my:html validator="YES"&gt;</pre>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. 숫자값 검증 </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. 이메일값 검증 </a>
                    <a href="#list3" class="list-group-item list-group-item-action list-group-item-light"> 3. URL값 검증 </a>
                    <a href="#list4" class="list-group-item list-group-item-action list-group-item-light"> 4. IP값 검증 </a>
                    <a href="#list5" class="list-group-item list-group-item-action list-group-item-light"> 5. 기타값 검증 </a>
                </div>
            </div>
            <div class="card" id="list1">
                <h3 class="panel-title text-center">1. 숫자값 검증</h3>
                <div class="card-body">
                    <input type="text" placeholder="숫자값 검증" id="inputTry1_1"/>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try1_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-js">
jQuery.isNumeric(value);
</pre>
                </div>
            </div>
            <div class="card" id="list2">
                <h3 class="panel-title text-center">2. 이메일값 검증</h3>
                <div class="card-body">
                    <input type="text" placeholder="이메일값 검증" id="inputTry2_1"/>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try2_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry2_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-js">
validator.isEmail(value);
</pre>
                </div>
            </div>

            <div class="card" id="list3">
                <h3 class="panel-title text-center">3. URL값 검증</h3>
                <div class="card-body">
                    <input type="text" placeholder="URL값 검증" id="inputTry3_1"/>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try3_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry3_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-js">
validator.isURL(value);
</pre>
                </div>
            </div>

            <div class="card" id="list4">
                <h3 class="panel-title text-center">4. IP값 검증</h3>
                <div class="card-body">
                    <input type="text" placeholder="IP값 검증" id="inputTry4_1"/>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try4_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry4_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-js">
validator.isIP(value);
</pre>
                </div>
            </div>

            <div class="card" id="list5">
                <h3 class="panel-title text-center">5. 기타값 검증</h3>
            </div>
            <div class="card-body">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <button class="btn btn-outline-primary" type="button" onclick="try5_1()">
                            <i class="far fa-play-circle"></i>
                            Demo
                        </button>
                    </div>
                    <input type="text" class="form-control" id="resultTry5_1" placeholder="Demo를 누르시면 검증 plugin URL로 링크됩니다." readonly/>
                </div>
                <div id="list6"></div>
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
            $('#resultTry1_1').val(_.isNumber($('#inputTry1_1').val()));
        }

        function try2_1() {
            $('#resultTry2_1').val(validator.isEmail($('#inputTry2_1').val()));
        }

        function try3_1() {
            $('#resultTry3_1').val(validator.isURL($('#inputTry3_1').val()));
        }

        function try4_1() {
            $('#resultTry4_1').val(validator.isIP($('#inputTry4_1').val()));
        }

        function try5_1() {
            MyCommon.goTab('https://lodash.com/docs/4.17.4#isNumber');
            MyCommon.goTab('https://github.com/chriso/validator.js#validators');
        }
    </script>
</my:html>
