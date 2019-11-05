;
const MyPaging = {
    lastPageNumber: 1, // 마지막페이지 넘버
    numberOfDisplayPages: 10, // 아래 1,2,3,4,5,6,7,8,9,10 페이지넘버 출력 개수
    selectorFirstBtn: null, // 맨앞 버튼 element
    selectorPrevBtn: null, // 이전 버튼 element
    selectorNextBtn: null, // 다음 버튼 element
    selectorLastBtn: null, // 맨끝 버튼 element
    selectorNumberGroup: null, // 페이지넘버 element를 담고 있는 배열 // 1,2,3,4,5,6,7,8,9,10 페이지넘버를 출력하는 테그명(<a>도 자주 쓰이더라?)
    // numberElementTagName : 'button', //
    numberSelectedClassName: 'active', // 1,2,3,4,5,6,7,8,9,10 페이지넘버 중 선택된 element('selected' 도 자주 쓰이더라..)
    positionSelectedClassName: null, // 'parent' 값을 주면 selectedClassName을 부모한테 할당한다.
    selectListFunction: null,
    lastIndex: 1,
    init: () => {
        if (typeof MyPaging.selectListFunction !== "function") {
            console.warn('개발자오류 : 리스트 호출함수를 지정하세요.(selectListFunction)');
            return;
        }
        if (MyCommon.isEmpty(MyPaging.selectorFirstBtn)) {
            console.warn('개발자오류 : selectorFirstBtn 를 지정하세요');
            return;
        }
        if (MyCommon.isEmpty(MyPaging.selectorLastBtn)) {
            console.warn('개발자오류 : selectorLastBtn 를 지정하세요');
            return;
        }
        if (MyCommon.isEmpty(MyPaging.selectorNumberGroup)) {
            console.warn('개발자오류 : selectorNumberGroup 을 지정하세요');
            return;
        }
        MyPaging.bindPageBtn();
        return true;
    },
    initPagingIndex: () => {
        $(MyPaging.selectorFirstBtn).trigger('click');
    },
    bindPageBtn: () => {
        $(document).on('click', MyPaging.selectorNumberGroup, function () {
            // console.log('event :: selectorNumberGroup');
            if (MyPaging.positionSelectedClassName === 'parent') {
                $(this).parent().addClass(MyPaging.numberSelectedClassName).siblings().removeClass(MyPaging.numberSelectedClassName).trigger('focusout');
            } else {
                $(this).addClass(MyPaging.numberSelectedClassName).siblings().removeClass(MyPaging.numberSelectedClassName).trigger('focusout');
            }
            MyPaging.selectListFunction(MyPaging.getSelectedPageIndex());
        });
        $(document).on('click', MyPaging.selectorFirstBtn, () => {
            // console.log('event :: selectorFirstBtn');
            MyPaging.setPageBtnNumber(1);
            $(MyPaging.selectorNumberGroup + ":not(:hidden)").first().trigger('click');
        });
        $(document).on('click', MyPaging.selectorPrevBtn, () => {
            // console.log('event :: selectorPrevBtn');
            if (+$(MyPaging.selectorNumberGroup + ":not(:hidden)").first().text() === 1) {
                $(MyPaging.selectorFirstBtn).trigger('click');
                return;
            }
            MyPaging.setPageBtnNumber(+$(MyPaging.selectorNumberGroup + ":not(:hidden)").first().text() - MyPaging.numberOfDisplayPages);
            $(MyPaging.selectorNumberGroup + '.' + MyPaging.numberSelectedClassName).trigger('click');
        });
        $(document).on('click', MyPaging.selectorNextBtn, () => {
            // console.log('event :: selectorNextBtn');
            if (+$(MyPaging.selectorNumberGroup + ":not(:hidden)").last().text() === MyPaging.lastPageNumber) {
                $(MyPaging.selectorLastBtn).trigger('click');
                return;
            }
            MyPaging.setPageBtnNumber(MyPaging.getSelectedPageIndex() + MyPaging.numberOfDisplayPages);
            $(MyPaging.selectorNumberGroup + '.' + MyPaging.numberSelectedClassName).trigger('click');
        });
        $(document).on('click', MyPaging.selectorLastBtn, () => {
            // console.log('event :: selectorLastBtn');
            MyPaging.setPageBtnNumber((MyPaging.lastPageNumber / MyPaging.numberOfDisplayPages).toFixed(0) * MyPaging.numberOfDisplayPages + 1);
            $(MyPaging.selectorNumberGroup + ":not(:hidden)").last().trigger('click');
        });
    },
    setPageBtnNumber: (pageIndex) => {
        pageIndex = pageIndex || 1;
        if (MyPaging.lastPageNumber) {
            pageIndex = pageIndex > MyPaging.lastPageNumber ? MyPaging.lastPageNumber : pageIndex;
        } else {
            MyPaging.lastPageNumber = 1;
            pageIndex = 1;
        }
        let test = ((pageIndex - 1).toFixed(0) / MyPaging.numberOfDisplayPages);
        test = test < 0 ? 0 : test;
        for (let i = 0; i < MyPaging.numberOfDisplayPages; i++) {
            if (MyPaging.lastPageNumber > test * MyPaging.numberOfDisplayPages + i + 1) {
                // console.info(test * MyPaging.numberOfDisplayPages + i + 1);
                $(MyPaging.selectorNumberGroup).eq(i).text(test * MyPaging.numberOfDisplayPages + i + 1).show();
            } else if (MyPaging.lastPageNumber === test * MyPaging.numberOfDisplayPages + i + 1) {
                // console.info("show");
                MyPaging.lastIndex = i;
                $(MyPaging.selectorNumberGroup).eq(i).text(MyPaging.lastPageNumber).show();
            } else {
                // console.info("hide");
                $(MyPaging.selectorNumberGroup).eq(i).text(MyPaging.lastPageNumber).hide();
            }
        }
    },
    changeValue: (pageIndex, totalItemCount, itemPerPage, numberOfNextPage) => {
        if (MyCommon.isEmpty(itemPerPage)) {
            itemPerPage = 10;
        }
        if (MyCommon.isEmpty(numberOfNextPage)) {
            numberOfNextPage = 10;
        }

        MyPaging.numberOfDisplayPages = numberOfNextPage;
        if (totalItemCount % itemPerPage === 0) {
            MyPaging.lastPageNumber = (totalItemCount / itemPerPage).toFixed(0);
        } else {
            MyPaging.lastPageNumber = (totalItemCount / itemPerPage).toFixed(0) + 1;
        }
        MyPaging.setPageBtnNumber(pageIndex);

        $.each($(MyPaging.selectorNumberGroup + ":not(:hidden)"), (index, element) => {
            if (+$(element).text() === pageIndex) {
                if (MyPaging.positionSelectedClassName === 'parent') {
                    $(element).parent().addClass(MyPaging.numberSelectedClassName).siblings().removeClass(MyPaging.numberSelectedClassName).trigger('focusout');
                } else {
                    $(element).addClass(MyPaging.numberSelectedClassName).siblings().removeClass(MyPaging.numberSelectedClassName).trigger('focusout');
                }
                return false;
            }
        });
    },
    getSelectedPageIndex: (pageIndex) => {
        if (MyCommon.isNotEmpty(pageIndex)) {
            return pageIndex;
        } else {
            return +($(MyPaging.selectorNumberGroup + '.' + MyPaging.numberSelectedClassName).text());
        }
    },
    createHtml: (obj) => {
        $(obj).addClass('pagnation').html(
            '<button type="button" class="begin"></button><button type="button" class="prev"></button><span class="pages">'
            + '<button type="button" class="active">1</button><button type="button" style="display: none;">2</button><button type="button" style="display: none;">3</button>'
            + '<button type="button" style="display: none;">4</button><button type="button" style="display: none;">5</button><button type="button" style="display: none;">6</button>'
            + '<button type="button" style="display: none;">7</button><button type="button" style="display: none;">8</button><button type="button" style="display: none;">9</button>'
            + '<button type="button" style="display: none;">10</button></span><button type="button" class="next"></button><button type="button" class="end"></button>');

        MyPaging.selectorFirstBtn = 'button.begin';
        MyPaging.selectorPrevBtn = 'button.prev';
        MyPaging.selectorNextBtn = 'button.next';
        MyPaging.selectorLastBtn = 'button.end';
        MyPaging.selectorNumberGroup = 'span.pages > button';
        MyPaging.numberSelectedClassName = 'active';
        MyPaging.init();
    }
};
