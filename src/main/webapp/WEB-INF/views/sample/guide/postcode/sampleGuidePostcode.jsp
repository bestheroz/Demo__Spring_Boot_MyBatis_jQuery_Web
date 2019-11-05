<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html>
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">우편번호 검색창
                    <small>띄우기</small>
                </h1>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. 우편번호 검색창 띄우기 </a>
                </div>
            </div>
            <div class="card" id="list1">
                <h3 class="panel-title text-center">1. 우편번호 검색창 띄우기</h3>
                <div class="card-body">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try1_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly/>
                    </div>
                    <h4>프로토콜별 js 선언</h4>
                    <pre class="prettyprint lang-html">
&lt;%
	if (request.isSecure()) {
%&gt;
&lt;script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"&gt;&lt;/script&gt;
&lt;%
	} else {
%&gt;
&lt;script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"&gt;&lt;/script&gt;
&lt;%
	}
%&gt;
</pre>
                    <pre class="prettyprint lang-js">
new daum.Postcode({
	oncomplete : function(data) {
		console.info(data);
	}
}).open();</pre>
                </div>
            </div>
        </div>
    </main>
    <my:footer/>
    <%
        if (request.isSecure()) {
    %>
    <script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
    <%
    } else {
    %>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <%
        }
    %>
    <script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
    <script data-for="ready">
        jQuery(($) => {
        });
    </script>
    <script>
        function try1_1() {
            new daum.Postcode({
                oncomplete: function (data) {
                    $('#resultTry1_1').val(JSON.stringify(data));
                }
            }).open();
        }
    </script>
</my:html>
