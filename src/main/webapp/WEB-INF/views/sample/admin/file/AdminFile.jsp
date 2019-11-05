<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>
<my:html table="YES" modal="YES" validator="YES" fileDownloader="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <h1 class="display-4 text-center">파일관리
                <small>
                    For 관리자
                    <i class="fas fa-wrench"></i>
                </small>
            </h1>
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table id="table1" class="table table-bordered table-hover table-sm" data-order='[[ 5, "desc" ]]' data-page-length="10">
                            <thead class="thead-dark">
                            <tr>
                                <th>
                                    <i class="fas fa-key"></i>
                                    &nbsp;파일 Seq.
                                </th>
                                <th>파일명</th>
                                <th>파일확장자</th>
                                <th>MIME_TYPE</th>
                                <th>수정자</th>
                                <th>수정일</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div id="modalFile" class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">파일 입력</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <input type="hidden" id="fileSeq"/>
                            <div class="form-group row">
                                <label for="file" class="col-3 col-form-label">파일선택</label>
                                <div class="col-9">
                                    <input type="file" id="file" class="form-control" title="파일선택" readonly/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="fileName" class="col-3 col-form-label">기존 파일명</label>
                                <div class="col-9">
                                    <input type="text" id="fileName" class="form-control" title="파일명" readonly/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="fileNameExt" class="col-3 col-form-label">기존 파일확장자</label>
                                <div class="col-9">
                                    <input type="text" id="fileNameExt" class="form-control" title="파일확장자" readonly/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="mimeType" class="col-3 col-form-label">기존 MIME_TYP</label>
                                <div class="col-9">
                                    <input type="text" id="mimeType" class="form-control" title="MIME_TYPE" readonly/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-outline-primary" onclick="MyModal.close($('#modalFile'));">
                                <i class="far fa-window-close"></i>
                            </button>

                            <div class="btn-group" role="group">
                                <button id="btnGroupDrop1" type="button" class="btn btn-outline-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                                <div class="dropdown-menu color-danger" aria-labelledby="btnGroupDrop1">
                                    <a class="dropdown-item" href="javascript:void(0);" onclick="deleteFile();">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </div>
                            </div>

                            <button type="button" class="btn btn-outline-success" onclick="saveFile();">
                                <i class="far fa-save"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <my:footer/>
    <script data-for="ready">
        jQuery(($) => {
            drawTable1();
            selectList();
        });
    </script>
    <script data-for="define-table">
        function drawTable1() {
            $('#table1').DataTable({
                dom: 'Bfrtip',
                lengthChange: false,
                language: MyDataTables.language,
                pagingType: "full_numbers",
                select: true,
                responsive: true,
                scrollY: '400px',
                fixedColumns: {
                    leftColumns: 2
                    // 	            ,rightColumns: 1
                },
                buttons: [{
                    text: '<i class="fas fa-plus"></i>',
                    titleAttr: '추가',
                    action: (e, dt, node, config) => {
                        modalAddFile();
                    }
                }, {
                    text: '<i class="fas fa-pencil-alt"></i>',
                    titleAttr: '수정',
                    action: (e, dt, node, config) => {
                        modalModifyFile();
                    }
                }, {
                    text: '<i class="fas fa-trash-alt"></i>',
                    titleAttr: '삭제',
                    action: (e, dt, node, config) => {
                        deleteFile();
                    }
                }, {
                    text: '<i class="fas fa-download"></i>',
                    titleAttr: '다운로드',
                    action: (e, dt, node, config) => {
                        downloadFile();
                    }
                }, {
                    extend: 'colvis',
                    text: '<i class="fas fa-eye"></i>',
                    titleAttr: '컬럼선택'
                }],
                columnDefs: [{
                    targets: "_all",
                    defaultContent: ""
                }, {
                    targets: [4, 5],
                    visible: false
                }, {
                    targets: 0,
                    width: 100,
                    className: "text-center"
                }, {
                    targets: 2,
                    width: 80,
                    className: "text-right"
                }, {
                    targets: 4,
                    width: 100
                }, {
                    targets: 5,
                    width: 120,
                    className: "text-center",
                    render: (data, type, row) => {
                        return moment(data).format("YYYY-MM-DD HH:mm");
                    }
                }],
                columns: [{
                    "data": "fileSeq"
                }, {
                    "data": "fileName"
                }, {
                    "data": "fileNameExt"
                }, {
                    "data": "mimeType"
                }, {
                    "data": "updatedBy"
                }, {
                    "data": "updated"
                }]
            }).on('column-visibility.dt', function (e, indicator) {
                $('#table1').DataTable().columns.adjust().responsive.recalc();
            });
        }
    </script>
    <script>
        async function selectList() {
            const response = await MyAjax.execute('${CONTEXT_PATH}/sample/admin/file/getSampleFileMstVOList.json', {});
            $('#table1').DataTable().clear().rows.add(response).draw();
        }

        function modalAddFile() {
            $('form.form-horizontal')[0].reset();
            $('div.modal-footer>div.btn-group>div.btn-group').hide();
            $('#file').parentsUntil('form.form-horizontal').siblings('div').hide();
            MyModal.open($('#modalFile'));
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            $('#power').val(300);
        }

        function modalModifyFile() {
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("수정하실 항목을 선택해주세요.");
                return;
            }
            $('form.form-horizontal')[0].reset();
            $('div.modal-footer>div.btn-group>div.btn-group').show();
            $('#btnGroupDrop1').next('div').css('display', '');
            $('#file').parentsUntil('form.form-horizontal').siblings('div').show();
            MyModal.open($('#modalFile'));
            $('#fileSeq').val(selectedRow.fileSeq);
            $('#fileName').val(selectedRow.fileName);
            $('#fileNameExt').val(selectedRow.fileNameExt);
            $('#mimeType').val(selectedRow.mimeType);
        }

        async function saveFile() {
            if (MyValidator.validate($('form.form-horizontal'), true) !== null) {
                return;
            }
            if ($('#file')[0].files[0] === undefined || $('#file')[0].files[0] === null) { // 기본 undefined, ie에서는 null
                alert("파일을 선택해주세요.");
                return;
            }
            let url;
            if ($('div.modal-footer:visible>div.btn-group>div.btn-group').is(':hidden')) {
                url = '${CONTEXT_PATH}/sample/admin/file/insertSampleFileMst.json';
            } else {
                url = '${CONTEXT_PATH}/sample/admin/file/updateSampleFileMst.json';
            }
            const formData = new FormData();
            formData.append('fileSeq', $('#fileSeq').val());
            formData.append('file', $('#file')[0].files[0]);
            const response = await MyAjax.executeWithFile(url, formData, {
                autoResultFunctionTF: true
            });
            if (_.startsWith(response.responseCode, 'S')) {
                MyModal.close($('#modalFile'));
                await selectList();
            }
        }

        async function deleteFile() {
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("삭제하실 항목을 선택해주세요.");
                return;
            }
            if (confirm("정말 삭제하시겠습니까?")) {
                const response = await MyAjax.execute('${CONTEXT_PATH}/sample/admin/file/deleteSampleFileMst.json', {
                    fileSeq: selectedRow.fileSeq
                }, {
                    autoResultFunctionTF: true
                });
                if (_.startsWith(response.responseCode, 'S')) {
                    MyModal.close($('#modalFile'));
                    await selectList();
                }
            }
        }

        function downloadFile() {
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("다운로드할 파일을 선택해주세요.");
                return;
            }
            MyAjax.downloadFile('${CONTEXT_PATH}/common/file/download/fileDownload.proc', {
                fileSeq: selectedRow.fileSeq,
                fileName: selectedRow.fileName
            });
        }
    </script>
</my:html>