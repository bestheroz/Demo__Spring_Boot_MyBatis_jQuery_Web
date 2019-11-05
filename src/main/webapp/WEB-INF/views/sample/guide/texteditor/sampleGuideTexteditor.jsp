<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html textEditor="YES" modal="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">텍스트 에디터
                    <small>Text Editor</small>
                </h1>
                <hr class="my-4">
                <h4>목차</h4>
                <hr class="my-4">
                <h4>required</h4>
                <pre class="prettyprint lang-html">&lt;my:html textedtior="YES"&gt;</pre>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. 자동/수동 생성 </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. get/set code </a>
                    <a href="#list3" class="list-group-item list-group-item-action list-group-item-light"> 3. 수정/읽기 모드 </a>
                    <a href="#list4" class="list-group-item list-group-item-action list-group-item-light"> 4. Air 모드 </a>
                    <a href="#list5" class="list-group-item list-group-item-action list-group-item-light"> 5. 기타 </a>
                </div>
            </div>
            <div class="card" id="list1">
                <h3 class="panel-title text-center">1. 자동/수동 생성</h3>
                <div class="card-body">
                    <div class="well">
                        <p>textarea로 하지 않는 이유</p>
                        <p>1. 화면에 텍스트에디터 구성전 화면에 잔상 때문..</p>
                        <p>2. 수정모드, 읽기모드 전환이 매우 간단!</p>
                    </div>
                    <p>자동 : div attribute -> data-texteditor="on" 사용</p>
                    <p>data-texteditor-height="50"을 사용하여 높이 조절(기본값 200)</p>
                    <pre class="prettyprint lang-html">
&lt;div id="textarea1_1" data-texteditor="on" data-texteditor-height="50"&gt;&lt;/div&gt;
</pre>
                    <div id="textarea1_1" data-texteditor="on" data-texteditor-height="50"></div>

                    <p>수동 : MyTextEditor.instance($('#textarea1_2')); 사용</p>
                    <pre class="prettyprint lang-html">
&lt;div id="textarea1_2"&gt;&lt;/div&gt;</pre>
                    <pre class="prettyprint lang-js">
MyTextEditor.instance($('#textarea1_2'));
MyTextEditor.instance($('#textarea1_2'), {height : '50px'});
</pre>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try1_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <div id="textarea1_2">textarea1_2 textarea1_2 textarea1_2</div>
                    </div>
                </div>
                <div class="card" id="list2">
                    <h3 class="panel-title text-center">2. get/set code</h3>
                    <div class="card-body">
                        <p>1. 값 가져오기(get)</p>
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
MyTextEditor.getCode($('#textarea2'));
</pre>
                        <p>2. 값 입력하기(set)</p>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <button class="btn btn-outline-primary" type="button" onclick="try2_2()">
                                    <i class="far fa-play-circle"></i>
                                    Demo
                                </button>
                            </div>
                            <input type="text" class="form-control" id="resultTry2_2" placeholder="result..." value="set Value">
                        </div>
                        <pre class="prettyprint lang-js">
MyTextEditor.setCode($('#textarea2'), 'set Value');
</pre>
                        <div id="textarea2" data-texteditor="on" data-texteditor-height="50"></div>
                    </div>
                </div>
                <div class="card" id="list3">
                    <h3 class="panel-title text-center">3. 수정/읽기 모드</h3>
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <button class="btn btn-outline-primary" type="button" onclick="try3_1()">
                                    <i class="far fa-play-circle"></i>
                                    Demo(disable true)
                                </button>
                                <button class="btn btn-outline-primary" type="button" onclick="try3_2()">
                                    <i class="far fa-play-circle"></i>
                                    Demo(disable false)
                                </button>
                            </div>
                        </div>
                        <pre class="prettyprint lang-js">
MyTextEditor.disable($('#textarea3'), true); // 읽기모드로 전환
MyTextEditor.disable($('#textarea3'), false); // 수정모드로 전환
</pre>
                        <div id="textarea3" data-texteditor="on" data-texteditor-height="50">textarea3 textarea3 textarea3</div>
                    </div>
                </div>
                <div class="card" id="list4">
                    <h3 class="panel-title text-center">4. Air 모드</h3>
                    <div class="card-body">
				<pre class="prettyprint lang-html">
//자동
&lt;div id="textarea4" data-texteditor="on" data-texteditor-height="50" data-texteditor-airmode="true"&gt;&lt;/div&gt;</pre>
                        <pre class="prettyprint lang-js">
//수동
MyTextEditor.instance($('#textarea4'), {
    airMode: true
});
</pre>
                        <div id="textarea4" data-texteditor="on" data-texteditor-height="50" data-texteditor-airmode="true">textarea4 textarea4 textarea4</div>
                    </div>
                </div>
                <div class="card" id="list5">
                    <h3 class="panel-title text-center">5. 기타</h3>
                    <div class="card-body">
				<pre class="prettyprint lang-js">
MyTextEditor.isEmpty($('#textarea4')) // 빈값 검증 시에 사용
MyTextEditor.focus($('#textarea4')) // 빈값 검증 시후 fucos 이동 시
MyTextEditor.reset($('#textarea4')) // 새로고침
</pre>
                    </div>
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
            MyTextEditor.instance($('#textarea1_2'), {
                height: '50px'
            });
        }

        function try2_1() {
            $('#resultTry2_1').val(MyTextEditor.getCode($('#textarea2')));
        }

        function try2_2() {
            MyTextEditor.setCode($('#textarea2'), $('#resultTry2_2').val());
        }

        function try3_1() {
            MyTextEditor.disable($('#textarea3'), true);
        }

        function try3_2() {
            MyTextEditor.disable($('#textarea3'), false);
            // 		MyTextEditor.destroy($('#textarea3'));
        }
    </script>
</my:html>
