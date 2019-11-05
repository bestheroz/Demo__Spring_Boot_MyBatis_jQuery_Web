<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html>
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">
                    페이지 이동
                    <small>(a href)</small>
                </h1>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. 페이지 이동 </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. &lt;a href="url"&gt; </a>
                </div>
            </div>
            <div class="card" id="list1">
                <h3 class="panel-title text-center">1. 페이지 이동</h3>
                <div class="card-body">
                    <h5 class="card-title">내부 링크</h5>
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
MyCommon.goLink('/sample/guide/movepage/sampleGuideMovepage.view');
</pre>
                    <h5 class="card-title">외부 링크(외부 링크는 기본적으로 탭으로 뜹니다.)</h5>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try1_2()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry1_2" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-js">
MyCommon.goLink('http://google.com');
</pre>
                    <h5 class="card-title">팝업으로 띄우기</h5>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try1_3()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry1_3" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-js">
MyCommon.goWindow('/sample/guide/movepage/sampleGuideMovepage.view');
MyCommon.goLink('/sample/guide/movepage/sampleGuideMovepage.view', 'W');
MyCommon.goLink('http://google.com', 'W');
</pre>
                    <h5 class="card-title">외부 링크(페이지 이동)</h5>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try1_4()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry1_4" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-js">
MyCommon.goLink('http://google.com', 'L');
</pre>
                </div>
            </div>

            <div class="card" id="list2">
                <h3 class="panel-title text-center">2. &lt;a href="url"&gt;</h3>
                <div class="card-body">
                    <h5 class="card-title">&lt;a href&gt; 사용을 지양합니다.</h5>
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
&lt;a href="javascript:void(0);" onclick="MyCommon.goLink('http://google.com');" /&gt;
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
            MyCommon.goLink('${CONTEXT_PATH}/sample/guide/movepage/sampleGuideMovepage.view');
        }

        function try1_2() {
            MyCommon.goLink('http://google.com');
        }

        function try1_3() {
            MyCommon.goLink('http://google.com', 'W');
        }

        function try1_4() {
            MyCommon.goLink('http://google.com', 'L');
        }

        function try2_1() {
            MyCommon.goLink('http://google.com');
        }
    </script>
</my:html>
