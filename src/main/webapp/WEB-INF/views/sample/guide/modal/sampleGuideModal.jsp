<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html modal="YES" textEditor="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">모달(modal)
                    <small>띄우기</small>
                </h1>
                <hr class="my-4">
                <h4>required</h4>
                <pre class="prettyprint lang-html">&lt;my:html modal="YES"&gt;</pre>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. 모달 띄우기 </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. 다중 모달 띄우기 </a>
                </div>
            </div>
            <div class="card" id="list1">
                <h3 class="panel-title text-center">1. 모달 띄우기</h3>
                <div class="card-body">
                    <h5 class="card-title">띄우기 / 닫기</h5>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try1_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                    </div>
                    <pre class="prettyprint lang-html">
&lt;div id="try1_modalContent" class="modal" style="display: none;"&gt;
    //modal contents
&lt;/div&gt;
                </pre>
                    <pre class="prettyprint lang-js">
MyModal.open($('#try1_modalContent'));
MyModal.close($('#try1_modalContent'));
</pre>
                </div>

                <div class="card" id="list2">
                    <h3 class="panel-title text-center">2. 다중 모달 띄우기</h3>
                    <div class="card-body">
                        <h5 class="card-title">띄우기</h5>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <button class="btn btn-outline-primary" type="button" onclick="try2_1()">
                                    <i class="far fa-play-circle"></i>
                                    Demo
                                </button>
                            </div>
                        </div>
                        <pre class="prettyprint lang-html">
&lt;div id="try2_1-a" class="modal" style="display: none;"&gt;
    //modal contents
&lt;/div&gt;
                    </pre>
                        <pre class="prettyprint lang-js">
MyModal.openMultiple($('#try2_1-a'));
MyModal.close($('#try2_1-a'));
</pre>
                    </div>

                </div>
                <!-- Modal -->
                <div id="try1_modalContent" class="modal" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">텍스트 에디터 Modal</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal">
                                    <div class="form-group row">
                                        <label for="inputEmail3" class="col-2 col-form-label">Email</label>
                                        <div class="col-10">
                                            <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="inputPassword3" class="col-2 col-form-label">Password</label>
                                        <div class="col-10">
                                            <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <div class="btn-group" role="group">
                                    <button type="button" class="btn btn-outline-primary">Sign in</button>
                                    <button type="button" class="btn btn-outline-secondary" onclick="MyModal.close($('#try1_modalContent'));">Cancel(Close)</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.modal -->
                <!-- Modal -->
                <div id="try2_1-a" class="modal" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <p>
                                    I'm the first modal.
                                    <a href="javascript:void(0);" onclick="MyModal.open($('#try2_1-b'));">Open second modal...</a>
                                </p>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                    exercitation
                                    ullamco
                                    laboris
                                    nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
                                    occaecat
                                    cupidatat non
                                    proident, sunt
                                    in culpa qui officia deserunt mollit anim id est laborum.</p>

                                <div class="btn-group" role="group">
                                    <button type="button" class="btn btn-outline-primary" onclick="MyModal.close($('#try2_1-a'));">Cancel(Close)</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Modal -->
                <div id="try2_1-b" class="modal" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <p>
                                    I'm the second modal.
                                    <a href="javascript:void(0);" onclick="MyModal.open($('#try2_1-c'));">Open third modal...</a>
                                </p>
                                <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa
                                    qui officia
                                    deserunt
                                    mollit anim id est laborum.</p>
                                <div class="btn-group" role="group">
                                    <button type="button" class="btn btn-outline-primary" onclick="MyModal.close($('#try2_1-b'));">Cancel(Close)</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal -->
                <div id="try2_1-c" class="modal" tabindex="-1" role="dialog">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <p>I'm the third modal. You get the idea.</p>
                                <button type="button" class="btn btn-success" onclick="MyModal.close($('#try2_1-c'));">Cancel(Close)</button>
                            </div>
                        </div>
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
            MyModal.open($('#try1_modalContent'));
        }

        function try2_1() {
            MyModal.open($('#try2_1-a'));
        }
    </script>
</my:html>
