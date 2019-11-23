<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>
<my:html table="YES" modal="YES" validator="YES" fileDownloader="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <h1 class="display-4 text-center">메뉴관리
                <small>
                    For 관리자
                    <i class="fas fa-wrench"></i>
                </small>
            </h1>
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table id="table1" class="table table-bordered table-hover table-sm" data-order='[[ 9, "desc" ]]' data-page-length="10">
                            <thead class="thead-dark">
                            <tr>
                                <th>
                                    <i class="fas fa-key"></i>
                                    &nbsp;메뉴 ID
                                </th>
                                <th>메뉴명</th>
                                <th>메뉴종류</th>
                                <th>URL</th>
                                <th>출력순서</th>
                                <th>상위메뉴</th>
                                <th>사용여부</th>
                                <th>권한</th>
                                <th>수정자</th>
                                <th>수정일</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <my:footer/>
    <!-- Modal -->
    <div id="modalMenu" class="modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">메뉴 입력</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <input type="hidden" id="menuId"/>
                        <div class="form-group row">
                            <label for="menuName" class="col-3 col-form-label">메뉴명</label>
                            <div class="col-9">
                                <input type="text" id="menuName" class="form-control" title="메뉴명" maxlength="30"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="menuType" class="col-3 col-form-label">메뉴종류</label>
                            <div class="col-9">
                                <select id="menuType" class="form-control" title="메뉴종류" style="width: 70%" required></select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="displayOrder" class="col-3 col-form-label">출력순서</label>
                            <div class="col-9">
                                <input type="text" id="displayOrder" class="form-control" title="출력순서" maxlength="10" data-parsley-type="digits"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="parMenuId" class="col-3 col-form-label">상위메뉴</label>
                            <div class="col-9">
                                <select id="parMenuId" class="form-control" title="상위메뉴" style="width: 70%" required></select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="url" class="col-3 col-form-label">URL</label>
                            <div class="col-9">
                                <input type="text" id="url" class="form-control" title="URL" maxlength="1000"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="useTf" class="col-3 col-form-label">사용여부</label>
                            <div class="col-9">
                                <input type="checkbox" class="form-control" id="useTf">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="power" class="col-3 col-form-label">권한</label>
                            <div class="col-9">
                                <select id="power" class="form-control" title="권한" style="width: 30%" required></select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-outline-primary" onclick="MyModal.close($('#modalMenu'));">
                            <i class="far fa-window-close"></i>
                        </button>

                        <div class="btn-group" role="group">
                            <button id="btnGroupDrop1" type="button" class="btn btn-outline-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-trash-alt"></i>
                            </button>
                            <div class="dropdown-menu color-danger" aria-labelledby="btnGroupDrop1">
                                <a class="dropdown-item" href="javascript:void(0);" onclick="deleteMenu();">
                                    <i class="fas fa-trash-alt"></i>
                                </a>
                            </div>
                        </div>

                        <button type="button" class="btn btn-outline-success" onclick="saveMenu();">
                            <i class="far fa-save"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script data-for="ready">
        jQuery(($) => {
            $.when(drawTable1(), MyAjax.getSelectOptions($("#menuType"), "${CONTEXT_PATH}/common/valuelabel/getValueLabelVOList.json", {
                groupCode: "MENU_TYPE"
            }), MyAjax.getSelectOptions($("#power"), "${CONTEXT_PATH}/common/valuelabel/getValueLabelVOList.json", {
                groupCode: "MEMBER_TYPE"
            }), MyAjax.getSelectOptions($("#parMenuId"), "${CONTEXT_PATH}/sample/admin/menu/getPMenuValueLableVOList.json")).done(() => {
                selectList();
            });
        });
    </script>
    <script data-for="define-table">
        function drawTable1() {
            $('#table1').DataTable({
                dom: 'Bfrtip',
                language: MyDataTables.language,
                pagingType: "full_numbers",
                select: true,
                responsive: true,
                scrollY: '400px',
                fixedColumns: {
                    leftColumns: 2
                    // ,rightColumns: 1
                },
                buttons: [{
                    text: '<i class="fas fa-plus"></i>',
                    titleAttr: '추가',
                    action: (e, dt, node, config) => {
                        modalAddMenu();
                    }
                }, {
                    text: '<i class="fas fa-pencil-alt"></i>',
                    titleAttr: '수정',
                    action: (e, dt, node, config) => {
                        modalModifyMenu();
                    }
                }, {
                    text: '<i class="fas fa-trash-alt"></i>',
                    titleAttr: '삭제',
                    action: (e, dt, node, config) => {
                        deleteMenu();
                    }
                }, {
                    extend: 'colvis',
                    text: '<i class="fas fa-eye"></i>',
                    titleAttr: '컬럼선택'
                }, {
                    extend: 'excelHtml5',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '메뉴관리데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fas fa-file-excel"></i> xlsx - javascript',
                    titleAttr: 'javascript'
                }, {
                    text: '<i class="fas fa-file-excel"></i> xlsx - java',
                    titleAttr: 'java',
                    action: (e, dt, node, config) => {
                        const dataParam = {};
                        MyAjax.downloadFile('${CONTEXT_PATH}/sample/admin/menu/adminMenu.xlsx', dataParam);
                    }
                }, {
                    text: '<i class="fas fa-file-excel"></i> xlsx - 대용량 java',
                    titleAttr: '대용량 java',
                    action: (e, dt, node, config) => {
                        const dataParam = {};
                        MyAjax.downloadFile('${CONTEXT_PATH}/sample/admin/menu/adminMenuHugeExcel.xlsx', dataParam);
                    }
                }, {
                    text: '<i class="fas fa-file-pdf"></i> pdf - java',
                    titleAttr: 'java',
                    action: (e, dt, node, config) => {
                        const dataParam = {};
                        MyAjax.downloadFile('${CONTEXT_PATH}/sample/admin/menu/adminMenu.pdf', dataParam);
                    }
                }, {
                    extend: 'print',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '메뉴관리데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fas fa-print"></i>',
                    titleAttr: 'print - table'
                }],
                columnDefs: [{
                    targets: "_all",
                    defaultContent: ""
                }, {
                    targets: [3, 8, 9],
                    visible: false
                }, {
                    targets: 0,
                    width: 100,
                    className: "text-center"
                }, {
                    targets: 2,
                    width: 80,
                    className: "text-center",
                    render: (data, type, row) => {
                        return MyCommon.getLabelFromSelectTag($('#menuType'), data);
                    }
                }, {
                    targets: 4,
                    width: 80,
                    className: "text-right"
                }, {
                    targets: 5,
                    render: (data, type, row) => {
                        return MyCommon.getLabelFromSelectTag($('#parMenuId'), data);
                    }
                }, {
                    targets: 6,
                    width: 80,
                    className: "text-center",
                    render: (data, type, row) => {
                        return data ? '<input type="checkbox" checked disabled />' :
                            '<input type="checkbox" disabled />'
                    }
                }, {
                    targets: 7,
                    width: 100,
                    className: "text-right",
                    render: (data, type, row) => {
                        return MyCommon.getLabelFromSelectTag($('#power'), data) + " 이상";
                    }
                }, {
                    targets: 8,
                    width: 100
                }, {
                    targets: 9,
                    width: 120,
                    className: "text-center",
                    render: (data, type, row) => {
                        return moment(data).format("YYYY-MM-DD HH:mm");
                    }
                }],
                columns: [{
                    "data": "menuId"
                }, {
                    "data": "menuName"
                }, {
                    "data": "menuType"
                }, {
                    "data": "url"
                }, {
                    "data": "displayOrder"
                }, {
                    "data": "parMenuId"
                }, {
                    "data": "useTf"
                }, {
                    "data": "power"
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
            const response = await MyAjax.execute('${CONTEXT_PATH}/sample/admin/menu/getSampleMenuMstVOList.json', {});
            $('#table1').DataTable().clear().rows.add(response).draw();
        }

        function modalAddMenu() {
            $('form.form-horizontal')[0].reset();
            $('#modalMenu div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').hide();
            MyModal.open($('#modalMenu'));
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            $('#parMenuId').val((selectedRow && selectedRow.parMenuId) || 10003);
            $('#power').val(300);
        }

        function modalModifyMenu() {
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("수정하실 항목을 선택해주세요.");
                return;
            }
            $('form.form-horizontal')[0].reset();
            $('div.modal-footer>div.btn-group>div.btn-group').show();
            MyModal.open($('#modalMenu'));
            $('#menuId').val(selectedRow.menuId);
            $('#menuName').val(selectedRow.menuName);
            $('#menuType').val(selectedRow.menuType);
            $('#url').val(selectedRow.url);
            $('#displayOrder').val(selectedRow.displayOrder);
            $('#useTf').prop('checked', selectedRow.useTf);
            $('#parMenuId').val(selectedRow.parMenuId);
            $('#power').val(selectedRow.power);
        }

        async function saveMenu() {
            if (MyValidator.validate($('form.form-horizontal'), true) !== null) {
                return;
            }
            let url;
            if ($('div.modal-footer:visible>div.btn-group>div.btn-group').is(':hidden')) {
                url = '${CONTEXT_PATH}/sample/admin/menu/insertSampleMenuMst.json';
            } else {
                url = '${CONTEXT_PATH}/sample/admin/menu/updateSampleMenuMst.json';
            }
            const response = await MyAjax.execute(url, {
                menuId: $('#menuId').val(),
                menuName: $('#menuName').val(),
                menuType: $('#menuType > option:selected').val(),
                url: $('#url').val(),
                displayOrder: $('#displayOrder').val(),
                parMenuId: $('#parMenuId').val(),
                useTf: $('#useTf').prop('checked'),
                power: $('#power').val()
            }, {
                autoResultFunctionTF: true
            });
            if (_.startsWith(response.responseCode, 'S')) {
                MyModal.close($('#modalMenu'));
                await selectList();
            }
        }

        async function deleteMenu() {
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("삭제하실 항목을 선택해주세요.");
                return;
            }
            if (confirm("정말 삭제하시겠습니까?")) {
                const response = await MyAjax.execute('${CONTEXT_PATH}/sample/admin/menu/deleteSampleMenuMst.json', {
                    menuId: selectedRow.menuId
                }, {
                    autoResultFunctionTF: true
                });
                if (_.startsWith(response.responseCode, 'S')) {
                    MyModal.close($('#modalMenu'));
                    await selectList();
                }
            }
        }
    </script>
</my:html>
