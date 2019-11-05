<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html>
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">팝업(popup)창
                    <small>띄우기</small>
                </h1>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. 팝업 띄우기 </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. 탭 띄우기 </a>
                </div>
            </div>
            <div class="card" id="list1">
                <h3 class="panel-title text-center">1. 팝업 띄우기</h3>
                <div class="card-body">
                    <h5 class="card-title">새창으로 띄우기</h5>
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
MyCommon.goWindow('http://google.com');
</pre>
                    <h5 class="card-title">새창으로 띄우기(옵션추가)</h5>
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
MyCommon.goWindow('http://google.com', {
    height : 800,
    width : 1200
});
</pre>
                </div>
            </div>

            <div class="card" id="list2">
                <h3 class="panel-title text-center">2. 탭 띄우기</h3>
                <div class="card-body">
                    <h5 class="card-title">탭으로 띄우기</h5>
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
MyCommon.goTab('http://google.com');
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
            MyCommon.goWindow('http://google.com');
        }

        function try1_2() {
            MyCommon.goWindow('http://google.com', {
                height: 800,
                width: 1200
            });
        }

        function try2_1() {
            MyCommon.goTab('http://google.com');
        }
    </script>
</my:html>
